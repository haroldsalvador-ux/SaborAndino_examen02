package com.tecsup.examen02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(text = "Cliente", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Nombre: Usuario Sabor Andino", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Correo: usuario@saborandino.com", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Teléfono: +51 999 888 777", fontSize = 16.sp)
        }
    }
}