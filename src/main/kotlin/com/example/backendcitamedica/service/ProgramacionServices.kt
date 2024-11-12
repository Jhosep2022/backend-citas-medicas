package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.ProgramacionDto
import com.example.backendcitamedica.entity.Programacion
import com.example.backendcitamedica.repository.DoctorRepository
import com.example.backendcitamedica.repository.HorarioRepository
import com.example.backendcitamedica.repository.ProgramacionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ProgramacionService(
    private val programacionRepository: ProgramacionRepository,
    private val doctorRepository: DoctorRepository,
    private val horarioRepository: HorarioRepository
) {

    fun obtenerTodos(estado: Boolean): List<ProgramacionDto> {
        return programacionRepository.findByEstado(estado).map { it.toDto() }
    }

    fun obtenerPorId(id: Long): ProgramacionDto? {
        val programacion = programacionRepository.findById(id).orElse(null)
        return programacion?.takeIf { it.estado }?.toDto()
    }

    @Transactional
    fun crearProgramacion(programacionDto: ProgramacionDto): ProgramacionDto {
        val doctor = doctorRepository.findById(programacionDto.doctorId).orElseThrow { IllegalArgumentException("Doctor no encontrado") }
        val horario = horarioRepository.findById(programacionDto.horarioId).orElseThrow { IllegalArgumentException("Horario no encontrado") }

        val programacion = Programacion(
            doctor = doctor,
            horario = horario,
            fechaProgramacion = programacionDto.fechaProgramacion,
            horaInicio = programacionDto.horaInicio,
            horaFin = programacionDto.horaFin,
            descripcion = programacionDto.descripcion,
            estado = true // `estado` se establece automáticamente en `true`
        )
        return programacionRepository.save(programacion).toDto()
    }

    @Transactional
    fun actualizarProgramacion(id: Long, programacionDto: ProgramacionDto): ProgramacionDto? {
        val programacionExistente = programacionRepository.findById(id).orElse(null)
        return if (programacionExistente != null && programacionExistente.estado) {
            val doctor = doctorRepository.findById(programacionDto.doctorId).orElseThrow { IllegalArgumentException("Doctor no encontrado") }
            val horario = horarioRepository.findById(programacionDto.horarioId).orElseThrow { IllegalArgumentException("Horario no encontrado") }

            val programacionActualizada = programacionExistente.copy(
                doctor = doctor,
                horario = horario,
                fechaProgramacion = programacionDto.fechaProgramacion,
                horaInicio = programacionDto.horaInicio,
                horaFin = programacionDto.horaFin,
                descripcion = programacionDto.descripcion
            )
            programacionRepository.save(programacionActualizada).toDto()
        } else {
            null
        }
    }

    @Transactional
    fun eliminarLogico(id: Long): Boolean {
        val programacion = programacionRepository.findById(id).orElse(null)
        return if (programacion != null && programacion.estado) {
            programacionRepository.save(programacion.copy(estado = false))
            true
        } else {
            false
        }
    }
}


// Extensión para convertir Programacion a ProgramacionDto
fun Programacion.toDto(): ProgramacionDto {
    return ProgramacionDto(
        id = this.id,
        doctorId = this.doctor.id,
        horarioId = this.horario.id,
        fechaProgramacion = this.fechaProgramacion,
        horaInicio = this.horaInicio,
        horaFin = this.horaFin,
        descripcion = this.descripcion,
        estado = this.estado
    )
}
