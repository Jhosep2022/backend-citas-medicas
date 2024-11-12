package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.RolDto
import com.example.backendcitamedica.entity.Rol
import com.example.backendcitamedica.repository.RolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class RolService(private val rolRepository: RolRepository) {

    fun obtenerTodos(): List<RolDto> {
        return rolRepository.findByEstado(true).map { it.toDto() }
    }

    fun obtenerPorId(id: Long): RolDto? {
        val rol = rolRepository.findById(id).orElse(null)
        return rol?.takeIf { it.estado }?.toDto()
    }

    @Transactional
    fun crearRol(rolDto: RolDto): RolDto {
        // Crea el rol con estado predeterminado en `true`
        val nuevoRol = Rol(
            nombre = rolDto.nombre,
            descripcion = rolDto.descripcion,
            fechaRegistro = LocalDateTime.now(),
            estado = true
        )
        return rolRepository.save(nuevoRol).toDto()
    }

    @Transactional
    fun actualizarRol(id: Long, rolDto: RolDto): RolDto? {
        val rolExistente = rolRepository.findById(id).orElse(null) ?: return null
        if (!rolExistente.estado) return null // No actualizar si el rol está inactivo

        val rolActualizado = rolExistente.copy(
            nombre = rolDto.nombre,
            descripcion = rolDto.descripcion,
            estado = rolDto.estado
        )

        return rolRepository.save(rolActualizado).toDto()
    }

    @Transactional
    fun eliminarRol(id: Long): Boolean {
        val rol = rolRepository.findById(id).orElse(null)
        return if (rol != null && rol.estado) {
            rolRepository.save(rol.copy(estado = false)) // Borrado lógico cambiando estado a false
            true
        } else {
            false
        }
    }
}

// Extensión para convertir Rol a RolDto
fun Rol.toDto(): RolDto {
    return RolDto(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        fechaRegistro = this.fechaRegistro,
        estado = this.estado
    )
}
