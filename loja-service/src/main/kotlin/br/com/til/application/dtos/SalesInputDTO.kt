package br.com.til.application.dtos

import java.math.BigDecimal

data class SalesInputDTO(
    val client: String,
    val vehicleId: Long,
    val value: BigDecimal,
    val installments: Int
)
