package org.example.repositories

interface CrudRepository<ID, T> {
    fun save(id:ID, entity: T): T
    fun delete(id: ID): T?
    fun update(id: ID, entity: T): T?
    fun getAll(): List<T>
    fun getById(id: ID): T?
}