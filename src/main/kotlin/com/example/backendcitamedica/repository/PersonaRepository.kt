package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository : JpaRepository<Persona, Long> {
    fun findByUsuario(usuario: String): Persona?
}
