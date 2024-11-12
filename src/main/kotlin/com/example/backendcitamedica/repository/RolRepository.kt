package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Rol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RolRepository : JpaRepository<Rol, Long> {
    fun findByEstado(estado: Boolean): List<Rol>
    fun findByNombre(nombre: String): Rol?
}
