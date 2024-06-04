package com.example.udppcmyplantsitter.model.FirebaseService

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class FirebaseStorageManager {

    private val storage = Firebase.storage

    suspend fun downloadImage(imageName: String): ByteArray? {
        return try {
            val reference = storage.reference.child(imageName)
            reference.getBytes(MAX_DOWNLOAD_SIZE_BYTES).await()
        } catch (e: Exception) {

            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val MAX_DOWNLOAD_SIZE_BYTES: Long = 1024 * 1024 // 1 MB
    }
}

