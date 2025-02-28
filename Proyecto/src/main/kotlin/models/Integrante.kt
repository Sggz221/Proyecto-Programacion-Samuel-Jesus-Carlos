package org.example.models

import java.time.LocalDateTime

abstract class Integrante (
    val id: Long,
    val nombre: String,
    val apellidos: String,
    val fecha_nacimiento: String, // Localizar
    val fecha_incorporacion: String, //Localizar
    val salario: Double,
    val pais: String,
    val rol: String, // Arreglar
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var isDeleted: Boolean = false,
){
}