package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.EspecialidadDto
import com.example.backendcitamedica.entity.Especialidad
import com.example.backendcitamedica.repository.EspecialidadRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class EspecialidadService(private val especialidadRepository: EspecialidadRepository) {

    // Obtener todas las especialidades activas
    fun obtenerTodas(): List<EspecialidadDto> {
        return especialidadRepository.findByEstadoTrue().map { it.toDto() }
    }

    // Obtener una especialidad por ID solo si está activa
    fun obtenerPorId(id: Long): EspecialidadDto? {
        val especialidad = especialidadRepository.findByIdAndEstadoTrue(id)
        return especialidad?.toDto()
    }

    // Crear una nueva especialidad
    @Transactional
    fun crearEspecialidad(especialidadDto: EspecialidadDto): EspecialidadDto {
        val especialidad = Especialidad(
            nombre = especialidadDto.nombre,
            descripcion = especialidadDto.descripcion,
            fechaRegistro = LocalDateTime.now(),
            estado = true
        )
        return especialidadRepository.save(especialidad).toDto()
    }

    // Actualizar una especialidad existente
    @Transactional
    fun actualizarEspecialidad(id: Long, especialidadDto: EspecialidadDto): EspecialidadDto? {
        val especialidadExistente = especialidadRepository.findByIdAndEstadoTrue(id)
        return if (especialidadExistente != null) {
            val especialidadActualizada = especialidadExistente.copy(
                nombre = especialidadDto.nombre,
                descripcion = especialidadDto.descripcion
            )
            especialidadRepository.save(especialidadActualizada).toDto()
        } else {
            null
        }
    }

    // Borrado lógico de una especialidad
    @Transactional
    fun eliminarEspecialidad(id: Long): Boolean {
        val especialidad = especialidadRepository.findByIdAndEstadoTrue(id)
        return if (especialidad != null) {
            especialidadRepository.save(especialidad.copy(estado = false))
            true
        } else {
            false
        }
    }
}

// Extensión para convertir Especialidad a EspecialidadDto
fun Especialidad.toDto(): EspecialidadDto {
    return EspecialidadDto(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        fechaRegistro = this.fechaRegistro,
        estado = this.estado
    )
}
