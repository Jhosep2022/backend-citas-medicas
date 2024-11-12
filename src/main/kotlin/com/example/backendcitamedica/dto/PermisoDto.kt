package com.example.backendcitamedica.dto

data class PermisoDto(
    val id: Long,
    val rolId: Long,
    val menuId: Long,
    val agregar: Boolean,
    val ver: Boolean,
    val editar: Boolean,
    val eliminar: Boolean,
    val estado: Boolean
)
