package org.example

import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import org.example.repositories.EquipoRepositoryImpl
import org.example.storage.EquipoStorage
import org.example.storage.EquipoStorageCSV
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    println("Hello World!")
    var repositorio = EquipoRepositoryImpl()
    val j1 = Jugador(0, "Pepe", "Pepe", LocalDate.now(), LocalDate.now(), 11.0, "Pepe", LocalDateTime.now(), LocalDateTime.now(),false, Posicion.CENTROCAMPISTA, 1, 2.0, 11.0, 1, 1)
    val e1 = Entrenador(0, "Pepe", "Pepe", LocalDate.now(), LocalDate.now(), 11.0, "Pepe", LocalDateTime.now(), LocalDateTime.now(),false, Especialidad.ENTRENADOR_PRINCIPAL)

    repositorio.save(j1)
    repositorio.save(e1)

    val storage = EquipoStorageCSV()
    val file = File("data", "personal.csv")
    val equipo: List<Integrante> = storage.fileRead(file, format = "aaaa")
    equipo.forEach {println(it)}

    val file2 = File("data", "personalOutput.csv")
    storage.fileWrite(equipo, file2, "aaaa")

}