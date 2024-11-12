package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class RolDto(
    val id: Long = 0,
    val nombre: String,
    val descripcion: String?,
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),
    val estado: Boolean
)
