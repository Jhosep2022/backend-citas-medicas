package com.example.backendcitamedica.entity

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "ROL")
data class Rol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rol_Id")
    val id: Long = 0,

    @Column(name = "Rol_Nombre", nullable = false)
    val nombre: String,

    @Column(name = "Rol_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Rol_FechaRegistro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Rol_Estado", nullable = false)
    val estado: Boolean = true,

    @OneToMany(mappedBy = "rol", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val personas: List<Persona> = listOf(),

    @OneToMany(mappedBy = "rol", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val permisos: List<Permiso> = listOf()
)

