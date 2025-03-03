package org.example.storage

import kotlinx.serialization.json.Json
import org.example.exceptions.Exceptions
import org.example.models.Integrante
import org.lighthousegames.logging.logging
import java.io.File

class EquipoStorageJSON: EquipoStorage {
    private var logger = logging()

    override fun fileRead(file: File, format: String): List<Integrante> {
        logger.debug { "Leyendo fichero JSON" }

        if (!file.canRead()) {
            throw Exceptions.StorageException("No se tienen permisos de lectura")
        }

        val json = Json { ignoreUnknownKeys = true }

    }

    override fun fileWrite(equipo: List<Integrante>, file: File, format: String) {
        TODO("Not yet implemented")
    }
}