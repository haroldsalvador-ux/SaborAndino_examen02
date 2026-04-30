package com.tecsup.examen02.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.examen02.data.listaPlatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(platoId: Int, onAgregar: (Int, Int) -> Unit, onBack: () -> Unit, onVerCarrito: () -> Unit) {

    val plato = listaPlatos.find { it.id == platoId }
    var cantidad by remember { mutableStateOf(1) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    val verdeOscuro = Color(0xFF1B5E20)
    val verdeMedio = Color(0xFF388E3C)
    val verdeMuyClaro = Color(0xFFF1F8E9)
    val verdeClaro = Color(0xFFC8E6C9)

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("¡Excelente elección!", fontWeight = FontWeight.Bold, color = verdeOscuro) },
            text = { Text("Se han añadido $cantidad ${plato?.nombre} a tu pedido.") },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogo = false
                        onVerCarrito()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Ir al Pedido")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                        onBack()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = verdeOscuro)
                ) {
                    Text("Seguir Explorando")
                }
            },
            shape = RoundedCornerShape(28.dp),
            containerColor = Color.White
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Plato", fontWeight = FontWeight.Bold, color = Color.White) },
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
                    containerColor = verdeOscuro
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        if (plato != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Box de imagen superior
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(verdeClaro),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = verdeOscuro
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Nombre y Precio
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = plato.nombre, 
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = verdeOscuro,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        color = verdeMuyClaro,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "S/. ${String.format("%.2f", plato.precio)}", 
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = verdeMedio,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "Descripción",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = verdeOscuro
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = plato.descripcionDetallada, 
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF424242), // Gris oscuro
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Selector de cantidad
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularQuantityButton(
                            text = "-", 
                            color = verdeOscuro,
                            onClick = { if (cantidad > 1) cantidad-- },
                            enabled = cantidad > 1
                        )
                        
                        Text(
                            text = "$cantidad", 
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = verdeOscuro,
                            modifier = Modifier.padding(horizontal = 40.dp)
                        )
                        
                        CircularQuantityButton(
                            text = "+", 
                            color = verdeOscuro,
                            onClick = { cantidad++ }
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botón de acción
                    Button(
                        onClick = {
                            onAgregar(plato.id, cantidad)
                            mostrarDialogo = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Text(
                            "AÑADIR AL PEDIDO", 
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircularQuantityButton(text: String, color: Color, onClick: () -> Unit, enabled: Boolean = true) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        color = if (enabled) color else Color.LightGray,
        modifier = Modifier.size(50.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text, 
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
