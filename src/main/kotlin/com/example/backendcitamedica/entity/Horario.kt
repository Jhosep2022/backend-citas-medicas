package com.example.backendcitamedica.entity
import jakarta.persistence.*

@Entity
@Table(name = "HORARIO")
data class Horario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hor_Id")
    val id: Long = 0,

    @Column(name = "Hor_Turno", nullable = false)
    val turno: String,

    @Column(name = "Hor_Dia", nullable = false)
    val dia: String,

    @Column(name = "Hor_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Hor_Estado", nullable = false)
    val estado: Boolean = true
)
