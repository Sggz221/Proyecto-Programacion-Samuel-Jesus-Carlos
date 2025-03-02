package org.example.models

import java.time.LocalDate
import java.time.LocalDateTime

abstract class Integrante (
    val id: Long = 0L,
    val nombre: String,
    val apellidos: String,
    val fecha_nacimiento: LocalDate,
    val fecha_incorporacion: LocalDate, //Localizar
    val salario: Double,
    val pais: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var isDeleted: Boolean = false,
){
}