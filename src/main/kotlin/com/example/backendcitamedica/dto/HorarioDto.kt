package com.example.backendcitamedica.dto

data class HorarioDto(
    val id: Long,
    val turno: String,
    val dia: String,
    val descripcion: String?,
    val estado: Boolean
)
