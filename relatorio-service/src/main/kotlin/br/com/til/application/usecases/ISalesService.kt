package br.com.til.application.usecases

import br.com.til.application.dtos.SalesCoreDTO

interface ISalesService {

    fun saveSales(salesDTO: SalesCoreDTO)

    fun listSales() : List<SalesCoreDTO>
}