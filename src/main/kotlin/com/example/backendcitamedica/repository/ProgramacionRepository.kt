package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Programacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProgramacionRepository : JpaRepository<Programacion, Long> {
    fun findByEstado(estado: Boolean): List<Programacion>
}
