package org.example.repositories

import org.example.extensions.copy
import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import org.example.validator.IntegranteValidator
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

class EquipoRepositoryImpl: EquipoRepository<Long, Integrante> {
    private val logger = logging()
    private val equipo = mutableMapOf<Long, Integrante>()
    private var nextId = 1L
    private var validator = IntegranteValidator()

    private fun generateId(): Long {
        return nextId++
    }
    override fun save(entity: Integrante): Integrante {
        logger.debug { "Guardando integrante del equipo..." }
        val timeStamp = LocalDateTime.now()
        val id = generateId()

        validator.validar(entity)

        if (entity is Jugador) {
            equipo[id] = entity.copy(id, timeStamp)
        }
        else if (entity is Entrenador) {
            equipo[id] = entity.copy(id, timeStamp)
        }
        return equipo[id]!!
    }

    override fun delete(id: Long): Integrante? {
        logger.debug { "Borrando integrante del equipo con ID: $id" }
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
        logger.debug { "Obteniendo integrante del equipo con ID: $id" }
        return equipo[id]
    }

    override fun deleteLogical(id: Long, entity: Integrante): Integrante? {
        logger.debug{"Borrando lógicamente integrante del equipo con ID: $id"}
        equipo[id] ?: return null
        val timeStamp = LocalDateTime.now()

        equipo[id] = entity.apply {
            updatedAt = timeStamp
            isDeleted = true
        }
        return equipo[id]
    }
}