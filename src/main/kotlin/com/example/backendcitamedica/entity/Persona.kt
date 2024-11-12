package com.example.backendcitamedica.entity

import com.example.backendcitamedica.dto.PersonaDto
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "PERSONA")
data class Persona(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Per_Id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "Per_Rol_Id", nullable = false)
    val rol: Rol,

    @ManyToOne
    @JoinColumn(name = "Per_Ubi_Id", nullable = false)
    val ubigeo: Ubigeo,

    @Column(name = "Per_Nombres", nullable = false)
    val nombres: String,

    @Column(name = "Per_Apellidos", nullable = false)
    val apellidos: String,

    @Column(name = "Per_Dni", nullable = false)
    val dni: String,

    @Column(name = "Per_Genero", nullable = false)
    val genero: String,

    @Column(name = "Per_Direccion")
    val direccion: String? = null,

    @Column(name = "Per_Telefono")
    val telefono: String? = null,

    @Column(name = "Per_Email")
    val email: String? = null,

    @Column(name = "Per_Usuario")
    val usuario: String? = null,

    @Column(name = "Per_Clave")
    val clave: String? = null,

    @Column(name = "Per_FechaRegistro", nullable = false)
    val fechaRegistro: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Per_Estado", nullable = false)
    val estado: Boolean = true
) {
    // Método de conversión a DTO
    fun toDto(): PersonaDto {
        return PersonaDto(
            id = this.id,
            rolId = this.rol.id,
            ubigeoId = this.ubigeo.id,
            nombres = this.nombres,
            apellidos = this.apellidos,
            dni = this.dni,
            genero = this.genero,
            direccion = this.direccion,
            telefono = this.telefono,
            email = this.email,
            usuario = this.usuario,
            clave = this.clave,
            fechaRegistro = this.fechaRegistro,
            estado = this.estado
        )
    }
}
