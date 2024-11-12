package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.HorarioDto
import com.example.backendcitamedica.service.HorarioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/horarios")
class HorarioController(private val horarioService: HorarioService) {

    private val logger = LoggerFactory.getLogger(HorarioController::class.java)

    @GetMapping
    fun obtenerTodos(@RequestParam estado: Boolean): ResponseEntity<List<HorarioDto>> {
        logger.info("Obteniendo todos los horarios con estado: $estado")
        val horarios = horarioService.obtenerTodos(estado)
        return ResponseEntity.ok(horarios)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<HorarioDto> {
        logger.info("Obteniendo horario con id: $id")
        val horario = horarioService.obtenerPorId(id)
        return horario?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun crearHorario(@RequestBody horarioDto: HorarioDto): ResponseEntity<HorarioDto> {
        logger.info("Creando nuevo horario: ${horarioDto.turno} - ${horarioDto.dia}")
        val nuevoHorario = horarioService.crearHorario(horarioDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario)
    }

    @PutMapping("/{id}")
    fun actualizarHorario(@PathVariable id: Long, @RequestBody horarioDto: HorarioDto): ResponseEntity<HorarioDto> {
        logger.info("Actualizando horario con id: $id")
        val horarioActualizado = horarioService.actualizarHorario(id, horarioDto)
        return horarioActualizado?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun eliminarHorario(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando lógicamente horario con id: $id")
        return if (horarioService.eliminarLogico(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
