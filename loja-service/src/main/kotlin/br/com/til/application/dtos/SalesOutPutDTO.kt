package br.com.til.application.dtos

import br.com.til.adapters.dtos.InstallmentOutDTO
import java.math.BigDecimal

data class SalesOutPutDTO(
    val client: String,
    val vehicle: VehicleDTO,
    val value: BigDecimal,
    val installments: List<InstallmentDTO>
)
