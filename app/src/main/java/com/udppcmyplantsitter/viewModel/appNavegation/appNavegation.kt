package com.udppcmyplantsitter.viewModel.appNavegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.udppcmyplantsitter.viewModel.appNavegation.appScreens
import com.udppcmyplantsitter.view.screenAccount
import com.udppcmyplantsitter.view.screenWelcome
import com.udppcmyplantsitter.view.screenLogin
import com.udppcmyplantsitter.view.screenMain
import com.udppcmyplantsitter.view.modalMyPlant
import com.udppcmyplantsitter.view.screenMyPlants
import com.udppcmyplantsitter.view.screenPlants
import com.udppcmyplantsitter.view.screenRegister
import com.udppcmyplantsitter.view.screenRegisterAssignment
import com.udppcmyplantsitter.view.screenRegisterMyPlants


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun appNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = appScreens.screenWelcome.router){
        composable(route= appScreens.screenWelcome.router){ screenWelcome(navController)}
        composable(route= appScreens.screenLogin.router){ screenLogin(navController) }
        composable(route= appScreens.screenRegister.router){ screenRegister(navController) }
        composable(route= appScreens.screenMain.router){ screenMain(navController) }
        composable(route= appScreens.screenAccount.router){ screenAccount(navController) }
        composable(route= appScreens.screenPlants.router){ screenPlants(navController) }
        composable(route= appScreens.screenMyPlants.router){ screenMyPlants(navController) }
        composable(route= appScreens.screenAssignment.router){ screenRegisterAssignment(navController)}
        composable(route= appScreens.tabsMovements.router){ tabsMovements(navController)}
        composable(route= appScreens.screenRegisterMyPlants.router){ screenRegisterMyPlants(navController) }
        composable(route= appScreens.modalMyPlant.router){ modalMyPlant(navController)}
    }
}