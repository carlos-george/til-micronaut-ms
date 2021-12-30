package br.com.til.adapters.dtos

import java.math.BigDecimal
import java.math.BigInteger

data class SalesRequest(
    val client: String,
    val vehicleId: Long,
    val value: BigDecimal,
    val installments: Int
)
