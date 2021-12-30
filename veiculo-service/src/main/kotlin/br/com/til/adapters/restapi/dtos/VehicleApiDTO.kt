package br.com.til.adapters.restapi.dtos

data class VehicleApiDTO(
    val id: Long? = null,
    val model: String,
    val brand: String,
    val plate: String
)
