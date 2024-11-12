package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Ubigeo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UbigeoRepository : JpaRepository<Ubigeo, Long> {
    fun findByIdDepartamentoAndIdProvinciaAndIdDistrito(
        idDepartamento: Long,
        idProvincia: Long,
        idDistrito: Long
    ): Ubigeo?
}
