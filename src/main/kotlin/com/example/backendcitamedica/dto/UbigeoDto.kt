package com.example.backendcitamedica.dto

data class UbigeoDto(
    val id: Long,
    val idDepartamento: Long,
    val idProvincia: Long,
    val idDistrito: Long,
    val descDepartamento: String,
    val descProvincia: String,
    val descDistrito: String,
    val estado: Boolean
)
