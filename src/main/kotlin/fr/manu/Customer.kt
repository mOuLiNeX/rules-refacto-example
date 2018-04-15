package fr.manu

import java.time.LocalDate
import java.time.LocalDateTime

data class Customer(val dateOfBirth: LocalDate, val dateOfFirstPurchase: LocalDateTime? = null)