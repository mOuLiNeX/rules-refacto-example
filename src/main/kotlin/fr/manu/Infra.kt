package fr.manu

import java.util.function.Function
import java.util.function.Predicate

typealias DiscountFilter = (Customer) -> Boolean

typealias DiscountCalculator = (Customer) -> Double

class DiscountRule(val matcher: Predicate<Customer>, val transformer: Function<Customer, Double>) {
    constructor(filter: DiscountFilter, transform: DiscountCalculator) : this(Predicate { filter(it) }, Function<Customer, Double> { transform(it) })

    fun match(customer: Customer): Boolean = matcher.test(customer)
    fun calculateCustomerDiscount(customer: Customer): Double = transformer.apply(customer)
}

class RulesDiscountEvaluator(private val rules: List<DiscountRule>) {
    constructor(rule: DiscountRule) : this(listOf(rule))

    fun calculateCustomerDiscount(customer: Customer) =
            rules
                    .filter { it.match(customer) }
                    .fold(.0, operation = { discount, rule ->
                        Math.max(discount, rule.calculateCustomerDiscount(customer))
                    })
}