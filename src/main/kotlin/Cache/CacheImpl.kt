package org.example.Cache

import org.lighthousegames.logging.logging

class CacheImpl<K, V>(private val capacidad: Int) : Cache<K, V> {
    private val logger = logging()

    private val cacheLRU: LinkedHashMap<K, V> = object: LinkedHashMap<K, V>(capacidad, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
            logger.debug { "Alcanzado el tamaño máximo: eliminando elemento más antiguo de la cache" }
            return size > capacidad
        }
    }

    override fun get(key: K): V? {
        logger.debug{"Obteniendo elemento en la cache con clave $key"}
        return cacheLRU[key]
    }

    override fun put(key: K, value: V): V? {
        logger.debug { "Guardando elemento en la cache con clave $key" }
        cacheLRU[key] = value
        return value
    }

    override fun remove(key: K): V? {
        logger.debug { "Eliminando elemento en la cache con clave $key" }
        return cacheLRU.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando la cache..." }
        cacheLRU.clear()
    }

    override fun size(): Int {
        logger.debug { "Obteninendo tamaño de la cache" }
        return cacheLRU.size
    }

    override fun keys(): Set<K> {
        logger.debug { "Obteniendo claves de la cache" }
        return cacheLRU.keys
    }

    override fun values(): Collection<V> {
        logger.debug { "Obteniendo valores de la cache" }
        return cacheLRU.values
    }

    override fun entries(): Set<Map.Entry<K, V>> {
        logger.debug { "Obteniendo entradas de la cache" }
        return cacheLRU.entries
    }
}