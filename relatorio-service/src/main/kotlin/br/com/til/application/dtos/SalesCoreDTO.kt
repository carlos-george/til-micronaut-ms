package br.com.til.application.dtos

import java.math.BigDecimal

data class SalesCoreDTO(
    val id: String? = null,
    val client: String,
    val vehicle: VehicleCoreDTO,
    val value: BigDecimal,
    val installments: List<InstallmentCoreDTO>
)
