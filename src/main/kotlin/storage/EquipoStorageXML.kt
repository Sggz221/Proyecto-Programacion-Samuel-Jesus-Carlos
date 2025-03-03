package org.example.storage

import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.XML
import org.example.dto.EquipoDTO
import org.example.exceptions.Exceptions
import org.example.mapper.toModel
import org.example.models.Integrante
import org.lighthousegames.logging.logging
import java.io.File

class EquipoStorageXML:EquipoStorage {
    private var logger = logging()

    override fun fileRead(file: File, format: String): List<Integrante> {
        logger.debug { "Leyendo fichero XML" }

        if (!file.canRead()) {
            throw Exceptions.StorageException("No se tienen permisos de lectura")
        }

        val xml = XML {}

        val xmlString = file.readText() // Leemos cada línea del fichero
        val listaIntegrantesDTO = xml.decodeFromString <EquipoDTO>(xmlString) // Convertimos el texto anteriormente leido a una lista de IntegrantesDTO
        val listaEquipo = listaIntegrantesDTO.equipo
        val listaIntegrantes = listaEquipo.mapNotNull { it.toModel() } // Mapeamos la lista de DTO para convertir cada elemento a un modelo segun convenga

        return  listaIntegrantes
    }


    override fun fileWrite(equipo: List<Integrante>, file: File, format: String) {
        TODO()
    }
}