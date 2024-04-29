package com.udppcmyplantsitter.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenAccount (navController: NavController){
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
                    .padding(50.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(

                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp)
                        .height(200.dp),

                    contentScale = ContentScale.Inside
                )

                Text(
                    text = "Username",
                    color = Color.Black
                )

                Text(
                    text = "*user*",
                    color = Color.Black
                )

                Text(
                    text = "Email",
                    color = Color.Black
                )

                Text(
                    text = "*email*",
                    color = Color.Black
                )

                //Boton modify information
                Button(
                    onClick = { /* logica del boton*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(MainColor)
                ) {
                    Text(
                        text = "Modify Information",
                        color = Color.White
                    )
                }
                //Boton delete account
                Button(
                    onClick = { /* logica del boton */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(MainColor)
                ) {
                    Text(
                        text = "Delete Account",
                        color = Color.White
                        )
                }
                //Boton logout
                Button(
                    onClick = { /* logica del boton */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(MainColor)
                ) {
                    Text(
                        text = "Logout",
                        color = Color.White
                        )
                }
            }

    }


@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    screenAccount(NavController(LocalContext.current))
}
