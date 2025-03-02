package org.example.models

import org.example.models.interfaces.Especialidad
import java.time.LocalDate
import java.time.LocalDateTime

class Entrenador(
    id: Long,
    nombre: String,
    apellidos: String,
    fecha_nacimiento: LocalDate,
    fecha_incorporacion: LocalDate,
    salario: Double,
    pais: String,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,
    isDeleted: Boolean,
    val especialidad: Especialidad
): Integrante(id = id, nombre = nombre, apellidos = apellidos, fecha_nacimiento = fecha_nacimiento, fecha_incorporacion = fecha_incorporacion, salario = salario, pais = pais, createdAt = createdAt, updatedAt = updatedAt, isDeleted = isDeleted) {
}