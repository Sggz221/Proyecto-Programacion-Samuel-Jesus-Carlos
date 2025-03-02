package org.example.storage

import org.example.models.Integrante
import java.io.File

interface EquipoStorage {
    fun fileRead(file: File, format: String): List<Integrante>
    fun fileWrite(integrantes: List<Integrante>, file: File, format: String)
}