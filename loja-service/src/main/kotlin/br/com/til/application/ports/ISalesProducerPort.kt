package br.com.til.application.ports

import br.com.til.application.dtos.SalesOutPutDTO

interface ISalesProducerPort {

    fun confirmeSale(salesOutPutDTO: SalesOutPutDTO)
}