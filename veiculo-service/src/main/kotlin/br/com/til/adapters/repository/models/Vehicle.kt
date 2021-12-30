package br.com.til.adapters.repository.models

import javax.persistence.*

@Entity
@Table(name = "VEHICLES")
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val model: String,
    val brand: String,
    val plate: String
)
