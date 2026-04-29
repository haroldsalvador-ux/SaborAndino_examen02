package com.tecsup.examen02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.examen02.data.listaPlatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(platoId: Int, onAgregar: (Int, Int) -> Unit, onBack: () -> Unit) {

    val plato = listaPlatos.find { it.id == platoId }
    var cantidad by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Plato") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (plato != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                Text(text = plato.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = plato.descripcion, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "S/. ${plato.precio}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Cantidad:", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { if (cantidad > 1) cantidad-- }) { Text("-") }
                    Text(text = "  $cantidad  ", fontSize = 20.sp)
                    Button(onClick = { cantidad++ }) { Text("+") }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onAgregar(plato.id, cantidad) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar al pedido")
                }
            }
        }
    }
}