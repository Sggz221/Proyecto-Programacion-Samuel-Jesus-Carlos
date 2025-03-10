package org.example.storage

import org.example.dto.EntrenadorDTO
import org.example.dto.JugadorDTO
import org.example.exceptions.Exceptions
import org.example.mapper.toModel
import org.example.models.Integrante
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
                if(it[7] == "Jugador") {
                    JugadorDTO(
                        id = it[0].toLong(),
                        nombre = it[1],
                        apellidos = it[2],
                        fecha_nacimiento = it[3],
                        fecha_incorporacion = it[4],
                        salario = it[5].toDouble(),
                        pais = it[6],
                        posicion = it[9],
                        dorsal = it[10].toInt(),
                        altura = it[11].toDouble(),
                        peso = it[12].toDouble(),
                        goles = it[13].toInt(),
                        partidos_jugados = it[14].toInt(),
                    ).toModel()
                }
                else{
                    EntrenadorDTO(
                        id = it[0].toLong(),
                        nombre = it[1],
                        apellidos = it[2],
                        fecha_nacimiento = it[3],
                        fecha_incorporacion = it[4],
                        salario = it[5].toDouble(),
                        pais = it[6],
                        especialidad = it[8]
                    ).toModel()
                }
            }
    }

    override fun fileWrite(integrantes: List<Integrante>, file: File, format: String) {
        TODO("Not yet implemented")
    }


}