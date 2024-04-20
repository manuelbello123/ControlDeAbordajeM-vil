package com.example.controldeabordaje.Navigation

sealed class AppScrens(val route: String){
    object  LoginScreen: AppScrens("login_screen")
    object  ScannerQr: AppScrens("qr_scanner")
}