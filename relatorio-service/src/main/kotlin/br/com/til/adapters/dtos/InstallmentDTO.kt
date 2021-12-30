package br.com.til.adapters.dtos

import java.math.BigDecimal

data class InstallmentDTO(
    val value: BigDecimal,
    val dueDate: String
)