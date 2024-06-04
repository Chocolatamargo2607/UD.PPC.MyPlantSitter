package com.udppcmyplantsitter.view

import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.udppcmyplantsitter.dataManagement.AssignmentDTO
import com.udppcmyplantsitter.dataManagement.AssignmentService
import com.udppcmyplantsitter.dataManagement.Helper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegisterMyPlants(navController: NavController) {

    var context = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }

    val nameplant = arrayOf(stringResource(R.string.categori_anthurium),
        stringResource(R.string.categori_air_plants),"Bonsais","Captus",
        stringResource(R.string.categori_hanging_plants),
        stringResource(R.string.categori_succulents)
    )
    val selectPet = remember { mutableStateOf(nameplant[0]) }
    val expanded = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ){
        Row() {
            TopAppBar(
                title = { Text(text= stringResource(R.string.register_my_plants)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    MainColor,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick ={ navController.navigate(route = appScreens.tabsMovements.router)}) {
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
                .background(MainColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = stringResource(R.string.inputmy_name_plant), color = MainColor)
                },
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
                onExpandedChange = { expanded.value= !expanded.value}
            ) {
                TextField(
                    value = selectPet.value,
                    onValueChange = {
                        selectPet.value = it
                    },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                    },
                    modifier = Modifier.menuAnchor(),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = {expanded.value= false}
                ) {
                    nameplant.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it)},
                            onClick = {
                                selectPet.value= it
                                expanded.value= false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                Toast.makeText(
                    context, context.getString(R.string.plant_saved), Toast.LENGTH_SHORT
                ).show()
            },colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = stringResource(R.string.button_save_plant), color = MainColor)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun screenRegisterMyPlants() {
    screenRegisterMyPlants(NavController(LocalContext.current))
}