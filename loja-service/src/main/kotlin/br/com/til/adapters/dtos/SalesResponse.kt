package br.com.til.adapters.dtos

import java.math.BigDecimal
import java.math.BigInteger

data class SalesResponse(
    val client: String,
    val vehicle: VehicleApiDTO,
    val value: BigDecimal,
    val installments: List<InstallmentOutDTO>
)
