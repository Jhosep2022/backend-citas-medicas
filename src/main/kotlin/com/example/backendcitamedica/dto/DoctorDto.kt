package com.example.backendcitamedica.dto

data class DoctorDto(
    val id: Long,
    val personaId: Long,
    val especialidadId: Long,
    val consultorioId: Long,
    val codCmp: String,
    val cargo: String?,
    val origen: String?
)
