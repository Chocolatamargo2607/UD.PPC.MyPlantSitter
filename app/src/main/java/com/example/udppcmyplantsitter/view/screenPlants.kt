package com.example.udppcmyplantsitter.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.model.GetPlants
import com.example.udppcmyplantsitter.model.PlantDTO
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.Image as Image

@Composable
fun screenPlants(navController: NavController) {
    val context = LocalContext.current
    val getPlants = GetPlants()
    val scope = rememberCoroutineScope()

    var plants by remember { mutableStateOf(listOf<PlantDTO>()) }
    var chosenPlant by remember { mutableStateOf<PlantDTO?>(null) }
    var openDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch {
            plants = getPlants.getPlantsFromFirestore()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(plants) { plant ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            chosenPlant = plant
                            openDialog = true
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        plant.imageBytes?.let { bytes ->
                            ImageFromBytes(bytes = bytes, modifier = Modifier.size(34.dp))
                        } ?: run {
                            Text("Cargando imagen...", modifier = Modifier.size(34.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = plant.name,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        if (openDialog && chosenPlant != null) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = { Text(text = chosenPlant!!.name) },
                text = {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text(text = chosenPlant!!.description)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "CUIDADOS: ${chosenPlant!!.care}")
                    }
                },
                icon = {
                    chosenPlant!!.imageBytes?.let { bytes ->
                        ImageFromBytes(bytes = bytes, modifier = Modifier.size(100.dp))
                    }
                },
                confirmButton = {
                    Button(onClick = { openDialog = false }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

@Composable
fun ImageFromBytes(bytes: ByteArray?, modifier: Modifier = Modifier) {
    if (bytes != null) {
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
        Image(
            bitmap = bitmap,
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun screenPlantsPreview() {
    screenPlants(NavController(LocalContext.current))
}


