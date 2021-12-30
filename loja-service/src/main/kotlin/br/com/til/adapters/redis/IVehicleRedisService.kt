package br.com.til.adapters.redis

import br.com.til.application.dtos.VehicleDTO


interface IVehicleRedisService {

    fun saveToCache(vehicle: VehicleDTO)

    fun findInCache(id: Long) : VehicleDTO
}