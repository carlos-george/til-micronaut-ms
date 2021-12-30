package br.com.til.adapters.dtos

import java.math.BigDecimal

data class SalesDTO(
    val client: String,
    val vehicle: VehicleDTO,
    val value: BigDecimal,
    val installments: List<InstallmentDTO>
)