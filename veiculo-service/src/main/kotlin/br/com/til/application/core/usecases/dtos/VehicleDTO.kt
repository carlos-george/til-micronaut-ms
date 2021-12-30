package br.com.til.application.core.usecases.dtos

data class VehicleDTO(
    val id: Long? = null,
    val model: String,
    val brand: String,
    val plate: String
)
