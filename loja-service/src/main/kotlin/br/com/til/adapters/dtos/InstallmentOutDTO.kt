package br.com.til.adapters.dtos

import java.math.BigDecimal

data class InstallmentOutDTO(
    val value: BigDecimal,
    val dueDate: String
)
