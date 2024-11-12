package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class MenuDto(
    val id: Long,
    val nombre: String,
    val icono: String?,
    val ruta: String,
    val descripcion: String?,
    val fechaRegistro: LocalDateTime,
    val estado: Boolean
)
