package com.example.backendcitamedica.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "CONSULTORIO")
data class Consultorio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Con_Id")
    val id: Long = 0,

    @Column(name = "Con_Nombre", nullable = false)
    val nombre: String,

    @Column(name = "Con_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Con_FechaRegistro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Con_Estado", nullable = false)
    val estado: Boolean = true
)
