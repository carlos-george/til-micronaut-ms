package br.com.til.adapters.repositories.mongo

import br.com.til.adapters.repositories.documents.Installment
import br.com.til.adapters.repositories.documents.Sales
import br.com.til.adapters.repositories.documents.Vehicle
import br.com.til.application.dtos.InstallmentCoreDTO
import br.com.til.application.dtos.SalesCoreDTO
import br.com.til.application.dtos.VehicleCoreDTO
import br.com.til.application.ports.SalesServicePort
import com.mongodb.client.MongoClient
import jakarta.inject.Singleton

@Singleton
class SalesRepository(
    private val mongoClient: MongoClient
) : SalesServicePort {
    override fun saveSale(salesDTO: SalesCoreDTO) {
        println("----- Sale for save -----")
        println(salesDTO)

        val result = salesDTO.let {
            getConection().insertOne(Sales(
                client = it.client,
                vehicle = Vehicle(
                    idVehicle = it.vehicle.id,
                    brand = it.vehicle.brand,
                    model = it.vehicle.model,
                    plate = it.vehicle.plate
                ),
                value = it.value,
                installments = it.installments.map { item ->
                    Installment(
                        value = item.value,
                        dueDate = item.dueDate
                    )
                }
            ))
        }

        println("-------")
        println(result.insertedId)
    }

    override fun listSales(): List<SalesCoreDTO> {
        val listOfSales = getConection().find().toList()

        println(listOfSales)

        return listOfSales.map {
            SalesCoreDTO(
                id = it.id.toString(),
                client =it.client,
                vehicle = VehicleCoreDTO(
                    id = it.vehicle.idVehicle,
                    brand = it.vehicle.brand,
                    model = it.vehicle.model,
                    plate = it.vehicle.plate
                ),
                value = it.value,
                installments = it.installments.map {
                    InstallmentCoreDTO(
                        value = it.value,
                        dueDate = it.dueDate
                    )
                }
            )
        }
    }

    fun getConection() = mongoClient.getDatabase("VehicleSales")
        .getCollection("sales", Sales::class.java)

}