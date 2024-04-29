package com.udppcmyplantsitter.modelpackage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.udppcmyplantsitter.view.screenLogin
import com.udppcmyplantsitter.view.screenMain
import com.udppcmyplantsitter.view.screenPlants
import com.udppcmyplantsitter.view.screenRegister
import com.udppcmyplantsitter.view.screenRegisterAssignment

sealed class ItemsTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnselected: ImageVector,
    var screen: @Composable (NavController) -> Unit
){
    object tabMain: ItemsTabs(
        "Main",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        {  navController -> screenMain(navController = navController)}
    )
    object tabPlants: ItemsTabs(
        "Plants",
        Icons.Filled.CheckCircle,
        Icons.Outlined.CheckCircle,
        { navController -> screenPlants(navController = navController)}
    )
    object tabMyPlants: ItemsTabs(
        "My Plants",
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder,
        {  navController -> screenRegisterAssignment(navController = navController) }

    )
}