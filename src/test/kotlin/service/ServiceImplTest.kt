package service

import org.example.exceptions.Exceptions
import org.example.extensions.copy
import org.example.models.Entrenador
import org.example.models.Especialidad
import org.example.models.Integrante
import org.example.models.Jugador
import org.example.service.ServiceImpl
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.time.LocalDate

class ServiceImplTest {

  @Test
  fun importAndExportFromFileCSV() {
   val service = ServiceImpl()

   val fileFrom = File("data", "personal.csv")
   val fileTo = File("data", "personalOutput.csv")
   service.importFromFile(fileFrom.path)
   service.exportToFile(fileTo.path)
  }
  @Test
  fun importAndExportFromFileXML() {
   val service = ServiceImpl()

   val fileFrom = File("data", "personal.xml")
   val fileTo = File("data", "personalOutput.xml")
   service.importFromFile(fileFrom.path)
   service.exportToFile(fileTo.path)
  }
  @Test
  fun importAndExportFromFileJSON() {
   val service = ServiceImpl()

   val fileFrom = File("data", "personal.json")
   val fileTo = File("data", "personalOutput.json")
   service.importFromFile(fileFrom.path)
   service.exportToFile(fileTo.path)
  }
  @Test
  fun importAndExportFromFileBIN() {
   val service = ServiceImpl()

   val fileFrom = File("data", "personal.bin")
   val fileTo = File("data", "personalOutput.bin")
   service.importFromFile(fileFrom.path)
   service.exportToFile(fileTo.path)
  }
  @Test
  fun getAll() {
   val service = ServiceImpl()

   service.getAll()
  }

  @Test
  fun getByIdRepository() {
      val service = ServiceImpl()
      val file = File("data", "personal.csv")
      service.importFromFile(file.path)
      val integrante = service.getById(1)

  }
 @Test
 fun getByIdRepositoryNotFound() {
     val service = ServiceImpl()
     val exception = assertThrows<Exceptions.NotFoundException>{service.getById(-1)}
     assertEquals(exception.message, "Integrante no encontrado: Integrante no encontrado con id -1")

 }
  @Test
  fun getByIdNotFound() {
      val service = ServiceImpl()
      val file = File("data", "personal.csv")
      service.importFromFile(file.path)
      val exception = assertThrows<Exceptions.NotFoundException>{service.getById(-1)}
      assertEquals(exception.message, "Integrante no encontrado: Integrante no encontrado con id -1")

  }

  @Test
  fun save() {
      val service = ServiceImpl()
      val entrenador = Entrenador(
          id = 1,
          nombre ="Tom",
          apellidos = "Baker",
          fecha_nacimiento = LocalDate.parse("1984-03-20"),
          fecha_incorporacion = LocalDate.parse("2001-05-15"),
          salario = 32000.0,
          pais = "Inglaterra",
          especialidad = Especialidad.ENTRENADOR_PRINCIPAL
      )
      val entrenadorDevuelto = service.save(entrenador)
      assertAll(
          { assertEquals(entrenador.id, entrenadorDevuelto.id) },
          { assertEquals(entrenador.nombre, entrenadorDevuelto.nombre) },
          { assertEquals(entrenador.apellidos, entrenadorDevuelto.apellidos) },
          { assertEquals(entrenador.fecha_nacimiento, entrenadorDevuelto.fecha_nacimiento) },
          { assertEquals(entrenador.fecha_incorporacion, entrenadorDevuelto.fecha_incorporacion) },
          { assertEquals(entrenador.salario, entrenadorDevuelto.salario) },
          { assertEquals(entrenador.pais, entrenadorDevuelto.pais) },
          { assertEquals(entrenador.especialidad, (entrenadorDevuelto as Entrenador).especialidad) },
          )
  }

