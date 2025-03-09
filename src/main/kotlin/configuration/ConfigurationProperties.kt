package org.example.configuration

/**
 * Clase que representa las propiedades de la configuración del programa
 * @property dataDirectory directorio del que se leerán los ficheros para cargar información
 * @property backupDirectory directorio en el que se exportarán los ficheros
 */
data class ConfigurationProperties(
    val dataDirectory: String,
    val backupDirectory: String,
)
