package br.com.til.adapters.restapi.controller

import br.com.til.adapters.dtos.InstallmentOutDTO
import br.com.til.adapters.dtos.SalesRequest
import br.com.til.adapters.dtos.SalesResponse
import br.com.til.adapters.dtos.VehicleApiDTO
import br.com.til.adapters.exceptions.VehicleNotFoundException
import br.com.til.adapters.httpclient.VehicleHttpImpl
import br.com.til.adapters.kafka.producer.SalesProducerImpl
import br.com.til.application.dtos.InstallmentDTO
import br.com.til.application.dtos.SalesInputDTO
import br.com.til.application.usecases.ISalesService
import br.com.til.application.usecases.SalesServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.exceptions.HttpStatusException

@Controller("/sales")
class SalesController(
    private val vehicleHttpImpl: VehicleHttpImpl,
    private val salesProducerImpl: SalesProducerImpl
) {

    private val iSalesService: ISalesService = SalesServiceImpl(
        vehicleHttpImpl,
        salesProducerImpl
    )

    @Post
    fun makeSales(@Body salesRequest: SalesRequest) : HttpResponse<SalesResponse>{
        val salesOutPutDTO = try {
            iSalesService.makeSales(
                SalesInputDTO(
                    client = salesRequest.client,
                    vehicleId = salesRequest.vehicleId,
                    value = salesRequest.value,
                    installments = salesRequest.installments,
                )
            )
        } catch (ex: VehicleNotFoundException) {
            throw HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
        } catch (ex: HttpClientResponseException) {
            throw HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
        }

        return HttpResponse.ok(
            SalesResponse(
            client = salesOutPutDTO.client,
                vehicle = VehicleApiDTO(
                    id = salesOutPutDTO.vehicle.id,
                    brand = salesOutPutDTO.vehicle.brand,
                    model = salesOutPutDTO.vehicle.model,
                    plate = salesOutPutDTO.vehicle.plate
                ),
                value = salesOutPutDTO.value,
                installments = salesOutPutDTO.installments.map { installment: InstallmentDTO ->
                    InstallmentOutDTO(
                        value = installment.value,
                        dueDate = installment.dueDate
                    )
                }
        ))
    }
}