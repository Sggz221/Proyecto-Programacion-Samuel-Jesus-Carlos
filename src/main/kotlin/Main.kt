package org.example

import org.example.models.Entrenador
import org.example.models.Integrante
import org.example.models.Jugador
import org.example.repositories.EquipoRepositoryImpl
import org.example.storage.*

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
    val storage2 = EquipoStorageJSON()
    val storage3 = EquipoStorageXML()
    val storage4 = EquipoStorageBIN()

    /*

    val equipo: List<Integrante> = storage.fileRead(file, format = "aaaa")
    //equipo.forEach {println(it)}

    val file2 = File("data", "personalOutput.csv")
    storage.fileWrite(equipo, file2, "aaaa")
    */
    //val fileCSV = File("data", "personal.csv")
    //val equipoCSV: List<Integrante> = storage.fileRead(fileCSV, format = "aaaa")

    //equipoCSV.forEach {println(it)}
    //val outputJSON = File("data", "personalOutput.json")
    //storage2.fileWrite(equipoCSV, outputJSON, "aaaa")

    //val fileXML = File("data", "personal.xml")
    //val equipoXML = storage3.fileRead(fileXML, format = "aaaa")
    //equipoXML.forEach { println(it) }

    //val outputXML = File("data", "personalOutput.xml")
    //storage3.fileWrite(equipoXML, outputXML, "aaaa")

    val file = File("data", "personalOutput.bin")
    val equipo: List<Integrante> = storage4.fileRead(file, format = "aaaa")
    equipo.forEach { println(it)}
}