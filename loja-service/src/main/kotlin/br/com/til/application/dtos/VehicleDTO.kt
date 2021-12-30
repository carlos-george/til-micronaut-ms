package br.com.til.application.dtos

data class VehicleDTO(
    val id: Long? = null,
    val model: String,
    val brand: String,
    val plate: String
)
