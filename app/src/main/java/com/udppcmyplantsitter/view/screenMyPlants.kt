package com.udppcmyplantsitter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.example.udppcmyplantsitter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMyPlants(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        TopAppBar (
            title = { Text(text = "My plants") },
            colors = TopAppBarDefaults.topAppBarColors(
                MainColor,
                titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick ={ navController.navigate(route = appScreens.screenWelcome.router)}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )

                }
            }
        )
        PlantList(navController)
        Spacer(modifier = Modifier.weight(1f))
        RegisterButton(navController)
    }
}

@Composable
fun PlantList(navController: NavController) {
    //lista de plantas con su imagen
    val plantItems = listOf(
        PlantItem("Sunflower", R.drawable.sunflower),
        PlantItem("Planta 2", R.drawable.plantexample),
        PlantItem("Planta 3", R.drawable.iconplant)
    )
    LazyColumn(modifier = Modifier.background(Color.White)
    ){
        items(plantItems) { plant ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navController.navigate("Detalle/${plant.name}") },
                verticalAlignment = Alignment.CenterVertically
            ) {
        Image(
            painter = painterResource(id = plant.imageResId),
            contentDescription = null, // descripcion de la planta
            modifier = Modifier.size(50.dp)
        )
        Text(text = plant.name, modifier = Modifier.padding(start = 16.dp))
    }
        }
    }
}

data class PlantItem(val name: String, val imageResId: Int)
@Composable
fun RegisterButton(navController: NavController) {
    Button(
        onClick = {navController.navigate("/*logica boton*/")},
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MainColor)
    ) {
        Text(text = "Register Plant")
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    screenMyPlants(NavController(LocalContext.current))
}
