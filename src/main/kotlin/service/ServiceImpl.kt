package org.example.service

import org.example.Cache.CacheImpl
import org.example.exceptions.Exceptions
import org.example.models.Integrante
import org.example.repositories.EquipoRepositoryImpl
import org.example.storage.EquipoStorageBIN
import org.example.storage.EquipoStorageCSV
import org.example.storage.EquipoStorageJSON
import org.example.storage.EquipoStorageXML
import org.example.validator.IntegranteValidator
import org.lighthousegames.logging.logging
import java.io.File

private val CACHE_SIZE = 5

class ServiceImpl(
    private val repository: EquipoRepositoryImpl = EquipoRepositoryImpl(),
    private val cache: CacheImpl<Long, Integrante> = CacheImpl(CACHE_SIZE),
    private val validator: IntegranteValidator = IntegranteValidator(),
    private val storageCSV: EquipoStorageCSV = EquipoStorageCSV(),
    private val storageJSON: EquipoStorageJSON = EquipoStorageJSON(),
    private val storageXML: EquipoStorageXML = EquipoStorageXML(),
    private val storageBIN: EquipoStorageBIN = EquipoStorageBIN()
): Service {
    private val logger = logging()


    override fun importFromFile(filePath: String) {
        logger.debug { "Importando integrantes del fichero $filePath" }

        val file = File(filePath)
        val equipo : List<Integrante>

        when {
            file.name.endsWith(".csv") -> {
                equipo = storageCSV.fileRead(file)
                equipo.forEach { repository.save(it) }
            }
            file.name.endsWith(".json") -> {
                equipo = storageJSON.fileRead(file)
                equipo.forEach { repository.save(it) }
            }
            file.name.endsWith(".xml") -> {
                equipo = storageXML.fileRead(file)
                equipo.forEach { repository.save(it) }
            }
            file.name.endsWith(".bin") -> {
                equipo = storageBIN.fileRead(file)
                equipo.forEach { repository.save(it) }
            }
        }
    }

    override fun exportToFile(filePath: String) {
        logger.debug { "Exportando integrantes al fichero $filePath" }

        val file = File(filePath)

        when {
            file.name.endsWith(".csv") -> {
                storageCSV.fileWrite(repository.getAll(),file)
            }
            file.name.endsWith(".json") -> {
                storageJSON.fileWrite(repository.getAll(),file)
            }
            file.name.endsWith(".xml") -> {
                storageXML.fileWrite(repository.getAll(),file)
            }
            file.name.endsWith(".bin") -> {
                storageBIN.fileWrite(repository.getAll(),file)
            }
        }
    }

    override fun getAll(): List<Integrante> {
        logger.debug { "Obteniendo todos los integrantes del equipo" }
        return repository.getAll()
    }

    override fun getById(id: Long): Integrante {
        logger.debug { "Obteniendo el integrante del equipo con id $id" }

        var result = cache.get(id)

        if (result == null){

            result = repository.getById(id)

            if (result == null){
                throw Exceptions.NotFoundException("Integrante no encontrado con id $id")
            } else {
                cache.put(id,result)
                return result
            }
        }

        return result
    }

    override fun save(integrante: Integrante): Integrante {
        logger.debug { "Guardando integrante" }
        validator.validar(integrante)
        return repository.save(integrante)
    }

    override fun update(id: Long, integrante: Integrante): Integrante {
        logger.debug { "Actualizando integrante" }
        validator.validar(integrante)

        val actualizado: Integrante? = repository.update(id,integrante)

        if (actualizado == null) {
            throw Exceptions.NotFoundException("Integrante no encontrado con id $id")
        } else {
            cache.remove(id)
        }

        return actualizado
    }

    override fun delete(id: Long): Integrante {
        logger.debug { "Borrando integrante" }

        val borrado: Integrante? = repository.delete(id)

        if (borrado == null) {
            throw Exceptions.NotFoundException("Integrante no encontrado con id $id")
        } else {
            cache.remove(id)
        }

        return borrado
    }

    override fun deleteLogical(id: Long, integrante: Integrante): Integrante {
        logger.debug { "Borrando lógicamente integrante" }

        val borrado: Integrante? = repository.deleteLogical(id,integrante)

        if (borrado == null) {
            throw Exceptions.NotFoundException("Integrante no encontrado con id $id")
        } else {
            cache.remove(id)
        }

        return borrado
    }
}