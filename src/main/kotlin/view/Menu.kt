package org.example.view

import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.service.Service
import org.example.service.ServiceImpl
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Menu (
    private val service: Service = ServiceImpl()
) {
    fun menu(){
        println("---- Aplicación de gestión de un equipo de fútbol ----")
        do{
            val option = readOpt() // Interactua co el usuario para preguntar que quiere
            callOperation(option)
        } while(option != 8)
        println("Cerrando aplicacion...")
    }
    // Funciones encapsuladas
    private fun readOpt(): Int{
        var option = 0
        do {
            println("¿Qué desea hacer?\n")
            println("|1. Cargar datos desde fichero\n" +
                    "|2. Crear un miebro del equipo\n" +
                    "|3. Actualizar un miembro del equipo\n" +
                    "|4. Eliminar un miembro del equipo\n" +
                    "|5. Mostrar miembros\n" +
                    "|6. Exportar equipo a fichero\n" +
                    "|7. Imprimir conusltas del equipo\n" +
                    "|8. Salir de la aplicacion")
            option = readln().toIntOrNull() ?: 0
            if (option !in 1..8) println(" Esa opcion no es valida, por favor, intentelo de nuevo.")
        }while (option !in 1..8)
        return option
    }
    fun callOperation(option: Int){
        when(option){
            1 -> cargarDatos()
            2 -> crearMiembro()
            5 -> service.getAll().forEach {println(it)}
            8 -> {}
        }
    }
    fun cargarDatos(){
        println("Especifique la ruta del archivo del que desea importar los datos: ")
        val path = readln()
        println("Leyendo fichero...")
        try {
            service.importFromFile(path)
        } catch (e: Exception) {
            println("Error al importar datos: ${e.message}")
        }
        println("Fichero cargado con exito")
    }
    fun crearMiembro(){
        var rol: String
        do{
            println("Quieres crear un jugador o un entrenador?")
            rol = readln().lowercase()
            if (rol != "jugador" && rol != "entrenador") println("Esa opcion no es valida, intentalo de nuevo.")
        }while(rol != "jugador" && rol != "entrenador")

        println("Escriba el nombre: ")
        val nombre = readln()

        println("Escriba los apellidos: ")
        val apellidos = readln()


        val regex = """^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\d|3[01])$""".toRegex()
        var fechaNacimiento = LocalDate.now()
        var fecha: String
        do {
            println("Escriba la fecha de nacimiento en formato yyyy-MM-dd Ej. 2000-01-01")
            fecha = readln()
            if (regex.matches(fecha)) {
                try {
                    fechaNacimiento = LocalDate.parse(fecha)
                }
                catch (e: Exception) {
                    fecha = "a"
                }
            }
            if(!regex.matches(fecha)) println("Fecha no valida.")
        }while (!regex.matches(fecha))

        var fechaIncorporacion = LocalDate.now()
        do {
            println("Escriba la fecha de incorporacion en formato yyyy-MM-dd Ej. 2000-01-01")
            fecha = readln()
            if (regex.matches(fecha)) {
                try {
                    fechaIncorporacion = LocalDate.parse(fecha)
                }
                catch (e: Exception) {
                    fecha = "a"
                }
            }
            if(!regex.matches(fecha)) println("Fecha no valida.")
        }while (!regex.matches(fecha))

        var salario: Double?
        do {
            println("Escriba el salario separando los decimales con un punto")
            salario = readln().toDoubleOrNull()
            if (salario == null) println("Ese salario no es valido, introduzcalo de nuevo por favor.")
        }while (salario == null)

        println("Escriba el pais de nacimiento: ")
        val pais = readln()

        if(rol == "jugador"){
            var option: Int
            do {
                println("Escriba el numero indicado en la posicion de la que juega el jugador \n1| Centrocampista, 2| Delantero, 3| Portero, 4| Defensa: ")
                option = readln().toIntOrNull() ?: 0
                if (option !in 1..4) println("Opcion no valida.")
            }while (option !in 1..4)
            var posicion: Posicion
            when(option){
                1 -> posicion = Posicion.CENTROCAMPISTA
                2 -> posicion = Posicion.DELANTERO
                3 -> posicion = Posicion.PORTERO
                else -> posicion = Posicion.DEFENSA
            }
            var dorsal: Int?
            do {
                println("Escriba la dorsal del jugador")
                dorsal = readln().toIntOrNull()
                if (dorsal == null) println("Esa dorsal no es valida.")
            }while (dorsal == null)

            var altura: Double?
            do {
                println("Escriba la altura (m) separando los decimales con un punto")
                altura = readln().toDoubleOrNull()
                if (altura == null) println("Esa altura no es valida.")
            }while (altura == null)

            var peso: Double?
            do {
                println("Escriba el peso (kg) separando los decimales con un punto")
                peso = readln().toDoubleOrNull()
                if (peso == null) println("Ese peso no es valido.")
            }while (peso == null)

            var goles: Int?
            do {
                println("Escriba los goles del jugador")
                goles = readln().toIntOrNull()
                if (goles == null) println("Ese numero de goles no es valido.")
            }while (goles == null)

            var partidosJugados: Int?
            do {
                println("Escriba el numero de partidos del jugador")
                partidosJugados = readln().toIntOrNull()
                if (partidosJugados == null) println("Ese numero de partidos no es valido.")
            }while (partidosJugados == null)

            val jugador = Jugador(
                nombre = nombre,
                apellidos = apellidos,
                fecha_nacimiento = fechaNacimiento,
                fecha_incorporacion = fechaIncorporacion,
                salario = salario,
                pais = pais,
                posicion = posicion,
                dorsal = dorsal,
                altura = altura,
                peso = peso,
                goles = goles,
                partidos_jugados = partidosJugados
            )
            try {
                service.save(jugador)
            }
            catch (e: Exception) {
                println("Error al crear jugador: ${e.message}")
            }
        }
        else{
            var option: Int
            do {
                println("Escriba el numero indicado en la especialidad del entrenador \n1| Principal, 2| Asistente, 3| Porteros")
                option = readln().toIntOrNull() ?: 0
                if (option !in 1..3) println("Opcion no valida.")
            }while (option !in 1..3)
            var especialidad: Especialidad
            when(option){
                1 -> especialidad = Especialidad.ENTRENADOR_PRINCIPAL
                2 -> especialidad = Especialidad.ENTRENADOR_ASISTENTE
                else -> especialidad = Especialidad.ENTRENADOR_PORTEROS
            }
            val entrenador = Entrenador(
                nombre = nombre,
                apellidos = apellidos,
                fecha_nacimiento = fechaNacimiento,
                fecha_incorporacion = fechaIncorporacion,
                salario = salario,
                pais = pais,
                especialidad = especialidad
            )
            try {
                service.save(entrenador)
            }
            catch (e: Exception) {
                println("Error al crear entrenador: ${e.message}")
            }
        }
    }
}