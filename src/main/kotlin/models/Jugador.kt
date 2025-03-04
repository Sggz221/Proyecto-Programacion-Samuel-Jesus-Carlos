package org.example.models

import java.time.LocalDate
import java.time.LocalDateTime

class Jugador(
    id: Long = 0L,
    nombre: String,
    apellidos: String,
    fecha_nacimiento: LocalDate,
    fecha_incorporacion: LocalDate,
    salario: Double,
    pais: String,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),
    isDeleted: Boolean = false,
    val posicion: Posicion,
    val dorsal: Int,
    val altura: Double,
    val peso: Double,
    val goles: Int,
    val partidos_jugados: Int
): Integrante(id = id, nombre = nombre, apellidos = apellidos, fecha_nacimiento = fecha_nacimiento, fecha_incorporacion = fecha_incorporacion, salario = salario, pais = pais, createdAt = createdAt,updatedAt = updatedAt, isDeleted = isDeleted) {
    override fun toString(): String {
        return "Jugador(id= $id, nombre= $nombre, apellidos= $apellidos, fecha_nacimiento= $fecha_nacimiento, fecha_incorporacion= $fecha_incorporacion, salario= $salario, pais = $pais, createdAt= $createdAt, updatedAt= $updatedAt, isDeleted= $isDeleted, posicion= $posicion, dorsal= $dorsal, altura= $altura, peso= $peso, goles= $goles, partidos_jugados= $partidos_jugados)"
    }
}