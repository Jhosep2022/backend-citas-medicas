package com.example.backendcitamedica.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "ESPECIALIDAD")
data class Especialidad(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Esp_Id")
    val id: Long = 0,

    @Column(name = "Esp_Nombre", nullable = false)
    val nombre: String,

    @Column(name = "Esp_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Esp_FechaRegistro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Esp_Estado", nullable = false)
    val estado: Boolean = true
)
