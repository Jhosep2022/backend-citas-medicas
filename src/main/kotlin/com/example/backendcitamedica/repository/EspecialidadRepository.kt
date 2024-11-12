package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Especialidad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EspecialidadRepository : JpaRepository<Especialidad, Long> {

    fun findByEstadoTrue(): List<Especialidad>
    fun findByIdAndEstadoTrue(id: Long): Especialidad?
}
