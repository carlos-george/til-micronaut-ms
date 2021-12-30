package br.com.til.application.usecases

import br.com.til.application.dtos.SalesInputDTO
import br.com.til.application.dtos.SalesOutPutDTO

interface ISalesService {

    fun makeSales(salesDTO: SalesInputDTO) : SalesOutPutDTO
}