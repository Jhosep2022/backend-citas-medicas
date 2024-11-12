package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Permiso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PermisoRepository : JpaRepository<Permiso, Long>
