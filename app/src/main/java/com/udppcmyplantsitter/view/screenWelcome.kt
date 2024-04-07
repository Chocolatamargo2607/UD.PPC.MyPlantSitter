package com.udppcmyplantsitter.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenWelcome(navController: NavController){

    val context = LocalContext.current
    var backColor = Color(0xFFC7D247)
    val repository = "https://github.com/Chocolatamargo2607/UD.PPC.MyPlantSitter"
    val repositoryintent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository)) }
    Row(){
        TopAppBar(
            title = { Text(text="Universidad Distrital Francisco Jose De Caldas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    backColor,
                    titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick ={ context.startActivity(repositoryintent)}) {
                    Icon(
                        imageVector = Icons.Default.Home,
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
        Image(painter = painterResource(id = R.drawable.logo), contentDescription ="LogoPlantSitter",
            modifier = Modifier.size(400.dp))
        Button(onClick = { navController.navigate(route = appScreens.screenLogin.router) },
            colors = ButtonDefaults.buttonColors(
                backColor, // Color hexadecimal como color de fondo
                contentColor = Color.White // Color del texto del botón
            )
        ){
            Text(text = "Save New Song")
        }
        Spacer(modifier = Modifier.width(200.dp))

        Button(onClick = { navController.navigate(route = appScreens.screenRegister.router)},

                colors = ButtonDefaults.buttonColors(
                    backColor, // Color hexadecimal como color de fondo
                    contentColor = Color.White // Color del texto del botón
                )

            ) {
            Text(text = "Practice Maraca")

        }
        Text(text = "Forgot password")


    }
}

