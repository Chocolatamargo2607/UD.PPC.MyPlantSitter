package com.udppcmyplantsitter.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegisterMyPlants(navController: NavController) {
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
}
@Preview(showBackground = true)
@Composable
fun screenRegisterMyPlants() {
    screenRegisterMyPlants(NavController(LocalContext.current))
}