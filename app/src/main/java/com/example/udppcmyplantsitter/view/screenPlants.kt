package com.udppcmyplantsitter.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.ui.theme.MainColor
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.SecondColor
import com.udppcmyplantsitter.dataManagement.Helper
import com.udppcmyplantsitter.dataManagement.AssignmentDTO
import com.udppcmyplantsitter.dataManagement.AssignmentService

@Composable
fun screenPlants(navController: NavController){

    val context = LocalContext.current

    var assignmentService = AssignmentService(Helper(context))
    var songs by remember { mutableStateOf(assignmentService.getAllPlants()) }
    var chosenSong by remember { mutableStateOf(AssignmentDTO(0, "", "", "")) }
    var open_Dialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.dp)
        ) {
            items(songs) { song ->
                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            chosenSong = song
                            open_Dialog = true
                        }

                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.iconplant),
                            contentDescription = "iconplant",
                            modifier = Modifier.size(34.dp),
                            tint = SecondColor
                        )

                        Text(
                            text = "  ${song.name}",
                            modifier = Modifier.weight(50f)

                        )

                    }

                }

            }
        }
        if (open_Dialog){
            AlertDialog(onDismissRequest = { open_Dialog = false },
                title = { Text(text = chosenSong.name) },
                text = { Text(text = chosenSong.task) },
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.plantexample),
                        contentDescription = "plant"
                    )
                },
                confirmButton = {
                    Button(onClick = { open_Dialog = false },
                        colors = ButtonDefaults.buttonColors(MainColor)) {
                        Text("Close")
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun screenPlantsreview() {
    screenPlants(NavController(LocalContext.current))
}



