package com.tecsup.examen02.model

data class Plato(
    val id: Int,
    val nombre: String,
    val descripcionCorta: String,
    val descripcionDetallada: String,
    val precio: Double,
    val categoria: String,
    val imagen: Int
)