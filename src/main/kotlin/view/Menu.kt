package org.example.view

import org.example.configuration.Configuration
import org.example.consults.Consultas
import org.example.extensions.copy
import org.example.models.*
import org.example.service.Service
import org.example.service.ServiceImpl
import java.io.File
import java.time.LocalDate

/** Menu para seleccionar la operacion que elija el usuario
 * @property service [ServiceImpl] Implementacion del servicio de integrantes del equipo.
 * @property data [Consultas] Implementacion de una calse externa para realizar las consultas propuestas en l ejercicio sobre un fichero csv
 */
class Menu (
    private val service: Service = ServiceImpl(),
    private val data: Consultas = Consultas(),
) {
    /**
     * Menu que ejecuta el programa hasta que e usuario decida cerrar
     */
    fun menu(){
        println("---- Aplicación de gestión de un equipo de fútbol ----")
        do{
            val option = readOpt() // Interactua co el usuario para preguntar que quiere
            callOperation(option)
        } while(option != 8)
        println("Cerrando aplicacion...")
    }
    // Funciones encapsuladas
    /**
     * Funcion que da opciones a elegir al usuario, recibe por consola y develve la opcion elegida como un Entero
     * @return [Int] Numero representativo de la opcion elegida por el usuario
     */
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

    /**
     * Funcion que recibe un entero emitido por la funcion [readOpt] y ejecuta otra funcion en consecuencia
     * @param opt [Int] Numero entero recibido por otra funcion
     */
    fun callOperation(option: Int){
        when(option){
            1 -> cargarDatos()
            2 -> crearMiembro()
            3 -> actualizarMiembro()
            4 -> borrarMiembro()
            5 -> service.getAll().forEach {println(it)}
            6 -> exportarDatos()
            7 -> data.consultas()
            8 -> {}
        }
    }

    /**
     * Pregunta por un formato de archivo al usuario entre [XML], [BIN], [CSV] y [JSON]
     * @param importar [Boolean] Parametro que define si el texto que aparecera en pantalla tiene sentido para preguntar por una importacion de archivos o por una exportacion
     * @return [String] Una cadena de texto que representa una de las extensiones permitidas en letra minuscula
     */
    fun preguntarFormato(importar: Boolean): String{
        var option: Int
        do {
            if (importar) println("¿De qué fichero importar los datos del equipo? \n1| CSV, 2| JSON, 3| XML, 4| BIN: ")
            else println("En qué formato quiere exportar los datos del equipo? \n1| CSV, 2| JSON, 3| XML, 4| BIN: ")

            option = readln().toIntOrNull() ?: 0
            if (option !in 1..4) println("Opcion no valida.")
        }while (option !in 1..4)

        var extension: String
        when(option){
            1 -> extension = "csv"
            2 -> extension = "json"
            3 -> extension = "xml"
            else -> extension = "bin"
        }
        return extension
    }

    /**
     * Pregunta por un nombre que es un [String] y sigue una expresion regular para poder ser empleado como un nombre de un archivo
     * @return [String] El nombre escrito y verificado como cadena de texto
     */
    fun preguntarNombre(): String {
        val regEx = """^[a-zA-Z0-9_-]+$""".toRegex()
        var nombre: String

        do {
            println("¿Qué nombre quiere ponerle al archivo?\nSolo caracteres alfanuméricos, guiones - y guiones bajos _")
            nombre = readln()
            if (!nombre.matches(regEx)) println("Nombre no válido")
        } while(!nombre.matches(regEx))

        return nombre
    }

    /**
     * Exporta los datos a un archivo generado con [preguntarNombre] y [preguntarFormato] en la carpeta backup
     */
    private fun exportarDatos() {
        val extension = preguntarFormato(false)
        val nombre = preguntarNombre()
        val file = File(Configuration.configurationProperties.backupDirectory, "${nombre}.${extension}")
        service.exportToFile(file.path)
    }

    /**
     * Actualiza un miembro preguntando por su ID con [preguntarID] y despues de inferenciar si es un [Jugador] o un [Entrenador]
     * pregunta los campos que se quieren cambiar usando [preguntarCampos] y en base al nombre del campo usar el servicio [ServiceImpl]
     * para crear una copia del objeto y actualizar uno de los campos permitidos elegido por el usuario
     * @see [preguntarString]
     * @see [preguntarFechas]
     * @see [preguntarDoble]
     * @see [preguntarString]
     * @see [preguntarEntero]
     * @see [preguntarCampos]
     * @see [preguntarPosicion]
     * @see [preguntarEspecialidad]
     * @throws Exception si no encuentra un elemento
     */
    fun actualizarMiembro() {
        val id  = preguntarID()
        try {
            val integrante = service.getById(id)
            if (integrante is Jugador){
                val campo = preguntarCampos(true)
                when(campo){
                    "nombre" -> service.update(id, integrante.copy(newNombre = preguntarString("nombre")))
                    "apellidos" -> service.update(id, integrante.copy(newApellidos = preguntarString("apellidos")))
                    "fecha_nacimiento" -> service.update(id, integrante.copy(new_fecha_nacimiento = preguntarFechas(true)))
                    "fecha_incorporacion" -> service.update(id, integrante.copy(new_fecha_incorporacion = preguntarFechas(false)))
                    "salario" -> service.update(id, integrante.copy(newSalario = preguntarDoble("salario")))
                    "pais" -> service.update(id, integrante.copy(newPais = preguntarString("pais")))
                    "posicion" -> service.update(id, integrante.copy(newPosicion = preguntarPosicion()))
                    "dorsal" -> service.update(id, integrante.copy(newDorsal = preguntarEntero("dorsal")))
                    "goles" -> service.update(id, integrante.copy(newDorsal = preguntarEntero("goles")))
                    "partidos_jugados" -> service.update(id, integrante.copy(newDorsal = preguntarEntero("partidos_jugados")))
                }
            }
            if(integrante is Entrenador){
                val campo = preguntarCampos(true)
                when(campo){
                    "nombre" -> service.update(id, integrante.copy(newNombre = preguntarString("nombre")))
                    "apellidos" -> service.update(id, integrante.copy(newApellidos = preguntarString("apellidos")))
                    "fecha_nacimiento" -> service.update(id, integrante.copy(new_fecha_nacimiento = preguntarFechas(true)))
                    "fecha_incorporacion" -> service.update(id, integrante.copy(new_fecha_incorporacion = preguntarFechas(false)))
                    "salario" -> service.update(id, integrante.copy(newSalario = preguntarDoble("salario")))
                    "pais" -> service.update(id, integrante.copy(newPais = preguntarString("pais")))
                    "especialidad" -> service.update(id, integrante.copy(newEspecialidad = preguntarEspecialidad()))
                }
            }
        }catch (e: Exception){
            println("${e.message}")
        }
    }

    /**
     * Pregunta los campos al usuario en funcion de un parametro para saber si tiene que preguntar los campos de un [Entrenador] o un [Jugador]
     * @param jugador Determina si se va a preguntar por un Jugador si es [true] o si se va a preguntar por un entrenador si es [false]
     * @return [String] El campo
     */
    fun preguntarCampos(jugador: Boolean): String{
        if (jugador) {
            println("¿Qué campo del jugador deseas acutualizar?" +
                    "|1. Nombre |2. Apellidos |3. Fecha de Nacimiento |4. Fecha de incorporacion |5. Salario |6. Pais |7. Posicion |8. Dorsal |9. Altura |10. Peso |11. Goles |12. Partidos Jugados")
            var opt: Int?
            do {
                opt = readln().toIntOrNull()
                if (opt == null) println("Opcion no valida.")
            }while (opt == null)
            var campo: String = ""
            when(opt) {
                1 -> campo = "nombre"
                2 -> campo = "apellidos"
                3 -> campo = "fecha_nacimiento"
                4 -> campo = "fecha_incorporacion"
                5 -> campo = "salario"
                6 -> campo = "pais"
                7 -> campo = "posicion"
                8 -> campo = "dorsal"
                9 -> campo = "altura"
                10 -> campo = "peso"
                11 -> campo = "goles"
                12 -> campo = "partidos_jugados"
            }
            return campo
        }
        else{
            println("¿Qué campo del entrenador deseas acutualizar?" +
                    "|1. Nombre |2. Apellidos |3. Fecha de Nacimiento |4. Fecha de incorporacion |5. Salario |6. Pais |7. Especialidad")
            var opt: Int?
            do {
                opt = readln().toIntOrNull()
                if (opt == null) println("Opcion no valida.")
            }while (opt == null)
            var campo: String = ""
            when(opt) {
                1 -> campo = "nombre"
                2 -> campo = "apellidos"
                3 -> campo = "fecha_nacimiento"
                4 -> campo = "fecha_incorporacion"
                5 -> campo = "salario"
                6 -> campo = "pais"
                7 -> campo = "especialidad"
            }
            return campo
        }
    }

    /**
     * Borra un miembro logicamente cambiando el campo [Jugador.isDeleted] en base a un id usando [preguntarID].
     * En esencia funciona igual que [actualizarMiembro] pero para un campo en especifico comun a [Jugador] y a [Entrenador]
     * @throws Exception Si no encuentra el integrante
     */
    fun borrarMiembro() {
        val id = preguntarID()

        try {
            val jugador = service.getById(id)
            service.deleteLogical(id,jugador)
        } catch(e:Exception){
            println(e.message)
        }
    }

    /**
     * Pregunta un ID al usuario
     * @return [Long] Devuelve el ID
     */
    fun preguntarID(): Long {
        var input: Long?

        do {
            println("Escriba el id del jugador")
            input = readln().toLongOrNull()
            if (input == null) println("Dato no válido")
        }while (input == null)

        return input
    }

    /**
     * Importa los datos a la memoria de un archivo de nombre personal y extension elegida por el usuario usando [preguntarFormato]
     * y el servicio [ServiceImpl]
     * @throws Exception Si no consigue leer el fichero
     */
    fun cargarDatos(){
        val formato = preguntarFormato(true)
        val file = File(Configuration.configurationProperties.dataDirectory, "personal.${formato}")

        println("Leyendo fichero...")
        try {
            service.importFromFile(file.path)
        } catch (e: Exception) {
            println("Error al importar datos: ${e.message}")
        }
        println("Fichero cargado con exito")
    }

    /**
     * Pregunta un rol al usuario
     * @return [String] La extension del archivo en letra minuscula
     */
    fun preguntarRol (): String {
        var rol: String
        do{
            println("Quieres crear un jugador o un entrenador?")
            rol = readln().lowercase()
            if (rol != "jugador" && rol != "entrenador") println("Esa opcion no es valida, intentalo de nuevo.")
        }while(rol != "jugador" && rol != "entrenador")

        return rol
    }

    /**
     * Pregunta un campo de [Jugador] o [Entrenador] como [String]
     * @param campo El nombre del campo que va a preguntar
     * @return [String] El valor del campo dado por el usuario
     */
    fun preguntarString (campo: String): String {

        if (campo == "nombre") println("Escriba el nombre:")
        if (campo == "apellidos") println("Escriba los apellidos:")
        if (campo == "pais") println("Escriba el país:")

        val input = readln()

        return input
    }

    /**
     * Pregunta una fecha al usuario haciendo un filtro a traves de una expresion regular
     * @throws Exception Si no consigue parsear la fecha
     */
    fun preguntarFechas (nacimiento: Boolean): LocalDate {
        val regex = """^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[12]\d|3[01])$""".toRegex()
        var fechaFormato = LocalDate.now()
        var fecha: String
        do {
            if (nacimiento) {
                println("Escriba la fecha de nacimiento en formato yyyy-MM-dd Ej. 2000-01-01")
            } else {
                println("Escriba la fecha de incorporacion en formato yyyy-MM-dd Ej. 2000-01-01")
            }

            fecha = readln()
            if (regex.matches(fecha)) {
                try {
                    fechaFormato = LocalDate.parse(fecha)
                }
                catch (e: Exception) {
                    fecha = "a" // Para que falle la expresion regular
                }
            }
            if(!regex.matches(fecha)) println("Fecha no valida.")
        }while (!regex.matches(fecha))

        return fechaFormato
    }

    /**
     * Pregunta un campo [Double] de [Jugador] o [Integrante]
     * @param campo Para saber por que campo preguntar
     * @return [Dopuble] El valor como un doble
     */
    fun preguntarDoble (campo: String): Double {
        var input: Double
        do {
            if (campo == "salario") println("Escriba el salario separando los decimales con un punto")
            if (campo == "altura") println("Escriba la altura (m) separando los decimales con un punto")
            if (campo == "peso") println("Escriba el peso (kg) separando los decimales con un punto")

            input = readln().toDoubleOrNull() ?: -1.0
            if (input == -1.0) println("Dato no valido, introduzcalo de nuevo por favor.")
        }while (input == -1.0)

        return input
    }

    /**
     * Pregunta una [Posicion] al usuario dandole unas opciones.
     * @return [Posicion] La posicion elegida
     */
    fun preguntarPosicion(): Posicion{
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
        return posicion
    }

    /**
     * Pregunta un campo [Int] de [Jugador] o [Entrenador]
     * @param campo Para saber por que campo preguntar
     * @return [Int] El valor del campo
     */
    fun preguntarEntero(campo: String): Int{
        var input: Int?

        if (campo == "dorsal") println("Escriba la dorsal del jugador")
        if (campo == "goles") println("Escriba los goles del jugador")
        if (campo == "partidos") println("Escriba el número de partidos del jugador")

        do {
            input = readln().toIntOrNull()
            if (input == null) println("Dato no válido")
        }while (input == null)

        return input
    }

    /**
     * Pregunta una [Especialidad] al usuario dandole unas opciones
     * @return [Especialidad] La especialidad elegida por el usuario
     */
    fun preguntarEspecialidad (): Especialidad {
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
        return especialidad
    }

    /**
     * Crea un objeto [Jugador] o [Entrenador] en base a [preguntarRol]
     * @throws Exception si no consigue guardar el integrante usando el [ServiceImpl]
     */
    fun crearMiembro(){
        var rol = preguntarRol()

        if(rol == "jugador"){

            val jugador = Jugador(
                nombre = preguntarString("nombre"),
                apellidos = preguntarString("apellidos"),
                fecha_nacimiento = preguntarFechas(true),
                fecha_incorporacion = preguntarFechas(false),
                salario = preguntarDoble("salario"),
                pais = preguntarString("pais"),
                posicion = preguntarPosicion(),
                dorsal = preguntarEntero("dorsal"),
                altura = preguntarDoble("altura"),
                peso = preguntarDoble("peso"),
                goles = preguntarEntero("goles"),
                partidos_jugados = preguntarEntero("partidos")
            )
            try {
                service.save(jugador)
                println("Jugador guardado con éxito")
            }
            catch (e: Exception) {
                println("Error al crear jugador: ${e.message}")
            }
        } else{

            val entrenador = Entrenador(
                nombre = preguntarString("nombre"),
                apellidos = preguntarString("apellidos"),
                fecha_nacimiento = preguntarFechas(true),
                fecha_incorporacion = preguntarFechas(false),
                salario = preguntarDoble("salario"),
                pais = preguntarString("pais"),
                especialidad = preguntarEspecialidad()
            )
            try {
                service.save(entrenador)
                println("Entrenador guardado con éxito")
            }
            catch (e: Exception) {
                println("Error al crear entrenador: ${e.message}")
            }
        }
    }
}