package org.example.service

import org.example.models.Integrante

interface Service {
    fun importFromFile(filePath: String)
    fun exportToFile(filePath: String)

    fun getAll(): List<Integrante>
    fun getById(id: Long): Integrante
    fun save(integrante: Integrante): Integrante
    fun update(id: Long, integrante: Integrante): Integrante
    fun delete(id: Long): Integrante
    fun deleteLogical(id: Long, integrante: Integrante): Integrante
}