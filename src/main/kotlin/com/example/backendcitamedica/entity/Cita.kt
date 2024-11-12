package com.example.backendcitamedica.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "CITA")
data class Cita(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cit_Id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "Cit_Pac_Id", nullable = false)
    val paciente: Paciente,

    @ManyToOne
    @JoinColumn(name = "Cit_Pro_Id", nullable = false)
    val programacion: Programacion,

    @Column(name = "Cit_Fecha", nullable = false)
    val fecha: LocalDateTime,

    @Column(name = "Cit_Hora", nullable = false)
    val hora: String,

    @Column(name = "Cit_Enfermedad")
    val enfermedad: String? = null,

    @Column(name = "Cit_Descripcion")
    val descripcion: String? = null,

    @Column(name = "Cit_HistoriaClinica")
    val historiaClinica: String? = null,

    @Column(name = "Cit_Estado", nullable = false)
    val estado: Boolean = true
)
