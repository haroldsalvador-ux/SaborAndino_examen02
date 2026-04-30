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
import com.tecsup.examen02.screens.MiPedidoScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    var pedido by remember { mutableStateOf(mapOf<Int, Int>()) }
    var correoUsuario by remember { mutableStateOf("") }
    var correoCompleto by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(onLoginExitoso = { correo ->
                correoCompleto = correo
                correoUsuario = correo.substringBefore("@")
                navController.navigate("home")
            })
        }

        composable("home") {
            HomeScreen(
                correo = correoUsuario,
                onVerMenu = { navController.navigate("menu") },
                onVerPedido = { navController.navigate("mipedido") },
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
                },
                onBack = { navController.popBackStack() },
                onVerCarrito = { navController.navigate("mipedido") }
            )
        }

        composable("mipedido") {
            MiPedidoScreen(
                pedido = pedido,
                onBack = { navController.popBackStack() },
                onPagar = {
                    pedido = mapOf()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("perfil") {
            PerfilScreen(
                correo = correoCompleto,
                onBack = { navController.popBackStack() },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}