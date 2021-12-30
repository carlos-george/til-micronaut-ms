package br.com.til.adapters.httpclient

import br.com.til.adapters.dtos.VehicleApiDTO
import br.com.til.adapters.exceptions.VehicleNotFoundException
import br.com.til.adapters.redis.IVehicleRedisService
import br.com.til.application.dtos.VehicleDTO
import br.com.til.application.ports.IVehicleServicePort
import io.micronaut.http.client.exceptions.HttpClientResponseException
import jakarta.inject.Singleton

@Singleton
class VehicleHttpImpl(
    private val iVehicleClientHttp: IVehicleClientHttp,
    private val iVehicleRedisService: IVehicleRedisService
) : IVehicleServicePort {
    override fun findVehicleById(id: Long): VehicleDTO {

        return iVehicleClientHttp.findById(id).body()
            .let{
                it ?: throw VehicleNotFoundException(message = "Vehicle not found.")
                VehicleDTO(
                    id = it.id,
                    model = it.model,
                    brand = it.brand,
                    plate = it.plate
                )
            }
            .let {
               iVehicleRedisService.saveToCache(it)
                it
            }
    }

}