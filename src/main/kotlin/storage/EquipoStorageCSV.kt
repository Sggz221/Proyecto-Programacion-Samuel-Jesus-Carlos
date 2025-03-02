package org.example.storage

import org.example.exceptions.Exceptions
import org.example.models.Integrante
import org.lighthousegames.logging.logging
import java.io.File
import java.io.FileNotFoundException

class EquipoStorageCSV: EquipoStorage {
    private var logger = logging()

    override fun fileRead(file: File, format: String): List<Integrante> {
        logger.debug { "Leyendo fichero CSV" }
        if (!file.canRead()) {
            throw Exceptions.StorageException("No se tienen permisos de lectura")
        }

        return file.readLines()
            .drop (1)
            .map { it.split(",") }
            .map {
                if (it[7] == "Jugador"){

                } else {

                }
            }
    }

    override fun fileWrite(integrantes: List<Integrante>, file: File, format: String) {
        TODO("Not yet implemented")
    }


}