package org.example

import org.example.configuration.Confifuration
import org.example.models.*
import org.example.repositories.EquipoRepositoryImpl
import org.example.service.ServiceImpl
import org.example.storage.*
import org.example.view.Menu

import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    val menu = Menu()
    menu.menu()
}