package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.CitaDto
import com.example.backendcitamedica.service.CitaService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/citas")
class CitaController(private val citaService: CitaService) {

    private val logger = LoggerFactory.getLogger(CitaController::class.java)

    @GetMapping
    fun obtenerTodas(@RequestParam estado: Boolean): ResponseEntity<List<CitaDto>> {
        logger.info("Obteniendo todas las citas con estado: $estado")
        val citas = citaService.obtenerTodas(estado)
        return ResponseEntity.ok(citas)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<CitaDto> {
        logger.info("Obteniendo cita con id: $id")
        val cita = citaService.obtenerPorId(id)
        return cita?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun crearCita(@RequestBody citaDto: CitaDto): ResponseEntity<CitaDto> {
        logger.info("Creando nueva cita para el paciente ${citaDto.pacienteId}")
        val nuevaCita = citaService.crearCita(citaDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita)
    }

    @PutMapping("/{id}")
    fun actualizarCita(@PathVariable id: Long, @RequestBody citaDto: CitaDto): ResponseEntity<CitaDto> {
        logger.info("Actualizando cita con id: $id")
        val citaActualizada = citaService.actualizarCita(id, citaDto)
        return citaActualizada?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun eliminarCita(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando lógicamente cita con id: $id")
        return if (citaService.eliminarLogico(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
