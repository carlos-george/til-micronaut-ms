package br.com.til.adapters.redis

import br.com.til.adapters.redis.config.RedisConnection
import br.com.til.application.dtos.VehicleDTO
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Singleton
class VehicleRedisServiceImpl(
    private val objectMapper: ObjectMapper
) : IVehicleRedisService {
    
    override fun findInCache(id: Long): VehicleDTO {

        val jedis = RedisConnection.getConnection()

        val vehicleJson = jedis.get(id.toString())

        val vehicleDTO: VehicleDTO = objectMapper.readValue(vehicleJson, VehicleDTO::class.java)

        return VehicleDTO(
            id = vehicleDTO.id,
            brand = vehicleDTO.brand,
            model = vehicleDTO.model,
            plate = vehicleDTO.plate
        )
    }

    override fun saveToCache(vehicle: VehicleDTO) {

        val jedis = RedisConnection.getConnection()
        val vehicleJson = objectMapper.writeValueAsString(vehicle)

        jedis.set(vehicle.id.toString(), vehicleJson)
    }
}