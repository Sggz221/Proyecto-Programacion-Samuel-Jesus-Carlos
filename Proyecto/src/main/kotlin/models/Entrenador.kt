package org.example.models

import java.time.LocalDateTime

class Entrenador(
    id: Long,
    nombre: String,
    apellidos: String,
    fecha_nacimiento: String, // Localizar
    fecha_incorporacion: String, //Localizar
    salario: Double,
    pais: String,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,
    isDeleted: Boolean,
    val especialidad: Especialidad
): Integrante(id = id, nombre = nombre, apellidos = apellidos, fecha_nacimiento = fecha_nacimiento, fecha_incorporacion = fecha_incorporacion, salario = salario, pais = pais, createdAt = createdAt, updatedAt = updatedAt, isDeleted = isDeleted) {
}