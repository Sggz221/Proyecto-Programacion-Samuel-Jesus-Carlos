package org.example.Cache

import org.lighthousegames.logging.logging

/**
 * Clase que representa la cache. Es un [LinkedHashMap] que sirve como un paso intermedio antes de buscar un elemento en el repositorio para acelerar la velocidad de consultas a objetos que sean consultados a menudo
 * @property capacidad [Int] Capacidad maxma de la Cache
 */
class CacheImpl<K, V>(private val capacidad: Int) : Cache<K, V> {
    private val logger = logging()

    /**
     * Filosofia LRU par la cache (Last Recently Used) Obtiene el elemento menos usado en la cache y lo elimina para dar paso a nuevos archivos
     */
    private val cacheLRU: LinkedHashMap<K, V> = object: LinkedHashMap<K, V>(capacidad, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            logger.debug { "Alcanzado el tamaño máximo: eliminando elemento más antiguo de la cache" }
            return size > capacidad
        }
    }
    /**
     * Devuelve un objeto de la cache en base a una clave
     * @return El objeto o un nulo si no lo encuentra
     */
    override fun get(key: K): V? {
        logger.debug{"Obteniendo elemento en la cache con clave $key"}
        return cacheLRU[key]
    }

    /**
     * Inserta un elemento en la Cache
     * @param key Clave del elemento
     * @param value El elemento del mapa
     */
    override fun put(key: K, value: V): V? {
        logger.debug { "Guardando elemento en la cache con clave $key" }
        cacheLRU[key] = value
        return value
    }

    /**
     * Elimina un elemento en la Cache
     * @param key Clave del elemento
     * @return El objeto o un nulo si no lo encuentra
     */
    override fun remove(key: K): V? {
        logger.debug { "Eliminando elemento en la cache con clave $key" }
        return cacheLRU.remove(key)
    }

    /**
     * Limpia la cache eliminando su contenido
     */
    override fun clear() {
        logger.debug { "Limpiando la cache..." }
        cacheLRU.clear()
    }

    /**
     * Devuelve el tamaño de la cache
     * @return [Int]
     */
    override fun size(): Int {
        logger.debug { "Obteninendo tamaño de la cache" }
        return cacheLRU.size
    }

    /**
     * Devuelve un conjunto [Set] de claves del mapa
     */
    override fun keys(): Set<K> {
        logger.debug { "Obteniendo claves de la cache" }
        return cacheLRU.keys
    }

    /**
     * Devuelve una conleccion [Collection] de los valores del mapa
     */
    override fun values(): Collection<V> {
        logger.debug { "Obteniendo valores de la cache" }
        return cacheLRU.values
    }

    /**
     * Devuelve un conjunto que es un [Set] de un mapa de entradas de la cache
     */
    override fun entries(): Set<Map.Entry<K, V>> {
        logger.debug { "Obteniendo entradas de la cache" }
        return cacheLRU.entries
    }
}