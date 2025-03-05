package org.example.exceptions

import ch.qos.logback.classic.ViewStatusMessagesServlet

/**
 * Clase que almacena las excepciones que se manejan en el proyecto
 * @property message [String] Mensaje de salida de la excepcion
 */
sealed class Exceptions (message: String) : Exception(message) {
    class InvalidoException (message: String) : Exceptions ("Integrante no válido: $message")
    class StorageException (message: String) : Exceptions ("Error en el storage: $message")
    class NotFoundException (message: String) : Exceptions ("Integrante no encontrado: $message")
}