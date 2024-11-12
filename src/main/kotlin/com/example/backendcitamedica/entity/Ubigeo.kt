package com.example.backendcitamedica.entity

import jakarta.persistence.*

@Entity
@Table(name = "UBIGEO")
data class Ubigeo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ubi_Id")
    val id: Long = 0,

    @Column(name = "Ubi_IdDepartamento", nullable = false)
    val idDepartamento: Long,

    @Column(name = "Ubi_IdProvincia", nullable = false)
    val idProvincia: Long,

    @Column(name = "Ubi_IdDistrito", nullable = false)
    val idDistrito: Long,

    @Column(name = "Ubi_DescDepartamento", nullable = false)
    val descDepartamento: String,

    @Column(name = "Ubi_DescProvincia", nullable = false)
    val descProvincia: String,

    @Column(name = "Ubi_DescDistrito", nullable = false)
    val descDistrito: String,

    @Column(name = "Ubi_Estado", nullable = false)
    val estado: Boolean = true
)
