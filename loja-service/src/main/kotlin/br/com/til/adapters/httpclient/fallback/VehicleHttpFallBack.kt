package br.com.til.adapters.httpclient.fallback

import br.com.til.adapters.dtos.VehicleApiDTO
import br.com.til.adapters.httpclient.IVehicleClientHttp
import br.com.til.adapters.redis.IVehicleRedisService
import io.micronaut.http.HttpResponse
import io.micronaut.retry.annotation.Fallback

@Fallback
class VehicleHttpFallBack(
    private val iVehicleRedisService: IVehicleRedisService
): IVehicleClientHttp {

    override fun findById(id: Long): HttpResponse<VehicleApiDTO> {

        val vehicleDTO = iVehicleRedisService.findInCache(id)

        return HttpResponse.ok(
            VehicleApiDTO(
                id = vehicleDTO.id,
                model = vehicleDTO.model,
                brand = vehicleDTO.brand,
                plate = vehicleDTO.plate
            )
        )

    }
}