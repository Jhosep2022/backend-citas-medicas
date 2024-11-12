package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.RolDto
import com.example.backendcitamedica.service.RolService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/roles")
class RolController(private val rolService: RolService) {

    private val logger = LoggerFactory.getLogger(RolController::class.java)

    @GetMapping
    fun obtenerTodos(): ResponseEntity<List<RolDto>> {
        logger.info("Obteniendo todos los roles")
        val roles = rolService.obtenerTodos()
        return ResponseEntity.ok(roles)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<RolDto> {
        logger.info("Obteniendo rol con id: $id")
        val rol = rolService.obtenerPorId(id)
        return if (rol != null) {
            ResponseEntity.ok(rol)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun crearRol(@RequestBody rolDto: RolDto): ResponseEntity<RolDto> {
        logger.info("Creando nuevo rol: ${rolDto.nombre}")
        val nuevoRol = rolService.crearRol(rolDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol)
    }

    @PutMapping("/{id}")
    fun actualizarRol(@PathVariable id: Long, @RequestBody rolDto: RolDto): ResponseEntity<RolDto> {
        logger.info("Actualizando rol con id: $id")
        val rolActualizado = rolService.actualizarRol(id, rolDto)
        return if (rolActualizado != null) {
            ResponseEntity.ok(rolActualizado)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarRol(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando rol con id: $id")
        return if (rolService.eliminarRol(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
