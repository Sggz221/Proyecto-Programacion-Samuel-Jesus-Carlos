package org.example.mapper


import org.example.dto.EntrenadorDTO
import org.example.models.Entrenador
import org.example.Especialidad
import java.time.LocalDate
import java.time.LocalDateTime

fun Entrenador.toDto (): EntrenadorDTO {
    return EntrenadorDTO(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = fecha_nacimiento.toString(),
        fecha_incorporacion = fecha_incorporacion.toString(),
        salario = salario,
        pais = pais,
        especialidad = especialidad.toString()

    )
}
fun EntrenadorDTO.toModel(): Entrenador {
    return Entrenador(
        id = id,
        nombre = nombre,
        apellidos = apellidos,
        fecha_nacimiento = LocalDate.parse(fecha_nacimiento),
        fecha_incorporacion = LocalDate.parse(fecha_incorporacion),
        salario = salario,
        pais = pais,
        especialidad = Especialidad.valueOf(especialidad)
    )
}