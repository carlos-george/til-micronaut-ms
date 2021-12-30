package br.com.til.adapters.repositories.documents

import br.com.til.adapters.dtos.NoArg
import org.bson.types.ObjectId
import java.math.BigDecimal

@NoArg
data class Sales(
    var id: ObjectId? = null,
    var client: String,
    var vehicle: Vehicle,
    var value: BigDecimal,
    var installments: List<Installment>
)