package br.com.til.adapters.kafka.producer

import br.com.til.application.dtos.SalesOutPutDTO
import br.com.til.application.ports.ISalesProducerPort
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Singleton
import java.util.*

@Singleton
class SalesProducerImpl(
    private val iSalesProducer: ISalesProducer,
    private val objectMapper: ObjectMapper
) : ISalesProducerPort{

    override fun confirmeSale(salesOutPutDTO: SalesOutPutDTO) {
        val salesJSON = objectMapper.writeValueAsString(salesOutPutDTO)

        iSalesProducer.salesPublisher(UUID.randomUUID().toString(), salesJSON)
    }
}