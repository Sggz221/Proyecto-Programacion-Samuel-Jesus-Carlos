package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("equipo")
data class EquipoDTO(
     val equipo: List<IntegranteXmlDTO>
     ): java.io.Serializable
