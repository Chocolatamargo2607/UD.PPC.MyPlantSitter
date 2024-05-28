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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.ui.theme.SecondColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.udppcmyplantsitter.viewModel.appNavegation.userViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegister(navController: NavController, viewModel: userViewModel = viewModel()){

    val nameUser by viewModel.nameUser.collectAsState()
    val password by viewModel.password.collectAsState()
    val email by viewModel.email.collectAsState()

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
                viewModel.onNameUserChange(it)
            },
            label = {
                Text(text = "Username", color = Color.White)
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
                viewModel.onPasswordChange(it)
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
                viewModel.onEmailChange(it)
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
        Button(onClick = {
            viewModel.registerUser(
                onSuccess = {
                    // Navegar a la pantalla de éxito o hacer algo en el éxito
                },
                onError = { error ->
                    // Mostrar mensaje de error, por ejemplo con un Snackbar
                }
            )
        },
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