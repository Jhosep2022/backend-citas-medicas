package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.UbigeoDto
import com.example.backendcitamedica.entity.Ubigeo
import com.example.backendcitamedica.repository.UbigeoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UbigeoService(private val ubigeoRepository: UbigeoRepository) {

    fun obtenerUbigeoPorId(id: Long): Ubigeo? {
        return ubigeoRepository.findById(id).orElse(null)?.takeIf { it.estado }
    }

    fun obtenerUbigeoPorRegion(idDepartamento: Long, idProvincia: Long, idDistrito: Long): Ubigeo? {
        return ubigeoRepository.findByIdDepartamentoAndIdProvinciaAndIdDistrito(idDepartamento, idProvincia, idDistrito)
            ?.takeIf { it.estado }
    }

    fun obtenerTodos(): List<Ubigeo> {
        return ubigeoRepository.findAll().filter { it.estado }
    }

    @Transactional
    fun crearUbigeo(ubigeoDto: UbigeoDto): Ubigeo {
        val ubigeo = Ubigeo(
            idDepartamento = ubigeoDto.idDepartamento,
            idProvincia = ubigeoDto.idProvincia,
            idDistrito = ubigeoDto.idDistrito,
            descDepartamento = ubigeoDto.descDepartamento,
            descProvincia = ubigeoDto.descProvincia,
            descDistrito = ubigeoDto.descDistrito,
            estado = true // Estado por defecto en true
        )
        return ubigeoRepository.save(ubigeo)
    }

    @Transactional
    fun actualizarUbigeo(id: Long, ubigeoDto: UbigeoDto): Ubigeo? {
        val ubigeoExistente = ubigeoRepository.findById(id).orElse(null)
        return if (ubigeoExistente != null && ubigeoExistente.estado) {
            val ubigeoActualizado = ubigeoExistente.copy(
                idDepartamento = ubigeoDto.idDepartamento,
                idProvincia = ubigeoDto.idProvincia,
                idDistrito = ubigeoDto.idDistrito,
                descDepartamento = ubigeoDto.descDepartamento,
                descProvincia = ubigeoDto.descProvincia,
                descDistrito = ubigeoDto.descDistrito
            )
            ubigeoRepository.save(ubigeoActualizado)
        } else {
            null
        }
    }

    @Transactional
    fun eliminarUbigeo(id: Long): Boolean {
        val ubigeo = ubigeoRepository.findById(id).orElse(null)
        return if (ubigeo != null && ubigeo.estado) {
            ubigeoRepository.save(ubigeo.copy(estado = false)) // Borrado lógico
            true
        } else {
            false
        }
    }
}
