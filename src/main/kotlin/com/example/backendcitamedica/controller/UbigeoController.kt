package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.UbigeoDto
import com.example.backendcitamedica.entity.Ubigeo
import com.example.backendcitamedica.service.UbigeoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ubigeos")
class UbigeoController(private val ubigeoService: UbigeoService) {

    @GetMapping("/{id}")
    fun obtenerUbigeoPorId(@PathVariable id: Long): ResponseEntity<Ubigeo?> {
        val ubigeo = ubigeoService.obtenerUbigeoPorId(id)
        return if (ubigeo != null) {
            ResponseEntity.ok(ubigeo)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun obtenerTodos(): ResponseEntity<List<Ubigeo>> {
        val ubigeos = ubigeoService.obtenerTodos()
        return ResponseEntity.ok(ubigeos)
    }

    @PostMapping
    fun crearUbigeo(@RequestBody ubigeoDto: UbigeoDto): ResponseEntity<Ubigeo> {
        val nuevoUbigeo = ubigeoService.crearUbigeo(ubigeoDto)
        return ResponseEntity.ok(nuevoUbigeo)
    }

    @PutMapping("/{id}")
    fun actualizarUbigeo(@PathVariable id: Long, @RequestBody ubigeoDto: UbigeoDto): ResponseEntity<Ubigeo> {
        val ubigeoActualizado = ubigeoService.actualizarUbigeo(id, ubigeoDto)
        return if (ubigeoActualizado != null) {
            ResponseEntity.ok(ubigeoActualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarUbigeo(@PathVariable id: Long): ResponseEntity<Void> {
        return if (ubigeoService.eliminarUbigeo(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
