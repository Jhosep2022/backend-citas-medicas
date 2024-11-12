package com.example.backendcitamedica.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "MENU")
data class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Men_Id")
    val id: Long = 0,

    @Column(name = "Men_Nombre", nullable = false)
    val nombre: String,

    @Column(name = "Men_Icono")
    val icono: String? = null,

    @Column(name = "Men_Ruta", nullable = false)
    val ruta: String,

    @Column(name = "Men_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Men_FechaRegistro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Men_Estado", nullable = false)
    val estado: Boolean = true
)
