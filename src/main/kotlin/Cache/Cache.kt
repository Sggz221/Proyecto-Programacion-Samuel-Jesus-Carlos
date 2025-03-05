package org.example.Cache

/**
 * Interfaz que represeta el contrato de la cache
 */
interface Cache<K, V> {
    fun get(key: K): V?
    fun put(key: K, value: V): V?
    fun remove(key: K): V?
    fun clear() // procedimiento
    fun size(): Int
    fun keys(): Set<K>
    fun values(): Collection<V>
    fun entries(): Set<Map.Entry<K, V>>
}