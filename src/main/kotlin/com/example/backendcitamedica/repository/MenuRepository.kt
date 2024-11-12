package com.example.backendcitamedica.repository

import com.example.backendcitamedica.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<Menu, Long>
