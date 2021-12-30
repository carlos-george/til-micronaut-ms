package br.com.til.adapters.kafka.producer

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface ISalesProducer {

    @Topic("ms-vendas")
    fun salesPublisher(@KafkaKey id: String, saleJSON: String)
}