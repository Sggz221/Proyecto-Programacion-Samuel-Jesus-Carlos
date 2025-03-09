package repositories


import org.example.models.*
import org.example.repositories.EquipoRepositoryImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate
import java.time.LocalDateTime


class EquipoRepositoryImplTest {

    val jugador:Integrante = Jugador(
        id = 1, nombre = "pepe",
        apellidos = "García",
        fecha_nacimiento = LocalDate.of(2002, 10, 16),
        fecha_incorporacion = LocalDate.of(2007, 10, 13),
        salario = 1000.0,
        pais = "España",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        isDeleted = false,
        posicion = Posicion.DELANTERO,
        dorsal = 9,
        altura = 1.82,
        peso = 85.0,
        goles = 40,
        partidos_jugados =100)

    val jugadorExpected:Integrante = Jugador(
        id = 1, nombre = "pepe",
        apellidos = "García",
        fecha_nacimiento = LocalDate.of(2002, 10, 16),
        fecha_incorporacion = LocalDate.of(2007, 10, 13),
        salario = 1000.0,
        pais = "España",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        isDeleted = false,
        posicion = Posicion.DELANTERO,
        dorsal = 9,
        altura = 1.82,
        peso = 85.0,
        goles = 40,
        partidos_jugados =100)

    val entrenador:Integrante = Entrenador(
        id = 2, nombre = "pepe",
        apellidos = "García",
        fecha_nacimiento = LocalDate.of(2002, 10, 16),
        fecha_incorporacion = LocalDate.of(2007, 10, 13),
        salario = 1000.0,
        pais = "España",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        isDeleted = false,
        especialidad = Especialidad.ENTRENADOR_ASISTENTE
    )






    @Test
    fun save() {
        val equip = EquipoRepositoryImpl()
        val jugadorGuardado = equip.save(jugador)
        val jugadorGuardado1 = equip.save(jugador)

        val entrenadorGuardado = equip.save(entrenador)


        assertNotEquals(jugadorGuardado1.id,jugadorGuardado.id)
        assertEquals(entrenadorGuardado.id,3)
    }


    @Test
    fun delete() {
        val equip = EquipoRepositoryImpl()
        val equipoGuardado = equip.save(jugador)
        val equipoEliminado = equip.delete(equipoGuardado.id)
        assertNotNull(equipoEliminado)

        var equipo = equip.getById(1)
        assertNull(equipo)


    }

    @Test
    fun update() {
        val equip = EquipoRepositoryImpl()
        val cambio1 = equip.save(jugador)
        Thread.sleep(1)
        val cambio2 = equip.update(1,jugador)
        if (cambio2 != null) {
            assertNotEquals(cambio1.updatedAt,cambio2.updatedAt)
        }

    }

    @Test
    fun actualizarNull(){
        val equip = EquipoRepositoryImpl()
        val cambio1 = equip.save(jugador)

        assertNull(equip.update(2,cambio1))
    }

    @Test
    fun getAll() {
        val equip = EquipoRepositoryImpl()
        var equipo = equip.getAll()
        assertTrue(equipo.isEmpty())



    }

    @Test
    fun getById() {
        val equip = EquipoRepositoryImpl()
        equip.save(jugador)
        var equipo = equip.getById(1)
        assertNotNull(equipo)

        equipo = equip.getById(5)
        assertNull(equipo)
    }



    @Test
    fun deleteLogical() {
        val equip = EquipoRepositoryImpl()
        var guardar = equip.save(jugador)
        var guardar1 = equip.save(jugador)

        Thread.sleep(1)

        equip.deleteLogical(guardar.id,guardar)


        if (guardar != null) {
            assertEquals(guardar.isDeleted,true)
            assertNotEquals(guardar1.updatedAt,guardar.updatedAt)
        }

        val error = equip.deleteLogical(3,guardar)
        assertNull(error)



    }



}

