package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class EntrenadorDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("nombre")
    val nombre: String,
    @SerialName("apellidos")
    val apellidos: String,
    @SerialName("fecha_nacimiento")
    val fecha_nacimiento: String,
    @SerialName("fecha_incorporacion")
    val fecha_incorporacion: String,
    @SerialName("salario")
    val salario: Double,
    @SerialName("pais")
    val pais: String,
    val createdAt: String = LocalDate.now().toString(),
    val updatedAt: String = LocalDate.now().toString(),
    val isDeleted: Boolean = false,
    @SerialName("especialidad")
    val especialidad: String
) {
}