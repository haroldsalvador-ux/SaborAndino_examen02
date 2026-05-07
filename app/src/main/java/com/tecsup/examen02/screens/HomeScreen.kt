package com.tecsup.examen02.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    correo: String,
    onVerMenu: () -> Unit,
    onVerPedido: () -> Unit,
    onVerPerfil: () -> Unit
) {
    val verdeOscuro = Color(0xFF1B5E20)
    val verdeMedio = Color(0xFF388E3C)
    val fondoGris = Color(0xFFF5F5F5)
    val nombreUsuario = correo.substringBefore("@").replaceFirstChar { it.uppercase() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGris)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        // CABECERA: Fondo degradado con esquinas redondeadas abajo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(verdeOscuro, verdeMedio)
                    ),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(bottom = 24.dp)
        ) {
            // Ícono superior derecho
            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .size(28.dp)
                    .align(Alignment.TopEnd)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¡Hola, $nombreUsuario!",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Descubre el auténtico sabor peruano",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        // SECCIÓN INFERIOR
        Text(
            text = "¿Qué deseas hacer hoy?",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // CARDS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Card: Ver Menú
            OptionCard(
                title = "Ver Menú",
                subtitle = "Explora nuestros platos",
                icon = Icons.Default.Restaurant,
                containerColor = Color(0xFFFFF3E0),
                circleColor = Color(0xFFFFE0B2),
                contentColor = Color(0xFFE65100),
                onClick = onVerMenu
            )

            // Card: Mi Pedido
            OptionCard(
                title = "Mi Pedido",
                subtitle = "Revisa lo que pediste",
                icon = Icons.Default.ShoppingCart,
                containerColor = Color(0xFFE8F4FD),
                circleColor = Color(0xFFE3F2FD),
                contentColor = Color(0xFF1565C0),
                onClick = onVerPedido
            )

            // Card: Mi Perfil
            OptionCard(
                title = "Mi Perfil",
                subtitle = "Tu información personal",
                icon = Icons.Default.Person,
                containerColor = Color(0xFFE8F5E9),
                circleColor = Color(0xFFC8E6C9),
                contentColor = Color(0xFF1B5E20),
                onClick = onVerPerfil
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun OptionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    containerColor: Color,
    circleColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo con ícono a la izquierda
            Surface(
                modifier = Modifier.size(52.dp),
                shape = CircleShape,
                color = circleColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            // Flecha a la derecha
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
