package br.com.til.adapters.repositories.documents

import br.com.til.adapters.dtos.NoArg
import org.bson.types.ObjectId
import java.math.BigDecimal

@NoArg
data class Installment(
    var id: ObjectId? = null,
    var value: BigDecimal,
    var dueDate: String
)