package com.tecsup.examen02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.examen02.data.listaPlatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(pedido: Map<Int, Int>, onBack: () -> Unit) {

    val total = pedido.entries.sumOf { (id, cantidad) ->
        val plato = listaPlatos.find { it.id == id }
        (plato?.precio ?: 0.0) * cantidad
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil / Mi Pedido") },
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
            Text(text = "Nombre: Usuario Sabor Andino", fontSize = 16.sp)
            Text(text = "Correo: usuario@saborandino.com", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Mi Pedido:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (pedido.isEmpty()) {
                Text(text = "No has agregado platos aún.", fontSize = 16.sp)
            } else {
                LazyColumn {
                    items(pedido.entries.toList()) { (id, cantidad) ->
                        val plato = listaPlatos.find { it.id == id }
                        if (plato != null) {
                            Text(
                                text = "${plato.nombre} x$cantidad — S/. ${"%.2f".format(plato.precio * cantidad)}",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Total: S/. ${"%.2f".format(total)}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
