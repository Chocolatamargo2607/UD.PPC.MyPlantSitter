package com.example.udppcmyplantsitter.viewModel.appNavegation

sealed class appScreens (val router: String){
    object screenWelcome: appScreens("screenWelcome")
    object screenLogin: appScreens("screenLogin")
    object screenRegister: appScreens("screenRegister")
    object screenMain: appScreens("screenMain")
    object screenAccount: appScreens("screenAccount")
    object screenPlants: appScreens("screenPlants")
    object screenMyPlants: appScreens("screenMyPlants")
}