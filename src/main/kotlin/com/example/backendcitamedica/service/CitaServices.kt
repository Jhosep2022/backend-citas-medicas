package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.CitaDto
import com.example.backendcitamedica.entity.Cita
import com.example.backendcitamedica.repository.CitaRepository
import com.example.backendcitamedica.repository.PacienteRepository
import com.example.backendcitamedica.repository.ProgramacionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CitaService(
    private val citaRepository: CitaRepository,
    private val pacienteRepository: PacienteRepository,
    private val programacionRepository: ProgramacionRepository
) {

    fun obtenerTodas(estado: Boolean): List<CitaDto> {
        return citaRepository.findByEstado(estado).map { it.toDto() }
    }

    fun obtenerPorId(id: Long): CitaDto? {
        val cita = citaRepository.findById(id).orElse(null)
        return cita?.takeIf { it.estado }?.toDto()
    }

    @Transactional
    fun crearCita(citaDto: CitaDto): CitaDto {
        val paciente = pacienteRepository.findById(citaDto.pacienteId)
            .orElseThrow { IllegalArgumentException("Paciente no encontrado") }
        val programacion = programacionRepository.findById(citaDto.programacionId)
            .orElseThrow { IllegalArgumentException("Programación no encontrada") }

        val cita = Cita(
            paciente = paciente,
            programacion = programacion,
            fecha = citaDto.fecha,
            hora = citaDto.hora,
            enfermedad = citaDto.enfermedad,
            descripcion = citaDto.descripcion,
            historiaClinica = citaDto.historiaClinica,
            estado = true
        )
        return citaRepository.save(cita).toDto()
    }

    @Transactional
    fun actualizarCita(id: Long, citaDto: CitaDto): CitaDto? {
        val citaExistente = citaRepository.findById(id).orElse(null)
        return if (citaExistente != null && citaExistente.estado) {
            val paciente = pacienteRepository.findById(citaDto.pacienteId)
                .orElseThrow { IllegalArgumentException("Paciente no encontrado") }
            val programacion = programacionRepository.findById(citaDto.programacionId)
                .orElseThrow { IllegalArgumentException("Programación no encontrada") }

            val citaActualizada = citaExistente.copy(
                paciente = paciente,
                programacion = programacion,
                fecha = citaDto.fecha,
                hora = citaDto.hora,
                enfermedad = citaDto.enfermedad,
                descripcion = citaDto.descripcion,
                historiaClinica = citaDto.historiaClinica
            )
            citaRepository.save(citaActualizada).toDto()
        } else {
            null
        }
    }

    @Transactional
    fun eliminarLogico(id: Long): Boolean {
        val cita = citaRepository.findById(id).orElse(null)
        return if (cita != null && cita.estado) {
            citaRepository.save(cita.copy(estado = false))
            true
        } else {
            false
        }
    }
}

// Extensión para convertir Cita a CitaDto
fun Cita.toDto(): CitaDto {
    return CitaDto(
        id = this.id,
        pacienteId = this.paciente.id,
        programacionId = this.programacion.id,
        fecha = this.fecha,
        hora = this.hora,
        enfermedad = this.enfermedad,
        descripcion = this.descripcion,
        historiaClinica = this.historiaClinica,
        estado = this.estado
    )
}
