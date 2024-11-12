package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.HorarioDto
import com.example.backendcitamedica.entity.Horario
import com.example.backendcitamedica.repository.HorarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HorarioService(private val horarioRepository: HorarioRepository) {

    fun obtenerTodos(estado: Boolean): List<HorarioDto> {
        return horarioRepository.findByEstado(estado).map { it.toDto() }
    }

    fun obtenerPorId(id: Long): HorarioDto? {
        val horario = horarioRepository.findById(id).orElse(null)
        return horario?.takeIf { it.estado }?.toDto()
    }

    @Transactional
    fun crearHorario(horarioDto: HorarioDto): HorarioDto {
        val horario = Horario(
            turno = horarioDto.turno,
            dia = horarioDto.dia,
            descripcion = horarioDto.descripcion,
            estado = true
        )
        return horarioRepository.save(horario).toDto()
    }

    @Transactional
    fun actualizarHorario(id: Long, horarioDto: HorarioDto): HorarioDto? {
        val horarioExistente = horarioRepository.findById(id).orElse(null)
        return if (horarioExistente != null && horarioExistente.estado) {
            val horarioActualizado = horarioExistente.copy(
                turno = horarioDto.turno,
                dia = horarioDto.dia,
                descripcion = horarioDto.descripcion
            )
            horarioRepository.save(horarioActualizado).toDto()
        } else {
            null
        }
    }

    @Transactional
    fun eliminarLogico(id: Long): Boolean {
        val horario = horarioRepository.findById(id).orElse(null)
        return if (horario != null && horario.estado) {
            horarioRepository.save(horario.copy(estado = false))
            true
        } else {
            false
        }
    }
}

// Extensión para convertir Horario a HorarioDto
fun Horario.toDto(): HorarioDto {
    return HorarioDto(
        id = this.id,
        turno = this.turno,
        dia = this.dia,
        descripcion = this.descripcion,
        estado = this.estado
    )
}
