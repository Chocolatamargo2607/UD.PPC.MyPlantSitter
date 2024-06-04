package com.example.udppcmyplantsitter.model

data class PlantDTO(
    val name: String,
    val description: String,
    val care: String,
    var image: String,
    var imageBytes: ByteArray? = null
)
