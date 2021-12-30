package br.com.til.adapters.dtos

data class VehicleApiDTO(
    val id: Long? = null,
    val model: String,
    val brand: String,
    val plate: String
)
