package br.com.til.adapters.rest.api.controllers

import br.com.til.adapters.repositories.mongo.SalesRepository
import br.com.til.application.dtos.SalesCoreDTO
import br.com.til.application.usecases.ISalesService
import br.com.til.application.usecases.SalesServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/sales")
class SalesReportController(
    private val salesRepository: SalesRepository
) {

    private val iSalesService: ISalesService = SalesServiceImpl(salesRepository)

    @Get()
    fun findAll() : HttpResponse<List<SalesCoreDTO>> = HttpResponse.ok(iSalesService.listSales())
}