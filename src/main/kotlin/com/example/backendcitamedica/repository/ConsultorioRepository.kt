package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Consultorio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConsultorioRepository : JpaRepository<Consultorio, Long> {
    fun findByEstado(estado: Boolean): List<Consultorio>
}
