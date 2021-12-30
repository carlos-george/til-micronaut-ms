package br.com.til.adapters.kafka.consumer

import br.com.til.adapters.dtos.SalesDTO
import br.com.til.adapters.repositories.mongo.SalesRepository
import br.com.til.application.dtos.InstallmentCoreDTO
import br.com.til.application.dtos.SalesCoreDTO
import br.com.til.application.dtos.VehicleCoreDTO
import br.com.til.application.usecases.SalesServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class SalesConsumer(
    private val salesRepository: SalesRepository,
    private val objectMapper: ObjectMapper
) {

    val saveSalesService = SalesServiceImpl(salesRepository)

    @Topic("ms-vendas")
    fun receiverSale(id: String, salesJSON: String) {

        val salesInPutDTO = objectMapper.readValue(salesJSON, SalesDTO::class.java)
            .let {
                SalesCoreDTO(
                    client = it.client,
                    vehicle = VehicleCoreDTO(
                        id = it.vehicle.id,
                        model = it.vehicle.model,
                        brand = it.vehicle.brand,
                        plate = it.vehicle.plate
                    ),
                    value = it.value,
                    installments = it.installments.map { installment ->
                        InstallmentCoreDTO(
                            installment.value,
                            installment.dueDate
                        )
                    }
                )
            }

        saveSalesService.saveSales(salesInPutDTO)

    }
}