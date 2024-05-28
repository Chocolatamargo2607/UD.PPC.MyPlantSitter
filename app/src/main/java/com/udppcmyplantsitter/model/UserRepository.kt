package com.udppcmyplantsitter.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await

class UserRepository{
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    suspend fun getUsers():List<User>{
        val snapshot = database.child("users").get().await()
        return snapshot.children.mapNotNull { it.getValue(User::class.java) }
    }

    suspend fun addUser(user: User){
        database.child("users").child(user.userName).setValue(user).await()
    }

    suspend fun deleteUser(userUsername:String){
        database.child("users").child(userUsername).removeValue().await()
    }

    suspend fun updateUser(user: User){
        database.child("users").child(user.userName).setValue(user).await()
    }
}
