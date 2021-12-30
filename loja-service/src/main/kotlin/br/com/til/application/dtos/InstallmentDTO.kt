package br.com.til.application.dtos

import java.math.BigDecimal

data class InstallmentDTO(
    val value: BigDecimal,
    val dueDate: String
)
