package org.example.storage

import kotlinx.serialization.json.Json
import org.example.dto.IntegranteDTO
import org.example.exceptions.Exceptions
import org.example.mapper.toDto
import org.example.mapper.toModel
import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
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

        val jsonString = file.readText() // Leemos cada linea del fichero
        val listaIntegrantesDTO: List<IntegranteDTO> = json.decodeFromString(jsonString) // Convertimos el texto anteriormente leido a una lsita de IntegrantesDTO
        val listaIntegrantes = listaIntegrantesDTO.map { it.toModel() } // Mapeamos la lista de DTO para convertir cada elemento a un modelo segun convenga

        return  listaIntegrantes
    }

    override fun fileWrite(equipo: List<Integrante>, file: File, format: String) {
        logger.debug { "Escribiendo integrantes del equipo en fichero JSON" }

        if (!file.parentFile.exists() || !file.parentFile.isDirectory) {
            throw Exceptions.StorageException("El directorio padre del fichero no existe")
        }

        val json = Json { ignoreUnknownKeys = true ; prettyPrint = true }

        val listaIntegrantesDTO: List<IntegranteDTO> = equipo.mapNotNull {
            when (it) {
                is Jugador -> {it.toDto()}
                is Entrenador -> {it.toDto()}
                else -> null
            }
        }  // Convertir integrantes a DTOs segun el modelo
        val jsonString: String = json.encodeToString(listaIntegrantesDTO)  // Serializar a JSON
        file.writeText(jsonString)  // Guardar en el archivo

        // file.writeText(json.encodeToString<List<IntegranteDTO>>(equipo.map { it.toDto() }))
    }
}