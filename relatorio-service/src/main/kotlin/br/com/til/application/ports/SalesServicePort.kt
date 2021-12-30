package br.com.til.application.ports

import br.com.til.application.dtos.SalesCoreDTO

interface SalesServicePort {

    fun saveSale(salesDTO: SalesCoreDTO)

    fun listSales() : List<SalesCoreDTO>
}