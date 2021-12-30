package br.com.til.application.usecases

import br.com.til.application.dtos.InstallmentDTO
import br.com.til.application.dtos.SalesInputDTO
import br.com.til.application.dtos.SalesOutPutDTO
import br.com.til.application.ports.ISalesProducerPort
import br.com.til.application.ports.IVehicleServicePort
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.time.LocalDate

@Singleton
class SalesServiceImpl(
    private val iVehicleServicePort: IVehicleServicePort,
    private val iSalesProducerPort: ISalesProducerPort
) : ISalesService {

    override fun makeSales(salesDTO: SalesInputDTO): SalesOutPutDTO {

        val vehicleFound = iVehicleServicePort.findVehicleById(salesDTO.vehicleId)

        val valuePerInstallment = salesDTO.value.div(salesDTO.installments.toBigDecimal())
        val installments = (1..salesDTO.installments).map { i: Int ->
            InstallmentDTO(
                value = (i == salesDTO.installments).let {
                        if(it) {
                            getInstallmentValue(valuePerInstallment, salesDTO)
                        } else {
                            valuePerInstallment
                        }

                },
                dueDate = LocalDate.now().plusMonths(i.toLong()).toString()
            )
        }

        val salesOutPutDTO = SalesOutPutDTO(
            client = salesDTO.client,
            vehicle = vehicleFound,
            value = salesDTO.value,
            installments = installments.toList()
        )

        iSalesProducerPort.confirmeSale(salesOutPutDTO)

        return salesOutPutDTO
    }

    private fun getInstallmentValue(
        valuePerInstallment: BigDecimal,
        salesDTO: SalesInputDTO
    ): BigDecimal {


        if ((valuePerInstallment.multiply(salesDTO.installments.toBigDecimal())).compareTo(salesDTO.value) == 0) {

            return valuePerInstallment
        }

        if ((valuePerInstallment.multiply(salesDTO.installments.toBigDecimal())).compareTo(salesDTO.value) < 0) {

            return valuePerInstallment.add(
                valuePerInstallment.multiply(salesDTO.installments.toBigDecimal()).minus(salesDTO.value)
            )
        }

        if ((valuePerInstallment.multiply(salesDTO.installments.toBigDecimal())).compareTo(salesDTO.value) > 0) {

            return valuePerInstallment.minus(
                valuePerInstallment.multiply(salesDTO.installments.toBigDecimal()).minus(salesDTO.value)
            )
        }

        return BigDecimal.ZERO
    }
}
