package org.example

import org.example.models.*
import org.example.repositories.EquipoRepositoryImpl
import org.example.service.ServiceImpl
import org.example.storage.*

import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    println("Hello World!")

    val service = ServiceImpl()

    service.importFromFile("C:\\Users\\carlo\\Desktop\\proyecto\\Proyecto-Programacion-Samuel-Jesus-Carlos\\data\\personal.bin")
    service.getAll().forEach {println(it)}



}