  @Test
  fun update() {
      val service = ServiceImpl()
      val file = File("data", "personal.csv")
      val entrenador = Entrenador(
          id = 1,
          nombre ="Tom",
          apellidos = "Baker",
          fecha_nacimiento = LocalDate.parse("1984-03-20"),
          fecha_incorporacion = LocalDate.parse("2001-05-15"),
          salario = 32000.0,
          pais = "Inglaterra",
          especialidad = Especialidad.ENTRENADOR_PRINCIPAL
      )
      val entrenador2 = entrenador.copy()
      service.importFromFile(file.path)
      val entrenadorDevuelto = service.update(1, entrenador2)
      assertAll(
          { assertEquals(entrenador.id, entrenadorDevuelto.id) },
          { assertEquals(entrenador.nombre, entrenadorDevuelto.nombre) },
          { assertEquals(entrenador.apellidos, entrenadorDevuelto.apellidos) },
          { assertEquals(entrenador.fecha_nacimiento, entrenadorDevuelto.fecha_nacimiento) },
          { assertEquals(entrenador.fecha_incorporacion, entrenadorDevuelto.fecha_incorporacion) },
          { assertEquals(entrenador.salario, entrenadorDevuelto.salario) },
          { assertEquals(entrenador.pais, entrenadorDevuelto.pais) },
          { assertEquals(entrenador.especialidad, (entrenadorDevuelto as Entrenador).especialidad) },
          { assertNotEquals(entrenador.updatedAt, entrenadorDevuelto.updatedAt) },
      )
  }
    @Test
    fun updateNull(){
        val service = ServiceImpl()
        val entrenador = Entrenador(
            id = 1,
            nombre ="Tom",
            apellidos = "Baker",
            fecha_nacimiento = LocalDate.parse("1984-03-20"),
            fecha_incorporacion = LocalDate.parse("2001-05-15"),
            salario = 32000.0,
            pais = "Inglaterra",
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL
        )
        val exception = assertThrows<Exceptions.NotFoundException>{service.update(9999,entrenador)}
        assertEquals(exception.message, "Integrante no encontrado: Integrante no encontrado con id 9999")

    }

  @Test
  fun delete() {
      val service = ServiceImpl()
      val entrenador = Entrenador(
          id = 1,
          nombre ="Tom",
          apellidos = "Baker",
          fecha_nacimiento = LocalDate.parse("1984-03-20"),
          fecha_incorporacion = LocalDate.parse("2001-05-15"),
          salario = 32000.0,
          pais = "Inglaterra",
          especialidad = Especialidad.ENTRENADOR_PRINCIPAL
      )
      service.save(entrenador)
      val entrenadorDevuelto = service.delete(1)

      assertAll(
          { assertEquals(entrenador.id, entrenadorDevuelto.id) },
          { assertEquals(entrenador.nombre, entrenadorDevuelto.nombre) },
          { assertEquals(entrenador.apellidos, entrenadorDevuelto.apellidos) },
          { assertEquals(entrenador.fecha_nacimiento, entrenadorDevuelto.fecha_nacimiento) },
          { assertEquals(entrenador.fecha_incorporacion, entrenadorDevuelto.fecha_incorporacion) },
          { assertEquals(entrenador.salario, entrenadorDevuelto.salario) },
          { assertEquals(entrenador.pais, entrenadorDevuelto.pais) },
          { assertEquals(entrenador.especialidad, (entrenadorDevuelto as Entrenador).especialidad) },
      )
  }

    @Test
    fun deleteNull(){
        val service = ServiceImpl()
        val exception = assertThrows<Exceptions.NotFoundException>{service.delete(9999)}
        assertEquals(exception.message, "Integrante no encontrado: Integrante no encontrado con id 9999")
    }

  @Test
  fun deleteLogical() {
      val service = ServiceImpl()
      val file = File("data", "personal.csv")
      val entrenador = Entrenador(
          id = 1,
          nombre ="Tom",
          apellidos = "Baker",
          fecha_nacimiento = LocalDate.parse("1984-03-20"),
          fecha_incorporacion = LocalDate.parse("2001-05-15"),
          salario = 32000.0,
          pais = "Inglaterra",
          especialidad = Especialidad.ENTRENADOR_PRINCIPAL
      )
      val entrenador2 = entrenador.copy()
      service.save(entrenador)
      val entrenadorDevuelto = service.deleteLogical(1, entrenador2)
      assertNotEquals(entrenador.isDeleted, entrenadorDevuelto.isDeleted)
  }
    @Test
    fun deleteLogicalNull(){
        val service = ServiceImpl()
        val entrenador = Entrenador(
            id = 1,
            nombre ="Tom",
            apellidos = "Baker",
            fecha_nacimiento = LocalDate.parse("1984-03-20"),
            fecha_incorporacion = LocalDate.parse("2001-05-15"),
            salario = 32000.0,
            pais = "Inglaterra",
            especialidad = Especialidad.ENTRENADOR_PRINCIPAL
        )
        val exception = assertThrows<Exceptions.NotFoundException>{service.deleteLogical(9999, entrenador)}
        assertEquals(exception.message, "Integrante no encontrado: Integrante no encontrado con id 9999")
    }
}