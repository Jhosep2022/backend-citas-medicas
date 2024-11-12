package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Cita
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CitaRepository : JpaRepository<Cita, Long> {
    fun findByEstado(estado: Boolean): List<Cita>
}
