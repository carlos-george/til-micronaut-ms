package br.com.til.adapters.restapi.controllers

import br.com.til.adapters.repository.VehicleRepository
import br.com.til.adapters.restapi.dtos.VehicleApiDTO
import br.com.til.application.core.usecases.IVehicleService
import br.com.til.application.core.usecases.VehicleServiceImpl
import br.com.til.application.core.usecases.dtos.VehicleDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.exceptions.HttpStatusException
import javax.persistence.EntityNotFoundException

@Controller("/vehicles")
class VehicleController(
    private val vehicleRepository: VehicleRepository
) {
    private var iVehicleService: IVehicleService = VehicleServiceImpl(
           iVehicleServicePort = vehicleRepository
        )

    @Post
    fun create(vehicleDTO: VehicleApiDTO) : HttpResponse<VehicleApiDTO> {

        val vehicleSavedDTO = iVehicleService.create(
            VehicleDTO(
                model = vehicleDTO.model,
                brand = vehicleDTO.brand,
                plate = vehicleDTO.plate
            )
        ).let { (id, model, brand, plate): VehicleDTO ->
            VehicleApiDTO(
                id = id,
                model = model,
                brand = brand,
                plate = plate
            )
        }

        return HttpResponse.created(vehicleSavedDTO)
    }

    @Get("/{id}")
    fun findById(@PathVariable id: Long) : HttpResponse<VehicleApiDTO> {
        val vehicleFoundDTO: VehicleApiDTO = try {
            iVehicleService.findById(id)
                .let { vehicle: VehicleDTO ->

                    VehicleApiDTO(
                        id = vehicle.id,
                        model = vehicle.model,
                        brand = vehicle.brand,
                        plate = vehicle.plate
                    )
                }
        } catch (ex: EntityNotFoundException) {
            println("Exception: ${ex.message}")
            throw HttpClientResponseException(ex.message, HttpResponse.badRequest(ex.message))
        }
        return HttpResponse.ok(vehicleFoundDTO)
    }
}