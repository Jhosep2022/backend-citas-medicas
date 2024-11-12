package com.example.backendcitamedica.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "PACIENTE")
data class Paciente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pac_Id")
    val id: Long = 0,

    @OneToOne
    @JoinColumn(name = "Pac_Per_Id", nullable = false)
    val persona: Persona,

    @Column(name = "Pac_FechaNacimiento", nullable = false)
    val fechaNacimiento: LocalDateTime,

    @Column(name = "Pac_Edad", nullable = false)
    val edad: Int,

    @Column(name = "Pac_Tipo", nullable = false)
    val tipo: String
)
