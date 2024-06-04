package com.example.udppcmyplantsitter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

        Spacer(modifier = Modifier.weight(1f))
        RegisterButton(navController)
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
        Text(text = stringResource(R.string.button_register_plant))
    }
}
@Preview
@Composable
fun screenMyPlantsPreview() {
    screenMyPlants(NavController(LocalContext.current))
}
