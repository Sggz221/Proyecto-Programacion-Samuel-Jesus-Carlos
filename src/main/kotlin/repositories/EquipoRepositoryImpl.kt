package org.example.repositories

import org.example.extensions.copy
import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import org.example.validator.IntegranteValidator
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

/**
 * Clase que implementa la interfaz [EquipoRepository] para gestionar un equpo de futbol en memoria con una serie de [Integrante] que pueden ser [Jugador] o [Entrenador]
 */
class EquipoRepositoryImpl: EquipoRepository<Long, Integrante> {
    private val logger = logging()
    private val equipo = mutableMapOf<Long, Integrante>()
    private var nextId = 1L
    private var validator = IntegranteValidator()

    /**
     * Genera un identificador [Long] para la clave de los elementos del mapa que representa el equipo en memoria
     * @return [Long]
     */
    private fun generateId(): Long {
        return nextId++
    }

    /**
     * Funcion que guarda un [Integrante] en una posicion libre del mapa que representa al equipo
     * @param entity [Integrante] El integrante que se intenta guardar en el mapa
     * @return [Integrante]
     */
    override fun save(entity: Integrante): Integrante {
        logger.debug { "Guardando integrante del equipo..." }
        val timeStamp = LocalDateTime.now()
        val id = generateId()

        validator.validar(entity)

        if (entity is Jugador) {
            equipo[id] = entity.copy(newId = id, timeStamp = timeStamp)
        }
        else if (entity is Entrenador) {

            equipo[id] = entity.copy(newId = id, timeStamp = timeStamp)
        }
        return equipo[id]!!
    }

    /**
     * Elimina un [Integrante] del mapa que representa al equipoen base a un ID
     * @param id [Long] el identificador que representa el objeto que se quiere borrar del mapa
     * @return [Integrante] o nulo si no encuentra el objeto
     */
    override fun delete(id: Long): Integrante? {
        logger.debug { "Borrando integrante del equipo con ID: $id" }
        return equipo.remove(id)
    }

    /**
     * Funcion que actualiza un integrante
     *  @param id [Long] el identificador que representa el objeto que se quiere actualizar
     *  @param entity [Integrante] El integrante que se quiere actualizar
     * @return [Integrante] o nulo si no encuentra el objeto
     */
    override fun update(id: Long, entity: Integrante): Integrante? {
        logger.debug{"Actualizando integrante del equipo con ID: $id"}
        equipo[id] ?: return null
        val timeStamp = LocalDateTime.now()

        equipo[id] = entity.apply {
            updatedAt = timeStamp
        }
        return equipo[id]
    }

    /**
     * Obtiene todos los [Integrante] del mapa y lo convierte en una [List] de [Integrante]
     * @return [List] de [Integrante]
     */
    override fun getAll(): List<Integrante> {
        logger.debug { "Obteniendo todos los integrantes" }
        return equipo.values.toList()
    }

    /**
     * Obtiene un [Integrante] en base a un id
     * @param id [Long] Identificador que representa el objeto
     * @return [Integrante] o nulo si no encuentra el objeto
     */
    override fun getById(id: Long): Integrante? {
        logger.debug { "Obteniendo integrante del equipo con ID: $id" }
        return equipo[id]
    }

    /**
     * Funcion que borra logicamente un integrante. Su funcionamiento es esencialmente igual al de [update] pero para cambiar el campo de [Integrante.isDeleted] a [true]
     * @return [Integrante] o nulo si no encuentra el objeto
     */
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