package com.tecsup.examen02.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.examen02.screens.LoginScreen
import com.tecsup.examen02.screens.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(onLoginExitoso = {
                navController.navigate("home")
            })
        }

        composable("home") {
            HomeScreen(
                onVerMenu = { },
                onVerPedido = { },
                onVerPerfil = { }
            )
        }
    }
}