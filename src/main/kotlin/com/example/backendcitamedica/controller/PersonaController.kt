package com.example.backendcitamedica.controller

import com.example.backendcitamedica.dto.PersonaDto
import com.example.backendcitamedica.service.PersonaService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/personas")
class PersonaController(
    private val personaService: PersonaService
) {
    private val logger = LoggerFactory.getLogger(PersonaController::class.java)

    // Endpoint para que el administrador registre solo una persona
    @PostMapping("/registrar-persona")
    fun registrarPersona(@RequestBody request: RegistrarPersonaRequest): ResponseEntity<PersonaDto> {
        return try {
            val persona = personaService.registrarPersona(
                nombres = request.nombres,
                apellidos = request.apellidos,
                dni = request.dni,
                genero = request.genero,
                direccion = request.direccion,
                telefono = request.telefono,
                email = request.email,
                usuario = request.usuario,
                clave = request.clave,
                rolId = request.rolId,
                idDepartamento = request.idDepartamento,
                idProvincia = request.idProvincia,
                idDistrito = request.idDistrito
            )
            ResponseEntity.ok(persona.toDto())
        } catch (ex: IllegalArgumentException) {
            logger.error("Error en el registro de persona: ${ex.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    // Endpoint para que el paciente registre una persona y un paciente
    @PostMapping("/registrar-paciente")
    fun registrarPaciente(@RequestBody request: RegistrarPacienteRequest): ResponseEntity<PersonaDto> {
        return try {
            val persona = personaService.registrarPersona(
                nombres = request.nombres,
                apellidos = request.apellidos,
                dni = request.dni,
                genero = request.genero,
                direccion = request.direccion,
                telefono = request.telefono,
                email = request.email,
                usuario = request.usuario,
                clave = request.clave,
                rolId = request.rolId,
                idDepartamento = request.idDepartamento,
                idProvincia = request.idProvincia,
                idDistrito = request.idDistrito
            )
            personaService.registrarPaciente(
                personaId = persona.id,
                fechaNacimiento = request.fechaNacimiento,
                edad = request.edad,
                tipo = request.tipo
            )
            ResponseEntity.ok(persona.toDto())
        } catch (ex: IllegalArgumentException) {
            logger.error("Error en el registro de paciente: ${ex.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    // Endpoint para que el doctor registre una persona y un doctor
    @PostMapping("/registrar-doctor")
    fun registrarDoctor(@RequestBody request: RegistrarDoctorRequest): ResponseEntity<PersonaDto> {
        return try {
            val persona = personaService.registrarPersona(
                nombres = request.nombres,
                apellidos = request.apellidos,
                dni = request.dni,
                genero = request.genero,
                direccion = request.direccion,
                telefono = request.telefono,
                email = request.email,
                usuario = request.usuario,
                clave = request.clave,
                rolId = request.rolId,
                idDepartamento = request.idDepartamento,
                idProvincia = request.idProvincia,
                idDistrito = request.idDistrito
            )
            personaService.registrarDoctor(
                personaId = persona.id,
                idEspecialidad = request.idEspecialidad,
                idConsultorio = request.idConsultorio,
                codCmp = request.codCmp,
                cargo = request.cargo,
                origen = request.origen
            )
            ResponseEntity.ok(persona.toDto())
        } catch (ex: IllegalArgumentException) {
            logger.error("Error en el registro de doctor: ${ex.message}")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @GetMapping
    fun obtenerTodos(): ResponseEntity<List<PersonaDto>> {
        val personas = personaService.obtenerTodos()
        return ResponseEntity.ok(personas)
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<PersonaDto> {
        val persona = personaService.obtenerPorId(id)
        return persona?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/rol/{rolId}")
    fun obtenerPorRolId(@PathVariable rolId: Long): ResponseEntity<List<PersonaDto>> {
        val personas = personaService.obtenerPorRolId(rolId)
        return ResponseEntity.ok(personas)
    }

    @PutMapping("/{id}")
    fun actualizarPersona(@PathVariable id: Long, @RequestBody personaDto: PersonaDto): ResponseEntity<PersonaDto> {
        val personaActualizada = personaService.actualizarPersona(id, personaDto)
        return personaActualizada?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun eliminarPersona(@PathVariable id: Long): ResponseEntity<Void> {
        return if (personaService.eliminarPersona(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}
data class RegistrarPersonaRequest(
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val usuario: String?,
    val clave: String?,
    val rolId: Long,
    val idDepartamento: Long,
    val idProvincia: Long,
    val idDistrito: Long
)

data class RegistrarPacienteRequest(
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val usuario: String?,
    val clave: String?,
    val rolId: Long,
    val idDepartamento: Long,
    val idProvincia: Long,
    val idDistrito: Long,
    val fechaNacimiento: LocalDateTime,
    val edad: Int,
    val tipo: String
)

data class RegistrarDoctorRequest(
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val usuario: String?,
    val clave: String?,
    val rolId: Long,
    val idDepartamento: Long,
    val idProvincia: Long,
    val idDistrito: Long,
    val idEspecialidad: Long,
    val idConsultorio: Long,
    val codCmp: String,
    val cargo: String?,
    val origen: String?
)

