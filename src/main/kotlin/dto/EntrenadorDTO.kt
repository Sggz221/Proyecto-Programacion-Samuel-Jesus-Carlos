package org.example.dto

import java.time.LocalDate
import java.time.LocalDateTime

class EntrenadorDTO(
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fecha_nacimiento: String,
    val fecha_incorporacion: String,
    val salario: Double,
    val pais: String,
    val createdAt: String = LocalDate.now().toString(),
    val updatedAt: String = LocalDate.now().toString(),
    val isDeleted: Boolean = false,
    val especialidad: String
) {
}