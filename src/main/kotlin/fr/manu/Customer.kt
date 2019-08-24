package fr.manu

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period

data class Customer(private val dateOfBirth: LocalDate, private val dateOfFirstPurchase: LocalDateTime? = null) {
    val isBirthday by lazy {
        val age = Period.between(dateOfBirth, LocalDate.now())
        (age.days == 0 && age.months == 0)
    }

    val isSenior by lazy {
        dateOfBirth < LocalDate.now().minusYears(65)
    }

    val isKnown by lazy {
        dateOfFirstPurchase != null
    }

    fun hasBeenLoyalForYears(year: Int): Boolean {
        return if (!isKnown)
            false
        else
            Period.between(dateOfFirstPurchase!!.toLocalDate(), LocalDate.now()).years >= year
    }
}