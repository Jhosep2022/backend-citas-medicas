package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class CitaDto(
    val id: Long,
    val pacienteId: Long,
    val programacionId: Long,
    val fecha: LocalDateTime,
    val hora: String,
    val enfermedad: String?,
    val descripcion: String?,
    val historiaClinica: String?,
    val estado: Boolean
)
