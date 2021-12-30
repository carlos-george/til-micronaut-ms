package br.com.til.adapters.httpclient

import br.com.til.adapters.dtos.VehicleApiDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker

@Client(id = "veiculo-service")
@CircuitBreaker
interface IVehicleClientHttp {

    @Get("/vehicles/{id}")
    fun findById(@PathVariable id: Long) : HttpResponse<VehicleApiDTO>
}