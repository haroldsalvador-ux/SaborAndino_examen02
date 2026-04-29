package com.tecsup.examen02.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.examen02.screens.LoginScreen
import com.tecsup.examen02.screens.HomeScreen
import com.tecsup.examen02.screens.MenuScreen
import com.tecsup.examen02.screens.DetalleScreen
import com.tecsup.examen02.screens.PerfilScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    var pedido by remember { mutableStateOf(mapOf<Int, Int>()) }

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(onLoginExitoso = {
                navController.navigate("home")
            })
        }

        composable("home") {
            HomeScreen(
                onVerMenu = { navController.navigate("menu") },
                onVerPedido = { navController.navigate("perfil") },
                onVerPerfil = { navController.navigate("perfil") }
            )
        }

        composable("menu") {
            MenuScreen(
                onPlatoClick = { id -> navController.navigate("detalle/$id") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("detalle/{platoId}") { backStackEntry ->
            val platoId = backStackEntry.arguments?.getString("platoId")?.toInt() ?: 1
            DetalleScreen(
                platoId = platoId,
                onAgregar = { id, cantidad ->
                    val nuevoPedido = pedido.toMutableMap()
                    nuevoPedido[id] = (nuevoPedido[id] ?: 0) + cantidad
                    pedido = nuevoPedido
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("perfil") {
            PerfilScreen(
                pedido = pedido,
                onBack = { navController.popBackStack() }
            )
        }
    }
}