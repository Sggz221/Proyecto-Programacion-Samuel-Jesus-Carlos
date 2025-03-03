package org.example.storage

import org.example.dto.IntegranteDTO
import org.example.exceptions.Exceptions
import org.example.mapper.toDto
import org.example.mapper.toModel
import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import org.lighthousegames.logging.logging
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDateTime

class EquipoStorageCSV: EquipoStorage {
    private var logger = logging()

    override fun fileRead(file: File, format: String): List<Integrante> {
        logger.debug { "Leyendo fichero CSV" }
        if (!file.canRead()) {
            throw Exceptions.StorageException("No se tienen permisos de lectura")
        }

        return file.readLines()
            .drop(1)
            .map{it.split(",")}
            .map{
                IntegranteDTO(
                    id = it[0].toLong(),
                    nombre = it[1],
                    apellidos = it[2],
                    fecha_nacimiento = it[3],
                    fecha_incorporacion = it[4],
                    salario = it[5].toDouble(),
                    pais = it[6],
                    rol = it[7],
                    especialidad = it[8],
                    posicion = it[9],
                    dorsal = it[10].toIntOrNull(),
                    altura = it[11].toDoubleOrNull(),
                    peso = it[12].toDoubleOrNull(),
                    goles = it[13].toIntOrNull(),
                    partidos_jugados = it[14].toIntOrNull(),
                ).toModel()
            }
    }

    override fun fileWrite(equipo: List<Integrante>, file: File, format: String) {
        logger.debug { "Escribiendo integrantes del equipo en fichero CSV" }

        if (!file.parentFile.exists() || !file.parentFile.isDirectory) {
            throw Exceptions.StorageException("El directorio padre del fichero no existe")
        }

        file.writeText("id,nombre,apellidos,fecha_nacimiento,fecha_incorporacion,salario,pais,rol,especialidad,posicion,dorsal,altura,peso,goles,partidos_jugados\n")

        equipo.map {
            if (it is Jugador) {
                it.toDto()
                file.appendText("${it.id},${it.nombre},${it.apellidos},${it.fecha_nacimiento},${it.fecha_incorporacion},${it.salario},${it.pais},Jugador,,${it.posicion},${it.dorsal},${it.altura},${it.peso},${it.goles},${it.partidos_jugados}\n")
            }
            if (it is Entrenador) {
                it.toDto()
                file.appendText("${it.id},${it.nombre},${it.apellidos},${it.fecha_nacimiento},${it.fecha_incorporacion},${it.salario},${it.pais},Entrenador,${it.especialidad},,,,,,\n")
            }
        }



}}