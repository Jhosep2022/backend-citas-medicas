package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Horario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HorarioRepository : JpaRepository<Horario, Long> {

    fun findByEstado(estado: Boolean): List<Horario>
}
