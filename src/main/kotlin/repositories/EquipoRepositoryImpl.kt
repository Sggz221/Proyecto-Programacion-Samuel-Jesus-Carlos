package org.example.repositories

import org.example.models.Integrante
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class EquipoRepositoryImpl: EquipoRepository<Long, Integrante> {
    private val logger = logging()
    private val equipo = mutableMapOf<Long, Integrante>()
    private var nextId = 1L

    private fun generateId(): Long {
        return nextId++
    }
    override fun save(id: Long, entity: Integrante): Integrante {
        logger.debug { "Guardando integrante del equipo con ID: $id" }
        val timeStamp = LocalDateTime.now()
        val id = generateId()

    }

    override fun delete(id: Long): Integrante? {
        logger.debug { "Borrando integrante del equipo ocn ID: $id" }
        return equipo.remove(id)
    }

    override fun update(id: Long, entity: Integrante): Integrante? {
        logger.debug{"Actualizando integrante del equipo con ID: $id"}
        equipo[id] ?: return null
        val timeStamp = LocalDateTime.now()

        equipo[id] = entity.apply {
            updatedAt = timeStamp
        }
        return equipo[id]
    }

    override fun getAll(): List<Integrante> {
        logger.debug { "Obteniendo todos los integrantes" }
        return equipo.values.toList()
    }

    override fun getById(id: Long): Integrante? {
        logger.debug { "Obteniendo itegrante del equipo con ID: $id" }
        return equipo[id]
    }

    override fun deleteLogical(id: Long, entity: Integrante): Integrante? {
        logger.debug{"Actualizando integrante del equipo con ID: $id"}
        equipo[id] ?: return null
        val timeStamp = LocalDateTime.now()

        equipo[id] = entity.apply {
            updatedAt = timeStamp
            isDeleted = true
        }
        return equipo[id]
    }
}