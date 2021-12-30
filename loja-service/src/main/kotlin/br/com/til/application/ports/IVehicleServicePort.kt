package br.com.til.application.ports

import br.com.til.application.dtos.VehicleDTO

interface IVehicleServicePort {

    fun findVehicleById(id: Long) : VehicleDTO
}