package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.ConsultorioDto
import com.example.backendcitamedica.service.ConsultorioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/consultorios")
class ConsultorioController(private val consultorioService: ConsultorioService) {

    private val logger = LoggerFactory.getLogger(ConsultorioController::class.java)

    @GetMapping
    fun obtenerTodos(@RequestParam estado: Boolean): ResponseEntity<List<ConsultorioDto>> {
        logger.info("Obteniendo todos los consultorios con estado: $estado")
        val consultorios = consultorioService.obtenerTodos(estado)
        return ResponseEntity.ok(consultorios)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<ConsultorioDto> {
        logger.info("Obteniendo consultorio con id: $id")
        val consultorio = consultorioService.obtenerPorId(id)
        return consultorio?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun crearConsultorio(@RequestBody consultorioDto: ConsultorioDto): ResponseEntity<ConsultorioDto> {
        logger.info("Creando nuevo consultorio: ${consultorioDto.nombre}")
        val nuevoConsultorio = consultorioService.crearConsultorio(consultorioDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConsultorio)
    }

    @PutMapping("/{id}")
    fun actualizarConsultorio(@PathVariable id: Long, @RequestBody consultorioDto: ConsultorioDto): ResponseEntity<ConsultorioDto> {
        logger.info("Actualizando consultorio con id: $id")
        val consultorioActualizado = consultorioService.actualizarConsultorio(id, consultorioDto)
        return consultorioActualizado?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun eliminarConsultorio(@PathVariable id: Long): ResponseEntity<Void> {
        logger.info("Eliminando lógicamente consultorio con id: $id")
        return if (consultorioService.eliminarLogico(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
