package com.tecsup.examen02.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.examen02.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var correoUsuario by remember { mutableStateOf("") }
    var pedido by remember { mutableStateOf(mapOf<Int, Int>()) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginExitoso = { correo ->
                correoUsuario = correo
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        composable("home") {
            HomeScreen(
                correo = correoUsuario,
                onVerMenu = { navController.navigate("menu") },
                onVerPedido = { navController.navigate("pedido") },
                onVerPerfil = { navController.navigate("perfil") }
            )
        }

        composable("menu") {
            MenuScreen(
                onPlatoClick = { platoId ->
                    navController.navigate("detalle/$platoId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "detalle/{platoId}",
            arguments = listOf(navArgument("platoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val platoId = backStackEntry.arguments?.getInt("platoId") ?: 0
            DetalleScreen(
                platoId = platoId,
                onAgregar = { id, cant ->
                    val nuevaCantidad = (pedido[id] ?: 0) + cant
                    pedido = pedido + (id to nuevaCantidad)
                },
                onBack = { navController.popBackStack() },
                onVerCarrito = { navController.navigate("pedido") }
            )
        }

        composable("pedido") {
            MiPedidoScreen(
                pedido = pedido,
                onBack = { navController.popBackStack() },
                onPagar = {
                    pedido = emptyMap()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onRestar = { id ->
                    val cantActual = pedido[id] ?: 0
                    if (cantActual > 1) {
                        pedido = pedido + (id to cantActual - 1)
                    } else {
                        pedido = pedido - id
                    }
                },
                onSumar = { id ->
                    val cantActual = pedido[id] ?: 0
                    pedido = pedido + (id to cantActual + 1)
                }
            )
        }

        composable("perfil") {
            PerfilScreen(
                correo = correoUsuario,
                onBack = { navController.popBackStack() },
                onCerrarSesion = {
                    correoUsuario = ""
                    pedido = emptyMap()
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}
