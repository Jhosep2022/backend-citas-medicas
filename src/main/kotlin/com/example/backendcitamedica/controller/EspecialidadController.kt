package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.EspecialidadDto
import com.example.backendcitamedica.service.EspecialidadService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/especialidades")
class EspecialidadController(private val especialidadService: EspecialidadService) {

    private val logger = LoggerFactory.getLogger(EspecialidadController::class.java)

    @GetMapping
    fun obtenerTodas(): ResponseEntity<List<EspecialidadDto>> {
        logger.info("Obteniendo todas las especialidades activas")
        val especialidades = especialidadService.obtenerTodas()
        return ResponseEntity.ok(especialidades)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<EspecialidadDto> {
        logger.info("Obteniendo especialidad con id: $id")
        val especialidad = especialidadService.obtenerPorId(id)
        return especialidad?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun crearEspecialidad(@RequestBody especialidadDto: EspecialidadDto): ResponseEntity<EspecialidadDto> {
        logger.info("Creando nueva especialidad: ${especialidadDto.nombre}")
        val nuevaEspecialidad = especialidadService.crearEspecialidad(especialidadDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecialidad)
    }

    @PutMapping("/{id}")
    fun actualizarEspecialidad(@PathVariable id: Long, @RequestBody especialidadDto: EspecialidadDto): ResponseEntity<EspecialidadDto> {
        logger.info("Actualizando especialidad con id: $id")
        val especialidadActualizada = especialidadService.actualizarEspecialidad(id, especialidadDto)
        return especialidadActualizada?.let { ResponseEntity.ok(it) } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun eliminarEspecialidad(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando lógicamente especialidad con id: $id")
        return if (especialidadService.eliminarEspecialidad(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
