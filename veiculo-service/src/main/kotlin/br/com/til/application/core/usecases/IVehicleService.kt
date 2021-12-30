package br.com.til.application.core.usecases

import br.com.til.application.core.usecases.dtos.VehicleDTO

interface IVehicleService {

    fun create(veiculo: VehicleDTO) : VehicleDTO

    fun findById(id: Long) : VehicleDTO
}