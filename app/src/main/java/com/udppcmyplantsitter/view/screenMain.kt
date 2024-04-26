package com.udppcmyplantsitter.view


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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

import com.example.udppcmyplantsitter.ui.theme.MainColor
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens



///////////
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.toSize
import com.example.udppcmyplantsitter.R
import com.example.udppcmyplantsitter.ui.theme.SecondColor
import com.udppcmyplantsitter.dataManagement.Helper
import com.udppcmyplantsitter.dataManagement.AssignmentDTO
import com.udppcmyplantsitter.dataManagement.AssignmentService


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenMain(navController: NavController){
    //NavigationDrawer()
    val context = LocalContext.current
    //link repo
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val onSearch: (String) -> Unit = {
        Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
        active = false
    }

    var assignmentService = AssignmentService(Helper(context))
    var songs by remember { mutableStateOf(assignmentService.getAllPlants()) }
    var chosenSong by remember { mutableStateOf(AssignmentDTO(0, "", "", "")) }
    var open_Dialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            Button(onClick = { navController.navigate(route = appScreens.screenAssignment.router)},
                colors = ButtonDefaults.buttonColors(MainColor)) {
                Text(text = "Register Assignment")
            }

            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = onSearch,
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text(text = "Search") },
                trailingIcon = {
                    IconButton(
                        onClick = { onSearch(query) },
                        enabled = query.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    items(songs) { song ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(7.dp)
                                .clickable {
                                    chosenSong = song
                                    open_Dialog = true
                                }

                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                //Text(text = "ID: ${song.id}", modifier = Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SecondColor
                                )
                                Text(
                                    text = "  ${song.name}",
                                    modifier = Modifier.weight(3f)
                                )
                                Text(
                                    text = "Assigned day: ${song.date}\n",
                                    modifier = Modifier.weight(3f)
                                )
                                IconButton(onClick = {
                                    assignmentService.delete(song.id)
                                    songs = assignmentService.getAllPlants()
                                    Toast.makeText(context, "Assignment Deleted", Toast.LENGTH_SHORT)
                                        .show()
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Deleted")
                                }
                            }

                        }

                    }
                }
                if (open_Dialog){
                    AlertDialog(onDismissRequest = { open_Dialog = false },
                        title = { Text(text = chosenSong.name) },
                        text = { Text(text = chosenSong.task) },
                        confirmButton = {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(MainColor)) {
                                Text("Edit")
                            }
                            Button(onClick = { open_Dialog = false },
                                colors = ButtonDefaults.buttonColors(MainColor)) {
                                Text("Close")

                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun screenMainpreview() {
    screenMain(NavController(LocalContext.current))
}


