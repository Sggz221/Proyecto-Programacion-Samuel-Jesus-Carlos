package org.example

import org.example.models.DelanteroImpl
import org.example.models.interfaces.Delantero
import org.example.models.Entrenador
import org.example.models.EntrenadorPrincipalImpl
import org.example.models.interfaces.EntrenadorPorteros
import org.example.models.Jugador
import org.example.models.interfaces.EntrenadorPrincipal
import org.example.repositories.EquipoRepositoryImpl
import java.time.LocalDateTime

fun main() {
    println("Hello World!")
    var repositorio = EquipoRepositoryImpl()
    val j1 = Jugador(0, "Pepe", "Pepe", "Pepe", "Pepe", 11.0, "Pepe", LocalDateTime.now(), LocalDateTime.now(),false, DelanteroImpl(), 1, 11.0, 11.0, 1, 1)
    val e1 = Entrenador(0, "Pepe", "Pepe", "Pepe", "Pepe", 11.0, "Pepe", LocalDateTime.now(), LocalDateTime.now(),false, EntrenadorPrincipalImpl())

    repositorio.save(j1)
    repositorio.save(e1)

}