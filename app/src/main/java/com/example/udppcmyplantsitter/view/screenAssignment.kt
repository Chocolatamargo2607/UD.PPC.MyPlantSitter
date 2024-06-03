package com.udppcmyplantsitter.view

import android.icu.util.Calendar
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.udppcmyplantsitter.dataManagement.Helper
import com.udppcmyplantsitter.dataManagement.AssignmentDTO
import com.udppcmyplantsitter.dataManagement.AssignmentService
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun screenRegisterAssignment(navController: NavController) {

    var context = LocalContext.current
    val assignmentService = AssignmentService(Helper(context))
    val scope = rememberCoroutineScope()

    var name by remember {
        mutableStateOf("")
    }
    var task by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    val year: Int
    val month: Int
    val day: Int
    val calendar= Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year: Int, month: Int, day: Int ->
            date = "$day/${month + 1}/$year"
        }, year, month, day
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ){
        Row() {
            TopAppBar(
                title = { Text(text=" My Plant Sitter ღ") },
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
            modifier = androidx.compose.ui.Modifier
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
                    Text(text = "Name Plant", color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            Text(text = "Description Assigment", color = Color.White)
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            TextField(
                value = task,
                onValueChange = {
                    task = it
                },label = {
                    Text(text = "Write the description", color = MainColor)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                )
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            Row (modifier = Modifier.align(Alignment.CenterHorizontally)){
                TextField(
                    value = date,
                    onValueChange = {date = it},
                    readOnly = true,
                    label =  {
                        Text(text = "Date Assigment",color = MainColor)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.background

                    ),
                    modifier = Modifier
                        .width(230.dp)
                )
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                        .clickable { datePickerDialog.show() },
                    tint = Color.White
                )
            }
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            Button(onClick = {
                var plant = AssignmentDTO(0, name, date , task)

                var save = assignmentService.save(plant)
                if (save != -1L) {
                    scope.launch {
                        Toast.makeText(
                            context, "Assignment successfully saved ✅", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    scope.launch {
                        Toast.makeText(
                            context, "Error saving the assignment", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                name = ""
                task = ""
                date= ""
            },colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Save Assigment", color = MainColor)
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun screenRegisterAssignmentPreview() {
    screenRegisterAssignment(NavController(LocalContext.current))
}