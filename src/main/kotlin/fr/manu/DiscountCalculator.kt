package fr.manu

import java.lang.Math.max

fun calculateDiscount(customer: Customer): Double {
    var discount = .0

    if (customer.isSenior) {
        // senior discount 5%
        discount = .05
    }

    if (customer.isBirthday) {
        // birthday 10%
        discount = max(discount, .10)
    }

    if (customer.isKnown) {
        discount = max(discount, when {
            customer.hasBeenLoyalForYears(10) -> 0.2 // after 10 years, 20%
            customer.hasBeenLoyalForYears(5) -> 0.12 // after 5 years, 12%
            customer.hasBeenLoyalForYears(1) -> 0.1 // after 1 year, loyal customers get 10%
            else -> .0
        })

        if (customer.hasBeenLoyalForYears(1) && customer.isBirthday) {
            // birthday 10%
            discount += .1
        }

    } else {
        // first time purchase discount of 15%
        discount = max(discount, .15)
    }

    return discount
}
