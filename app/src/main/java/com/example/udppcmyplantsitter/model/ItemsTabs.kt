package com.udppcmyplantsitter.modelpackage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.udppcmyplantsitter.view.screenMain
import com.example.udppcmyplantsitter.view.screenMyPlants
import com.example.udppcmyplantsitter.view.screenPlants

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
        {  navController -> screenMain(navController = navController) }
    )
    object tabPlants: ItemsTabs(
        "Plants",
        Icons.Filled.CheckCircle,
        Icons.Outlined.CheckCircle,
        { navController -> screenPlants(navController = navController)}
    )
    @RequiresApi(Build.VERSION_CODES.N)
    object tabMyPlants: ItemsTabs(
        "My Plants",
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder,
        {  navController -> screenMyPlants(navController = navController) }

    )
}