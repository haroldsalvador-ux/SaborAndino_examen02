package com.tecsup.examen02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    correo: String,
    onVerMenu: () -> Unit,
    onVerPedido: () -> Unit,
    onVerPerfil: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "¡Hola, $correo!", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "¿Qué deseas hacer hoy?", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onVerMenu,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(text = "Ver Menú", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVerPedido,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(text = "Mi Pedido", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVerPerfil,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text(text = "Mi Perfil", fontSize = 18.sp)
        }
    }
}