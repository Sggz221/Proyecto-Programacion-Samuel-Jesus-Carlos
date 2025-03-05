package org.example.storage

import org.example.models.Integrante
import java.io.File

/**
 * Interfaz que representa el contrato para crear un almacenamiento
 */
interface EquipoStorage {
    fun fileRead(file: File): List<Integrante>
    fun fileWrite(equipo: List<Integrante>, file: File)
}