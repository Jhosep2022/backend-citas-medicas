package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class ProgramacionDto(
    val id: Long,
    val doctorId: Long,
    val horarioId: Long,
    val fechaProgramacion: LocalDateTime,
    val horaInicio: String,
    val horaFin: String,
    val descripcion: String?,
    val estado: Boolean = true
)
