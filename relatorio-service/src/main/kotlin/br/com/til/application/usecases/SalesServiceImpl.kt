package br.com.til.application.usecases

import br.com.til.application.dtos.SalesCoreDTO
import br.com.til.application.ports.SalesServicePort
import jakarta.inject.Singleton

@Singleton
class SalesServiceImpl(
    private val salesServicePort: SalesServicePort
): ISalesService {

    override fun saveSales(salesDTO: SalesCoreDTO) = salesServicePort.saveSale(salesDTO)

    override fun listSales(): List<SalesCoreDTO> = salesServicePort.listSales()
}