package com.example.backendcitamedica.service

import com.example.backendcitamedica.dto.PersonaDto
import com.example.backendcitamedica.entity.*
import com.example.backendcitamedica.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PersonaService(
    private val personaRepository: PersonaRepository,
    private val pacienteRepository: PacienteRepository,
    private val doctorRepository: DoctorRepository,
    private val rolRepository: RolRepository,
    private val ubigeoService: UbigeoService,
    private val especialidadRepository: EspecialidadRepository,
    private val consultorioRepository: ConsultorioRepository
) {
    fun login(usuario: String, clave: String): PersonaDto? {
        val persona = personaRepository.findByUsuario(usuario)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        return if (persona.clave == clave && persona.estado) {
            persona.toDto() // Devuelve el DTO si las credenciales son correctas y la persona está activa
        } else {
            throw IllegalArgumentException("Credenciales inválidas")
        }
    }

    @Transactional
    fun registrarPersona(
        nombres: String,
        apellidos: String,
        dni: String,
        genero: String,
        direccion: String?,
        telefono: String?,
        email: String?,
        usuario: String?,
        clave: String?,
        rolId: Long,
        idDepartamento: Long,
        idProvincia: Long,
        idDistrito: Long
    ): Persona {
        val rol = rolRepository.findById(rolId).orElseThrow { IllegalArgumentException("Rol con ID $rolId no encontrado") }
        val ubigeo = ubigeoService.obtenerUbigeoPorRegion(idDepartamento, idProvincia, idDistrito)
            ?: throw IllegalArgumentException("Ubigeo no encontrado")

        val persona = Persona(
            rol = rol,
            ubigeo = ubigeo,
            nombres = nombres,
            apellidos = apellidos,
            dni = dni,
            genero = genero,
            direccion = direccion,
            telefono = telefono,
            email = email,
            usuario = usuario,
            clave = clave,
            fechaRegistro = LocalDateTime.now(),
            estado = true
        )

        return personaRepository.save(persona)
    }

    @Transactional
    fun registrarPaciente(
        personaId: Long,
        fechaNacimiento: LocalDateTime,
        edad: Int,
        tipo: String
    ): Paciente {
        val persona = personaRepository.findById(personaId).orElseThrow { IllegalArgumentException("Persona no encontrada") }

        val paciente = Paciente(
            persona = persona,
            fechaNacimiento = fechaNacimiento,
            edad = edad,
            tipo = tipo
        )
        return pacienteRepository.save(paciente)
    }

    @Transactional
    fun registrarDoctor(
        personaId: Long,
        idEspecialidad: Long,
        idConsultorio: Long,
        codCmp: String,
        cargo: String?,
        origen: String?
    ): Doctor {
        val persona = personaRepository.findById(personaId).orElseThrow { IllegalArgumentException("Persona no encontrada") }

        val especialidad = especialidadRepository.findById(idEspecialidad).orElseThrow { IllegalArgumentException("Especialidad no encontrada") }
        val consultorio = consultorioRepository.findById(idConsultorio).orElseThrow { IllegalArgumentException("Consultorio no encontrado") }

        val doctor = Doctor(
            persona = persona,
            especialidad = especialidad,
            consultorio = consultorio,
            codCmp = codCmp,
            cargo = cargo,
            origen = origen
        )
        return doctorRepository.save(doctor)
    }

    // Obtener todos los registros activos
    fun obtenerTodos(): List<PersonaDto> {
        return personaRepository.findAll()
            .filter { it.estado }
            .map { it.toDto() }
    }

    // Obtener un registro por ID si está activo
    fun obtenerPorId(id: Long): PersonaDto? {
        val persona = personaRepository.findById(id).orElse(null)
        return persona?.takeIf { it.estado }?.toDto()
    }

    // Obtener registros por tipo de rol
    fun obtenerPorRolId(rolId: Long): List<PersonaDto> {
        return personaRepository.findAll()
            .filter { it.estado && it.rol.id == rolId }
            .map { it.toDto() }
    }

    // Actualizar registro de persona
    @Transactional
    fun actualizarPersona(id: Long, personaDto: PersonaDto): PersonaDto? {
        val personaExistente = personaRepository.findById(id).orElse(null)
        return if (personaExistente != null && personaExistente.estado) {
            val personaActualizada = personaExistente.copy(
                nombres = personaDto.nombres,
                apellidos = personaDto.apellidos,
                dni = personaDto.dni,
                genero = personaDto.genero,
                direccion = personaDto.direccion,
                telefono = personaDto.telefono,
                email = personaDto.email,
                usuario = personaDto.usuario,
                clave = personaDto.clave
            )
            personaRepository.save(personaActualizada).toDto()
        } else {
            null
        }
    }

    // Borrado lógico
    @Transactional
    fun eliminarPersona(id: Long): Boolean {
        val persona = personaRepository.findById(id).orElse(null)
        return if (persona != null && persona.estado) {
            personaRepository.save(persona.copy(estado = false))
            true
        } else {
            false
        }
    }

}
