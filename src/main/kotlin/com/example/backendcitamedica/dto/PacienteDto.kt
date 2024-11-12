package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class PacienteDto(
    val id: Long,
    val personaId: Long,
    val fechaNacimiento: LocalDateTime,
    val edad: Int,
    val tipo: String
)
