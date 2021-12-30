package br.com.til.application.core.usecases.ports

import br.com.til.application.core.usecases.dtos.VehicleDTO

interface IVehicleServicePort {

    fun create(vehicle: VehicleDTO) : VehicleDTO

    fun findById(id: Long) : VehicleDTO
}