package com.udppcmyplantsitter.view

import androidx.compose.runtime.Composable

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController



@Composable
fun screenRegisterAssignment(navController: NavController) {



}

@Preview(showBackground = true)
@Composable
fun screenRegisterAssignmentPreview() {
    screenRegisterAssignment(NavController(LocalContext.current))
}