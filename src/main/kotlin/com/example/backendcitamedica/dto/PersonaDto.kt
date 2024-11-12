package com.example.backendcitamedica.dto

import java.time.LocalDateTime

data class PersonaDto(
    val id: Long,
    val rolId: Long,
    val ubigeoId: Long,
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val usuario: String?,
    val clave: String?,
    val fechaRegistro: LocalDateTime,
    val estado: Boolean
)
