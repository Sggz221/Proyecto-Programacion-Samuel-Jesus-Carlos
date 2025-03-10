package org.example.repositories

import org.example.models.Integrante

interface EquipoRepository<T, U> : CrudRepository<Long, Integrante> {
    fun deleteLogical(id: Long, entity: Integrante): Integrante?
}