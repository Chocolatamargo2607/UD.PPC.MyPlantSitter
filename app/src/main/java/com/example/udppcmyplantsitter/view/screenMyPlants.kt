package com.example.udppcmyplantsitter.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class Plant(val name: String, val category: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMyPlants(navController: NavController) {
    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val email = currentUser?.email ?: "Correo electrónico no disponible"

    var plants by remember { mutableStateOf(listOf<Plant>()) }

    LaunchedEffect(Unit) {
        if (email != "Correo electrónico no disponible") {
            val db = FirebaseFirestore.getInstance()
            try {
                val documents = db.collection("users").document(email).collection("plants").get().await()
                plants = documents.map { document ->
                    Plant(
                        name = document.getString("name") ?: "",
                        category = document.getString("category") ?: ""
                    )
                }
            } catch (e: Exception) {
                Log.e("screenMyPlants", "Error getting plants", e)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (plants.isEmpty()) {
            Text(
                text = " ",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.Gray
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(plants) { plant ->
                    PlantCard(plant)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        RegisterButton(navController)
    }
}

@Composable
fun PlantCard(plant: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${plant.name}")
            Text(text = "Category: ${plant.category}")
        }
    }
}

@Composable
fun RegisterButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(route = appScreens.screenRegisterMyPlants.router) },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MainColor)
    ) {
        Text(text = stringResource(R.string.button_register_plant))
    }
}

@Preview
@Composable
fun screenMyPlantsPreview() {
    screenMyPlants(NavController(LocalContext.current))
}
