package com.udppcmyplantsitter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegister(navController: NavController){

    var MainColor = Color(0xFFC7D247)
    var SecondColor = Color(0xFF74A6B1)
    
    var nameUser by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Row() {
        TopAppBar(
            title = { Text(text=" My Plant Sitter ღ") },
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
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        TextField(
            value = nameUser,
            onValueChange = {
                nameUser = it
            },
            label = {
                Text(text = "Name User", color = Color.White)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SecondColor,
                unfocusedIndicatorColor = SecondColor


            )


        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = "Password", color = Color.White)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SecondColor,
                unfocusedIndicatorColor = SecondColor

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = {
                email= it
            },
            placeholder = {
                Text(text = "Email", color = Color.White)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = SecondColor,
                unfocusedIndicatorColor = SecondColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(MainColor)) {
            Text(text = "Register")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun screenRegisterpreview() {
    screenRegister(NavController(LocalContext.current))
}