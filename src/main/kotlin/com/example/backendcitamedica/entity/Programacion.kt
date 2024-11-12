package com.example.backendcitamedica.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "PROGRAMACION")
data class Programacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pro_Id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "Pro_Doc_Per_Id", nullable = false)
    val doctor: Doctor,

    @ManyToOne
    @JoinColumn(name = "Pro_Hor_Id", nullable = false)
    val horario: Horario,

    @Column(name = "Pro_FechaProgramacion", nullable = false)
    val fechaProgramacion: LocalDateTime,

    @Column(name = "Pro_HoraInicio", nullable = false)
    val horaInicio: String,

    @Column(name = "Pro_HoraFin", nullable = false)
    val horaFin: String,

    @Column(name = "Pro_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Pro_Estado", nullable = false)
    val estado: Boolean = true,

    @OneToMany(mappedBy = "programacion", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val citas: List<Cita> = listOf()
)
