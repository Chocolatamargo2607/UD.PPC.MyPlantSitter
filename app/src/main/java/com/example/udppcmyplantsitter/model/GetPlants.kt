package com.example.udppcmyplantsitter.model

import com.example.udppcmyplantsitter.model.FirebaseService.FirebaseStorageManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GetPlants {
    private val storageManager = FirebaseStorageManager()

    suspend fun getPlantsFromFirestore(): List<PlantDTO> {
        val db = FirebaseFirestore.getInstance()
        val plantList = db.collection("plants").get().await().map { document ->
            PlantDTO(
                name = document.getString("name") ?: "",
                description = document.getString("description") ?: "",
                care = document.getString("care") ?: "",
                image = document.getString("image") ?: ""
            )
        }
        plantList.forEach { plant ->
            val bytes = storageManager.downloadImage(plant.image)
            plant.imageBytes = bytes
        }
        return plantList
    }
}
