package fr.manu

import java.lang.Math.max
import java.time.LocalDate.now
import java.time.Period.between

fun calculateDiscount(customer: Customer): Double {
    var discount = .0

    if (customer.dateOfBirth < now().minusYears(65)) {
        // senior discount 5%
        discount = .05
    }

    val age = between(customer.dateOfBirth, now())
    if (age.days == 0 && age.months == 0) {
        // birthday 10%
        discount = max(discount, .10)
    }

    if (customer.dateOfFirstPurchase != null) {
        val lastPurchasePeriodInYear = between(customer.dateOfFirstPurchase.toLocalDate(), now()).years
        discount = max(discount, when {
            lastPurchasePeriodInYear >= 10 -> 0.2 // after 10 years, 20%
            lastPurchasePeriodInYear >= 5 -> 0.12 // after 5 years, 12%
            lastPurchasePeriodInYear >= 1 -> 0.1 // after 1 year, loyal customers get 10%
            else -> .0
        })

        if (lastPurchasePeriodInYear >= 1 && age.days == 0 && age.months == 0) {
            // birthday 10%
            discount += .1
        }

    } else {
        // first time purchase discount of 15%
        discount = max(discount, .15)
    }

    return discount
}
