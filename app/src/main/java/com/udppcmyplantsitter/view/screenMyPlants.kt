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
import com.udppcmyplantsitter.dataManagement.Plant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMyPlants(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        PlantList(navController)
        Spacer(modifier = Modifier.weight(1f))
        RegisterButton(navController)
    }
}

@Composable
fun PlantList(navController: NavController) {
    //ejemplo de plantas
    val plant1 = Plant("Sunflower",null, R.drawable.sunflower)
    val plant2 = Plant("Cactus", null, R.drawable.plantexample)
    val plant3 = Plant("Example Plant", null, R.drawable.iconplant)

    //lista de plantas con su imagen
    val plantList = listOf(plant1,plant2,plant3)

    LazyColumn(modifier = Modifier.background(Color.White)
    ){
        items(plantList) { plant ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navController.navigate(route = appScreens.modalMyPlant.router) },
                verticalAlignment = Alignment.CenterVertically
            ) {
        Image(
            painter = painterResource(id = plant.image!!),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(text = plant.name, modifier = Modifier.padding(start = 16.dp))
    }
        }
    }
}
@Composable
fun RegisterButton(navController: NavController) {
    Button(
        onClick = {navController.navigate(route = appScreens.screenRegisterMyPlants.router)},
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
fun screenMyPlantsPreview() {
    screenMyPlants(NavController(LocalContext.current))
}
