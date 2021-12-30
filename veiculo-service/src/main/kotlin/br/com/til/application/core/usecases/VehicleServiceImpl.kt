package br.com.til.application.core.usecases

import br.com.til.application.core.usecases.dtos.VehicleDTO
import br.com.til.application.core.usecases.ports.IVehicleServicePort
import jakarta.inject.Singleton

@Singleton
class VehicleServiceImpl(
    private  val iVehicleServicePort: IVehicleServicePort
) : IVehicleService {

    override fun create(veiculo: VehicleDTO): VehicleDTO {
        return iVehicleServicePort.create(veiculo);
    }

    override fun findById(id: Long): VehicleDTO {
        return iVehicleServicePort.findById(id).let { vehicle: VehicleDTO ->
            println("Vehicle 3: $vehicle")
            vehicle
        }
    }
}