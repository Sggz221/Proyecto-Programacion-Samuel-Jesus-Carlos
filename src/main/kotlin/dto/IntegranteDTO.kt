package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import java.time.LocalDateTime

@Serializable
@SerialName("personal")
data class IntegranteDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("nombre")
    @XmlElement
    val nombre: String,
    @SerialName("apellidos")
    @XmlElement
    val apellidos: String,
    @SerialName("fecha_nacimiento")
    @XmlElement
    val fecha_nacimiento: String,
    @SerialName("fecha_incorporacion")
    @XmlElement
    val fecha_incorporacion: String,
    @SerialName("salario")
    @XmlElement
    val salario: Double,
    @SerialName("pais")
    @XmlElement
    val pais: String,
    @SerialName("rol")
    @XmlElement
    val rol: String,
    @SerialName("especialidad")
    @XmlElement
    val especialidad: String?,
    @SerialName("posicion")
    @XmlElement
    val posicion: String?,
    @SerialName("dorsal")
    @XmlElement
    val dorsal: Int?,
    @SerialName("altura")
    @XmlElement
    val altura: Double?,
    @SerialName("peso")
    @XmlElement
    val peso: Double?,
    @SerialName("goles")
    @XmlElement
    val goles: Int?,
    @SerialName("partidos_jugados")
    @XmlElement
    val partidos_jugados: Int?
): java.io.Serializable