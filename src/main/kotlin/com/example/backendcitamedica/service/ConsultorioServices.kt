package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.ConsultorioDto
import com.example.backendcitamedica.entity.Consultorio
import com.example.backendcitamedica.repository.ConsultorioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ConsultorioService(private val consultorioRepository: ConsultorioRepository) {

    fun obtenerTodos(estado: Boolean): List<ConsultorioDto> {
        return consultorioRepository.findByEstado(estado).map { it.toDto() }
    }

    fun obtenerPorId(id: Long): ConsultorioDto? {
        val consultorio = consultorioRepository.findById(id).orElse(null)
        return consultorio?.takeIf { it.estado }?.toDto()
    }

    @Transactional
    fun crearConsultorio(consultorioDto: ConsultorioDto): ConsultorioDto {
        val consultorio = Consultorio(
            nombre = consultorioDto.nombre,
            descripcion = consultorioDto.descripcion,
            fechaRegistro = LocalDateTime.now(),
            estado = true
        )
        return consultorioRepository.save(consultorio).toDto()
    }


    @Transactional
    fun actualizarConsultorio(id: Long, consultorioDto: ConsultorioDto): ConsultorioDto? {
        val consultorioExistente = consultorioRepository.findById(id).orElse(null)
        return if (consultorioExistente != null && consultorioExistente.estado) {
            val consultorioActualizado = consultorioExistente.copy(
                nombre = consultorioDto.nombre,
                descripcion = consultorioDto.descripcion
            )
            consultorioRepository.save(consultorioActualizado).toDto()
        } else {
            null
        }
    }

    @Transactional
    fun eliminarLogico(id: Long): Boolean {
        val consultorio = consultorioRepository.findById(id).orElse(null)
        return if (consultorio != null && consultorio.estado) {
            consultorioRepository.save(consultorio.copy(estado = false))
            true
        } else {
            false
        }
    }
}

// Extensión para convertir Consultorio a ConsultorioDto
fun Consultorio.toDto(): ConsultorioDto {
    return ConsultorioDto(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        fechaRegistro = this.fechaRegistro,
        estado = this.estado
    )
}
