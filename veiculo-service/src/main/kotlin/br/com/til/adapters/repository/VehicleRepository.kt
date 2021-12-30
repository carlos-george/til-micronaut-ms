package br.com.til.adapters.repository

import br.com.til.adapters.repository.models.Vehicle
import br.com.til.application.core.usecases.dtos.VehicleDTO
import br.com.til.application.core.usecases.ports.IVehicleServicePort
import jakarta.inject.Singleton
import javax.persistence.EntityNotFoundException

@Singleton
class VehicleRepository(
    private val iVehicleRepository: IVehicleRepository
) : IVehicleServicePort {

    override fun create(vehicle: VehicleDTO): VehicleDTO {

        return iVehicleRepository.save(
            Vehicle(
                model = vehicle.model,
                brand = vehicle.brand,
                plate = vehicle.plate
            )
        ).let {vehicleSaved : Vehicle ->
            VehicleDTO(
                id = vehicleSaved.id,
                model = vehicleSaved.model,
                brand = vehicleSaved.brand,
                plate = vehicleSaved.plate
            )
        }

    }

    override fun findById(id: Long): VehicleDTO {

        val vehicleOptional = iVehicleRepository.findById(id)

        return vehicleOptional.let {
            if(!it.isPresent) throw EntityNotFoundException("Vehicle not found")
            it.get()
        }.let { vehicle : Vehicle ->
            VehicleDTO(
                id = vehicle.id,
                model = vehicle.model,
                brand = vehicle.brand,
                plate = vehicle.plate
            )
        }

    }
}