package org.example.exceptions

sealed class IntegranteExceptions (message: String) : Exception(message) {
    class IntegranteInvalidoException (message: String) : IntegranteExceptions ("Integrante no válido: $message")
}