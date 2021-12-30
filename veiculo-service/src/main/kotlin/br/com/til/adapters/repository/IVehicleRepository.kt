package br.com.til.adapters.repository

import br.com.til.adapters.repository.models.Vehicle
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface IVehicleRepository : JpaRepository<Vehicle, Long> {
}