package com.tecsup.examen02.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.examen02.data.listaPlatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(onPlatoClick: (Int) -> Unit, onBack: () -> Unit) {

    val naranjaOscuro = Color(0xFFE65100)
    val naranjaMedio = Color(0xFFFB8C00)
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
                title = { 
                    Text(
                        "Nuestra Carta", 
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = naranjaOscuro
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // Categorías con Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categorias) { categoria ->
                    val seleccionado = categoriaSeleccionada == categoria
                    FilterChip(
                        selected = seleccionado,
                        onClick = { categoriaSeleccionada = categoria },
                        label = { Text(categoria) },
                        shape = RoundedCornerShape(12.dp),
                        border = if (seleccionado) null else BorderStroke(1.dp, naranjaOscuro),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = naranjaOscuro,
                            selectedLabelColor = Color.White,
                            labelColor = naranjaOscuro
                        )
                    )
                }
            }

            // Lista de Platos
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(platosFiltrados) { plato ->
                    Card(
                        onClick = { onPlatoClick(plato.id) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min) // Para que la barra lateral ocupe toda la altura
                        ) {
                            // Franja decorativa izquierda
                            Box(
                                modifier = Modifier
                                    .width(6.dp)
                                    .fillMaxHeight()
                                    .background(naranjaOscuro)
                            )
                            
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = plato.nombre, 
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = naranjaOscuro
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = plato.descripcionCorta, 
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                        maxLines = 2
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "S/. ${String.format("%.2f", plato.precio)}", 
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = naranjaMedio
                                    )
                                }
                                
                                // Placeholder para imagen pequeña
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFFF3E0)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "🍽️",
                                        fontSize = 32.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
