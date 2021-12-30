package br.com.til.adapters.repositories.documents

import br.com.til.adapters.dtos.NoArg
import org.bson.types.ObjectId

@NoArg
data class Vehicle(
    var id: ObjectId? = null,
    var idVehicle: Long?,
    var model: String,
    var brand: String,
    var plate: String
)
