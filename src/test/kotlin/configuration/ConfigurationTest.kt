package configuration

import org.example.configuration.Configuration
import org.example.configuration.ConfigurationProperties
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.pathString

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class ConfigurationTest {

 @Test
 @DisplayName("Leer la configuracion")
 fun cargarConfiguracion() {
  val configurationProperties = Configuration.configurationProperties

  val expectedDataDir = Path.of(System.getProperty("user.dir"), "data").pathString
  val expectedBackupDir = Path.of(System.getProperty("user.dir"), "backup").pathString

  assertEquals(expectedDataDir,configurationProperties.dataDirectory)
  assertEquals(expectedBackupDir,configurationProperties.backupDirectory)

  assertTrue(Files.exists(Path.of(configurationProperties.dataDirectory)), "El directorio dataTest deberia existir")
  assertTrue(Files.exists(Path.of(configurationProperties.backupDirectory)), "El directorio backupTest deberia existir")
 }

 @Test
 @DisplayName("Leer configuracion con valores por defecto")
 fun leerDeafultValues(){
  val configDeafult = Configuration.configurationProperties

  val expectedDataDir = Path.of(System.getProperty("user.dir"), "data").pathString
  val expectedBackupDir = Path.of(System.getProperty("user.dir"), "backup").pathString

  assertEquals(expectedDataDir, configDeafult.dataDirectory)
  assertEquals(expectedBackupDir,configDeafult.backupDirectory)
 }
}