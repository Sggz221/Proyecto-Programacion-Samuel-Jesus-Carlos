package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@SerialName("personal")
data class IntegranteXmlDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("tipo")
    @XmlElement
    val rol: String,
    @SerialName("nombre")
    @XmlElement
    val nombre: String,
    @SerialName("apellidos")
    @XmlElement
    val apellidos: String,
    @SerialName("fechaNacimiento")
    @XmlElement
    val fecha_nacimiento: String,
    @SerialName("fechaIncorporacion")
    @XmlElement
    val fecha_incorporacion: String,
    @SerialName("salario")
    @XmlElement
    val salario: Double,
    @SerialName("pais")
    @XmlElement
    val pais: String,
    @SerialName("especialidad")
    @XmlElement
    val especialidad: String?,
    @SerialName("posicion")
    @XmlElement
    val posicion: String?,
    @SerialName("dorsal")
    @XmlElement
    val dorsal: String?,
    @SerialName("altura")
    @XmlElement
    val altura: String?,
    @SerialName("peso")
    @XmlElement
    val peso:String?,
    @SerialName("goles")
    @XmlElement
    val goles: String?,
    @SerialName("partidosJugados")
    @XmlElement
    val partidos_jugados: String?
): java.io.Serializable