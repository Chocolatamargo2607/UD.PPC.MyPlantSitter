package com.example.udppcmyplantsitter.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.udppcmyplantsitter.dataManagement.Plant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun modalMyPlant(navController: NavController) {
    //ejemplo de planta, a partir del objeto planta de la clase Plant
    val plant = Plant(
        name = "Sunflower",
        description = "Sunflower are perennial flowering plants of the genus Rosa, in the family Rosaceae, or the flower it bears. There are over three hundred species and thousands of cultivars. They form a group of plants that can be erect shrubs, climbing, or trailing, with stems that are often armed with sharp prickles. Flowers vary in size and shape and are usually large and showy, in colors ranging from white through yellows and reds.",
        image = R.drawable.sunflower
    )

    Surface(color = Color.White) {
        Row() {
            TopAppBar(
                title = { Text(text = " My Plant") },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(route = appScreens.screenWelcome.router) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            //.background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Título e imagen de la planta
            PlantHeader(plant = plant)

            // Descripción de la planta
            PlantDescription(description = plant.description!!)

            Button(
                onClick = { navController.navigate("/*logica del boton*/") },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(MainColor)
            ) {
                Text(text = "Remove Plant")
            }

            Button(
                onClick = { navController.navigate("/* Modify Plant screen */") },
                modifier = Modifier.padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(MainColor)
            ) {
                Text(text = "Modify Plant")
            }
        }
    }
}

// Función para renderizar el título y la imagen de la planta
@Composable
fun PlantHeader(plant: Plant) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = plant.name,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = plant.image!!),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PlantDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(top = 16.dp)
    )
}
@Preview
@Composable
fun modalMyPlantPreview() {
    modalMyPlant(NavController(LocalContext.current))
}

