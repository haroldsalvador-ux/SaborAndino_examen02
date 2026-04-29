package com.tecsup.examen02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.examen02.data.listaPlatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onPlatoClick: (Int) -> Unit, onBack: () -> Unit) {

    val categorias = listOf("Todos", "Entradas", "Platos de Fondo", "Postres", "Bebidas")
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val platosFiltrados = if (categoriaSeleccionada == "Todos") {
        listaPlatos
    } else {
        listaPlatos.filter { it.categoria == categoriaSeleccionada }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            LazyRow(modifier = Modifier.padding(8.dp)) {
                items(categorias) { categoria ->
                    FilterChip(
                        selected = categoriaSeleccionada == categoria,
                        onClick = { categoriaSeleccionada = categoria },
                        label = { Text(categoria) },
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

            LazyColumn {
                items(platosFiltrados) { plato ->
                    Card(
                        onClick = { onPlatoClick(plato.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = plato.nombre, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = plato.descripcion, fontSize = 14.sp)
                            Text(text = "S/. ${plato.precio}", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}
