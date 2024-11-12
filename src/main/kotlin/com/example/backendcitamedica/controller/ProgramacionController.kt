package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.ProgramacionDto
import com.example.backendcitamedica.service.ProgramacionService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/programaciones")
class ProgramacionController(private val programacionService: ProgramacionService) {

    private val logger = LoggerFactory.getLogger(ProgramacionController::class.java)

    @GetMapping
    fun obtenerTodos(@RequestParam estado: Boolean): ResponseEntity<List<ProgramacionDto>> {
        logger.info("Obteniendo todas las programaciones con estado: $estado")
        val programaciones = programacionService.obtenerTodos(estado)
        return ResponseEntity.ok(programaciones)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<ProgramacionDto> {
        logger.info("Obteniendo programación con id: $id")
        val programacion = programacionService.obtenerPorId(id)
        return programacion?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun crearProgramacion(@RequestBody programacionDto: ProgramacionDto): ResponseEntity<ProgramacionDto> {
        logger.info("Creando nueva programación para el doctor ${programacionDto.doctorId}")
        val nuevaProgramacion = programacionService.crearProgramacion(programacionDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaProgramacion)
    }

    @PutMapping("/{id}")
    fun actualizarProgramacion(@PathVariable id: Long, @RequestBody programacionDto: ProgramacionDto): ResponseEntity<ProgramacionDto> {
        logger.info("Actualizando programación con id: $id")
        val programacionActualizada = programacionService.actualizarProgramacion(id, programacionDto)
        return programacionActualizada?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun eliminarProgramacion(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando lógicamente programación con id: $id")
        return if (programacionService.eliminarLogico(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
