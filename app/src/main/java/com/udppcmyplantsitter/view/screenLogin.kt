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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.udppcmyplantsitter.viewModel.appNavegation.appNavegation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenLogin(navController: NavController){

    var MainColor = Color(0xFFC7D247)


    var nameUser by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.padding(16.dp)
    ) {

        Button(onClick = { navController.navigate(route = appScreens.screenWelcome.router)},
            colors = ButtonDefaults.buttonColors(MainColor)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
            Text(text = "  Back")
        }
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
                containerColor = MainColor,
                unfocusedIndicatorColor = Color.Black


            )


        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Insert your password")
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
                containerColor = MainColor,
                unfocusedIndicatorColor = Color.Black

            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(MainColor)) {
            Text(text = "Log in")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun screenLoginpreview() {
    screenLogin(NavController(LocalContext.current))
}