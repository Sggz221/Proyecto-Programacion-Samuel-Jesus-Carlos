package org.example.service

import org.example.models.Integrante

/**
 * Interfaz que crea el contrato para crear el servicio para gestionar un equipo de futbol en memoria y en el almacenamiento local
 */
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