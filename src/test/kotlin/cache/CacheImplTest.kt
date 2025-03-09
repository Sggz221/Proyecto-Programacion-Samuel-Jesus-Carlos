package cache

import org.example.Cache.CacheImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CacheImplTest {
    val cache = CacheImpl<String,String> (3)

    @BeforeEach
    fun setup() {
        cache.clear() //Limpiamos la cache antes de cada test para que los test anteriores no condicionen los siguientes
    }

    @Test
    fun cacheLRU() {

        //Llenamos la cache (tiene 3 de tamaño)
        cache.put("clave1", "valor1")
        cache.put("clave2", "valor2")
        cache.put("clave3", "valor3")

        //Consultamos clave2, así es el más recientemente usado
        cache.get("clave2")

        //Ahora, al añadir otro valor, se debería eliminar el menos utilizado (valor 1)
        cache.put("clave4", "valor4")

        assertNull(cache.get("clave1")) //No debería estar
        assertNotNull(cache.get("clave3")) //Debería estar
        assertNotNull(cache.get("clave2")) //Debería estar
        assertNotNull(cache.get("clave4")) //Debería estar
    }

    @Test
    fun eliminarElemento () {
        cache.put("clave1", "valor1")
        cache.remove("clave1")
        assertNull(cache.get("clave1"))
    }

    @Test
    fun limpiarCache() {
        cache.put("clave1", "valor1")
        cache.put("clave2", "valor2")
        cache.put("clave3", "valor3")

        cache.clear()

        assertNull(cache.get("clave1"))
        assertNull(cache.get("clave2"))
        assertNull(cache.get("clave3"))
    }

    @Test
    fun tamanoCache() {
        val size = cache.size()

        assertEquals(0, size)
    }

    @Test
    fun clavesCache(){
        cache.put("clave1", "valor1")
        val claves:Set<String> = cache.keys()

        assertEquals(setOf("clave1"), claves)
    }

    @Test
    fun valoresCache(){
        cache.put("clave1", "valor1")
        val valores: Collection<String> = cache.values()

        assertTrue(valores.contains("valor1"))

    }

    @Test
    fun entradasCache(){
        cache.put("clave1", "valor1")
        val entradas: Set<Map.Entry<String, String>> = cache.entries()

        assertEquals("valor1", cache.get("clave1"))
    }
}