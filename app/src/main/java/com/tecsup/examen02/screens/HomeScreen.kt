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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
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
        // CABECERA CON DEGRADADO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(verdeOscuro, verdeMedio)
                    ),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¡Hola, $nombreUsuario!",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Descubre el auténtico sabor peruano",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopEnd)
            )
        }

        // SECCIÓN DE CARDS
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(
                text = "¿Qué deseas hacer hoy?",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card 1: Ver Menú - Naranja
            ModernHomeCard(
                title = "Ver Menú",
                description = "Explora nuestros platos",
                icon = Icons.Default.RestaurantMenu,
                iconContainerColor = Color(0xFFFFE0B2),
                iconTintColor = Color(0xFFE65100),
                containerColor = Color(0xFFFFF3E0), // Naranja muy claro
                onClick = onVerMenu
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card 2: Mi Pedido - Azul
            ModernHomeCard(
                title = "Mi Pedido",
                description = "Revisa lo que pediste",
                icon = Icons.Default.ShoppingCart,
                iconContainerColor = Color(0xFFE3F2FD),
                iconTintColor = Color(0xFF1565C0),
                containerColor = Color(0xFFE8F4FD), // Azul muy claro
                onClick = onVerPedido
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card 3: Mi Perfil - Verde
            ModernHomeCard(
                title = "Mi Perfil",
                description = "Tu información personal",
                icon = Icons.Default.AccountCircle,
                iconContainerColor = Color(0xFFE8F5E9),
                iconTintColor = Color(0xFF1B5E20),
                containerColor = Color(0xFFE8F5E9), // Verde muy claro
                onClick = onVerPerfil
            )
        }
    }
}

@Composable
fun ModernHomeCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconContainerColor: Color,
    iconTintColor: Color,
    containerColor: Color, // NUEVO PARÁMETRO
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor) // USA EL PARÁMETRO
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo del ícono a la izquierda
            Surface(
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                color = iconContainerColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTintColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos al centro
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = iconTintColor
                )
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            // Flecha a la derecha
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
