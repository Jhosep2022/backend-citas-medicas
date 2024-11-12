package com.example.backendcitamedica.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "DOCTOR")
data class Doctor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Doc_Id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "Doc_Per_Id", nullable = false)
    val persona: Persona,

    @ManyToOne
    @JoinColumn(name = "Doc_Esp_Id", nullable = false)
    val especialidad: Especialidad,

    @ManyToOne
    @JoinColumn(name = "Doc_Con_Id", nullable = false)
    val consultorio: Consultorio,

    @Column(name = "Doc_CodCmp", nullable = false)
    val codCmp: String,

    @Column(name = "Doc_Cargo")
    val cargo: String? = null,

    @Column(name = "Doc_Origen")
    val origen: String? = null
)
