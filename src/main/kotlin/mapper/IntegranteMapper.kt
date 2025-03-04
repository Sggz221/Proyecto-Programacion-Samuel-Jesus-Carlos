package org.example.mapper

import org.example.models.Especialidad
import org.example.models.Posicion
import org.example.dto.IntegranteDTO
import org.example.dto.IntegranteXmlDTO
import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import java.time.LocalDate

fun IntegranteDTO.toModel(): Integrante {
    return if(this.rol == "Jugador"){
        Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
            fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
            salario = salario,
            pais = pais,
            posicion = Posicion.valueOf(posicion!!),
            dorsal = dorsal!!,
            altura = altura!!,
            peso = peso!!,
            goles = goles!!,
            partidos_jugados = partidos_jugados!!
        )
    }
    else{
        Entrenador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
            fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
            salario = salario,
            pais = pais,
            especialidad = Especialidad.valueOf(especialidad!!)
        )
    }
}

fun IntegranteXmlDTO.toModel(): Integrante{
    return if(this.rol == "Jugador"){
        Jugador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
            fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
            salario = salario,
            pais = pais,
            posicion = Posicion.valueOf(posicion!!),
            dorsal = dorsal!!.toInt(),
            altura = altura!!.toDouble(),
            peso = peso!!.toDouble(),
            goles = goles!!.toInt(),
            partidos_jugados = partidos_jugados!!.toInt()
        )
    }
    else{
        Entrenador(
            id = id,
            nombre = nombre,
            apellidos = apellidos,
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
            fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
            salario = salario,
            pais = pais,
            especialidad = Especialidad.valueOf(especialidad!!)
        )
    }
}

fun Entrenador.toXmlDTO (): IntegranteXmlDTO {
    return IntegranteXmlDTO(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fecha_nacimiento.toString(),
        fecha_incorporacion = fecha_incorporacion.toString(),
        salario = salario,
        pais = pais,
        especialidad = especialidad.toString(),
        rol = "Entrenador",
        posicion = "",
        dorsal = "",
        altura = "",
        peso = "",
        goles = "",
        partidos_jugados = ""
    )
}

fun Jugador.toXmlDTO (): IntegranteXmlDTO {
    return IntegranteXmlDTO(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fecha_nacimiento.toString(),
        fecha_incorporacion = fecha_incorporacion.toString(),
        salario = salario,
        pais = pais,
        especialidad = "",
        rol = "Jugador",
        posicion = posicion.toString(),
        dorsal = dorsal.toString(),
        altura = altura.toString(),
        peso = peso.toString(),
        goles = goles.toString(),
        partidos_jugados = partidos_jugados.toString()
    )
}

fun Entrenador.toDto (): IntegranteDTO {
    return IntegranteDTO(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fecha_nacimiento.toString(),
        fecha_incorporacion = fecha_incorporacion.toString(),
        salario = salario,
        pais = pais,
        especialidad = especialidad.toString(),
        rol = "Entrenador",
        posicion = "",
        dorsal = null,
        altura = null,
        peso = null,
        goles = null,
        partidos_jugados = null
    )
}

fun Jugador.toDto (): IntegranteDTO {
    return IntegranteDTO(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fecha_nacimiento.toString(),
        fecha_incorporacion = fecha_incorporacion.toString(),
        salario = salario,
        pais = pais,
        rol = "Jugador",
        especialidad = "",
        posicion = posicion.toString(),
        dorsal = dorsal,
        altura = altura,
        peso = peso,
        goles = goles,
        partidos_jugados = partidos_jugados
    )
}