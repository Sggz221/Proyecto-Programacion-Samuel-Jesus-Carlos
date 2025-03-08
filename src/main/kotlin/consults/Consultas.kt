package org.example.consults

import org.example.models.Entrenador
import org.example.models.Jugador
import org.example.models.Posicion
import org.example.service.ServiceImpl
import java.io.File
import java.time.LocalDate

/**
 * @property service [ServiceImpl] Implementacion del servicio para poder realizar las consultas sobre un fichero
 */
class Consultas(private val service:ServiceImpl = ServiceImpl()) {

    fun consultas (){
        val service = ServiceImpl()


        service.importFromFile(File("data", "personal.csv").path)
        service.getAll().forEach {println(it)}
        println("--------------------------------------")

        //Println("1. Listados de personal agrupados por entrenadores y jugadores.")
        //service.getAll().groupBy {it is Jugador || it is Entrenador}.forEach { println(it) }

        println("--------------------------------------")

        println("2. El delantero más alto.")
        println(service.getAll().filterIsInstance<Jugador>().filter { it.posicion == Posicion.DELANTERO }.maxBy { it.altura })

        println("--------------------------------------")

        println("3. Media de goles de los delanteros.")
        println(service.getAll().filterIsInstance<Jugador>().filter { it.posicion == Posicion.DELANTERO }.map { it.goles }.average())

        println("--------------------------------------")

        println("4. Defensa con más partidos jugados.")
        println(service.getAll().filterIsInstance<Jugador>().filter { it.posicion == Posicion.DEFENSA }.maxBy { it.partidos_jugados })

        println("--------------------------------------")

        println("5. Jugadores agrupados por su país de origen.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.pais }.forEach { println(it) }

        println("--------------------------------------")

        println("6. Entrenador con el mayor salario.")
        println(service.getAll().filterIsInstance<Entrenador>().maxBy { it.salario })

        println("--------------------------------------")

        println("7. Promedio de altura de los jugadores agrupados por posición.")
        println(service.getAll().filterIsInstance<Jugador>().groupBy { it.posicion }.mapValues { (_,jugadores) -> jugadores.map { it.altura }.average() })

        println("--------------------------------------")

        println("8. Listado de todos los jugadores que han anotado más de 10 goles.")
        service.getAll().filterIsInstance<Jugador>().filter { it.goles > 10 }.forEach { println(it) }

        println("--------------------------------------")
        //no muestra nada ya que no hay nada
        println("9. Jugadores con un salario mayor al promedio del equipo.")
        service.getAll().filterIsInstance<Jugador>().filter { it.salario > service.getAll().map { it.salario }.average()  }.forEach { println(it) }
        println("--------------------------------------")

        println("10. Número total de partidos jugados por todos los jugadores.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.nombre }.mapValues { (_,jugadores) -> jugadores.map { it.partidos_jugados } }.forEach { println(it) }

        println("--------------------------------------")

        println("11. Jugadores agrupados por el año de su incorporación al club.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.fecha_incorporacion }.forEach { println(it) }

        println("--------------------------------------")

        println("12. Entrenadores agrupados por su especialidad.")
        service.getAll().filterIsInstance<Entrenador>().groupBy { it.especialidad }.forEach { println(it) }

        println("--------------------------------------")

        println("13. Jugador más joven en el equipo.")
        println(service.getAll().filterIsInstance<Jugador>().maxBy { it.fecha_nacimiento })

        println("--------------------------------------")
        println("14. Promedio de peso de los jugadores por posición.")
        println(service.getAll().filterIsInstance<Jugador>().groupBy { it.posicion }.mapValues { (_,jugadores) -> jugadores.map { it.peso }.average() })

        println("--------------------------------------")
        println("15. Listado de todos los jugadores que tienen un dorsal par.")
        service.getAll().filterIsInstance<Jugador>().filter { it.dorsal % 2 == 0 }.forEach { println(it) }
        //no muestra nada ya que no hay nada
        println("--------------------------------------")
        println("16. Jugadores que han jugado menos de 5 partidos.")
        service.getAll().filterIsInstance<Jugador>().filter { it.partidos_jugados < 5 }.forEach { println(it) }

        println("--------------------------------------")
        println("17. Media de goles por partido de cada jugador.")
        service.getAll().filterIsInstance<Jugador>().forEach {
            val media:Double = it.goles.toDouble() / it.partidos_jugados
            println(it.id.toString()+ " " + it.nombre + " " + media)
        }
        //filtro por jugador ya que entrenador no tiene altura
        println("--------------------------------------")
        println("18. Listado de jugadores que tienen una altura superior a la media del equipo.")
        val altura = service.getAll().filterIsInstance<Jugador>().map { it.altura }.average()
        service.getAll().filterIsInstance<Jugador>().filter { it.altura > altura }.forEach { println(it) }

        //no muestra nada ya que no hay nada
        println("--------------------------------------")
        println("19. Entrenadores que se incorporaron al club en los últimos 5 años.")
        service.getAll().filterIsInstance<Entrenador>().filter { (LocalDate.now().year - it.fecha_incorporacion.year) <=5  }.forEach { println(it) }

        println("--------------------------------------")

        println("20. Jugadores que han anotado más goles que el promedio de su posición.")
        val golesDelantero = service.getAll().filterIsInstance<Jugador>().filter { it.posicion == Posicion.DELANTERO }.map { it.goles}.average()
        service.getAll().filterIsInstance<Jugador>().filter { it.posicion == Posicion.DELANTERO && it.goles > golesDelantero}.forEach { it }

        println("--------------------------------------")

        println("21. Por posición, máximo de goles, mínimo de goles y media.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.posicion }.mapValues { (_,jugadores) ->
            val goles = jugadores.map { it.goles }
            Triple(goles.maxOrNull(), goles.minOrNull(), goles.average())}.forEach { println(it) }

        println("--------------------------------------")

        println("22. Estimación del coste total de la plantilla.")
        val sumSalarios = service.getAll().sumOf { it.salario }
        println("Coste total de toda la plantilla: $sumSalarios")

        println("--------------------------------------")

        println("23. Total del salario pagado, agrupados por año de incorporación.")
        println(service.getAll().groupBy { it.fecha_incorporacion }.mapValues { (_,jugadores) -> jugadores.sumOf { it.salario } } )

        println("--------------------------------------")

        println("24. Jugadores agrupados por país y, dentro de cada grupo, el jugador con más partidos jugados.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.pais }.mapValues { (_,jugadores) -> jugadores.maxBy { it.partidos_jugados } }.forEach { println(it) }

        println("--------------------------------------")

       println("25. Promedio de goles por posición, y dentro de cada posición, el jugador con el mayor número de goles")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.posicion }.mapValues { (_,jugadores) ->
            val maxGoles = jugadores.maxBy { it.goles }
            val goles = jugadores.map { it.goles }
            Pair(goles.maxOrNull(), maxGoles)
        }.forEach{ println(it)}

        println("--------------------------------------")

        println("26. Entrenadores agrupados por especialidad, y dentro de cada especialidad, el entrenador con el salario más alto.")
        service.getAll().filterIsInstance<Entrenador>().groupBy { it.especialidad }.mapValues { (_,entrenadores) -> entrenadores.maxBy { it.salario } }.forEach { println(it) }

        println("--------------------------------------")


        println("27. Jugadores agrupados por década de nacimiento, y dentro de cada grupo, el promedio de partidos jugados.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.fecha_nacimiento }.mapValues { (_,jugadores) -> jugadores.map { it.partidos_jugados }.average() }.forEach { println(it) }


        println("--------------------------------------")

        println("28. Salario promedio de los jugadores agrupados por su país de origen, y dentro de cada grupo, el jugador con el salario más bajo y alto.")
        service.getAll().filterIsInstance<Jugador>().groupBy { it.pais }.mapValues { (_,jugadores) ->
            Triple(jugadores.map{it.salario}.average(), jugadores.maxBy { it.salario }, jugadores.minBy{ it.salario} )}.forEach{ println(it)}
    }
}