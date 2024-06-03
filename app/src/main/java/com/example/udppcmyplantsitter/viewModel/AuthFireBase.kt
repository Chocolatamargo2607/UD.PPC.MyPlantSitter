package com.example.udppcmyplantsitter.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class AuthFireBase: ViewModel() {
    private val auth: FirebaseAuth= Firebase.auth
    private val _loading= MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String, home: ()-> Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult->
                    Log.d("FB", "" +
                            "singInWithEmailAndPassword Logueado!!!: ${authResult.toString()}")
                    home()
                }
                .addOnFailureListener{ex->

                    Log.d("FB", "" +
                            "singInWithEmailAndPassword Falló!!!: ${ex.localizedMessage}")
                    //errorLogueo() funcion para demostrar que algo fallo
                }
        }catch(ex:Exception){
            Log.d("Login","Logueo: ${ex.message}")
        }
    }

    fun createUser(email: String,password: String,home: () -> Unit){
        if (_loading.value== false){
            _loading.value= true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        home()
                    }else{
                        Log.d("Login", "createUser: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }

}