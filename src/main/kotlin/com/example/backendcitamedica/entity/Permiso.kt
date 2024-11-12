package com.example.backendcitamedica.entity

import jakarta.persistence.*

@Entity
@Table(name = "PERMISO")
data class Permiso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pso_Id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "Pso_Rol_Id", nullable = false)
    val rol: Rol,

    @ManyToOne
    @JoinColumn(name = "Pso_Men_Id", nullable = false)
    val menu: Menu,

    @Column(name = "Pso_Agregar", nullable = false)
    val agregar: Boolean = false,

    @Column(name = "Pso_Ver", nullable = false)
    val ver: Boolean = false,

    @Column(name = "Pso_Editar", nullable = false)
    val editar: Boolean = false,

    @Column(name = "Pso_Eliminar", nullable = false)
    val eliminar: Boolean = false,

    @Column(name = "Pso_Estado", nullable = false)
    val estado: Boolean = true
)
