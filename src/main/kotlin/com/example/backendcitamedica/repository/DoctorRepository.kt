package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository : JpaRepository<Doctor, Long>
