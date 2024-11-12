package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class ConsultorioDto(
    val id: Long,
    val nombre: String,
    val descripcion: String?,
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),
    val estado: Boolean
)
