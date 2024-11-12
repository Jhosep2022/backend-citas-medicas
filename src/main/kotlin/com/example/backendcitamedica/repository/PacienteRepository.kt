package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Paciente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PacienteRepository : JpaRepository<Paciente, Long>