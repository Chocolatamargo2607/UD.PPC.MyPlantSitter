package com.udppcmyplantsitter.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegisterMyPlants(navController: NavController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    val nameplant = arrayOf(
        stringResource(R.string.categori_anthurium),
        stringResource(R.string.categori_air_plants),
        "Bonsais", "Captus",
        stringResource(R.string.categori_hanging_plants),
        stringResource(R.string.categori_succulents)
    )
    val selectPet = remember { mutableStateOf(nameplant[0]) }
    val expanded = remember { mutableStateOf(false) }

    val db = FirebaseFirestore.getInstance()
    val firebaseAuth = FirebaseAuth.getInstance()

    // Obtener el usuario actualmente autenticado
    val currentUser = firebaseAuth.currentUser

    // Obtener el correo electrónico del usuario
    val email = currentUser?.email ?: "Correo electrónico no disponible"

    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.register_my_plants)) },
            colors = TopAppBarDefaults.topAppBarColors(
                MainColor,
                titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(route = appScreens.tabsMovements.router) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = { newName ->
                    name = newName
                    Log.d("RegisterMyPlants", "TextField value: $newName")
                },
                label = { Text(text = stringResource(R.string.inputmy_name_plant), color = MainColor) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(R.string.category_of_plant), color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value }
            ) {
                TextField(
                    value = selectPet.value,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                    modifier = Modifier.menuAnchor(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    nameplant.forEach { plant ->
                        DropdownMenuItem(
                            text = { Text(text = plant) },
                            onClick = {
                                selectPet.value = plant
                                expanded.value = false
                                Log.d("RegisterMyPlants", "DropdownMenu value: $plant")
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                Log.d("RegisterMyPlants", "Save Button clicked: name = $name, category = ${selectPet.value}")
                db.collection("users").document(email).collection("plants").add(
                    hashMapOf(
                        "name" to name,
                        "category" to selectPet.value
                    )
                ).addOnSuccessListener {
                    Toast.makeText(context, context.getString(R.string.plant_saved), Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Toast.makeText(context, context.getString(R.string.Failed), Toast.LENGTH_SHORT).show()
                    Log.e("RegisterMyPlants", "Error adding document", e)
                }
            }, colors = ButtonDefaults.buttonColors(Color.White)) {
                Text(text = stringResource(R.string.button_save_plant), color = MainColor)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun screenRegisterMyPlantsPreview() {
    screenRegisterMyPlants(NavController(LocalContext.current))
}
