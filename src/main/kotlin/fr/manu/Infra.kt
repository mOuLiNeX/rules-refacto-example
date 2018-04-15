package fr.manu

import java.util.*

interface IDiscountMatcher {
    fun match(customer: Customer): Boolean
}

// Interface de toutes les règles
interface IDiscountRule : IDiscountMatcher {
    fun calculateCustomerDiscount(customer: Customer): Double
}

// Pour imbrication de règles : un règle composable peut être enrichi de règle composite
abstract class ComposableDiscountRule(private val dependantRules: MutableList<CompositeDiscountRule> = LinkedList()) : IDiscountRule, IDiscountMatcher {
    fun withDependantRule(rule: CompositeDiscountRule): ComposableDiscountRule {
        dependantRules.add(rule)
        return this
    }

    abstract fun localCalculateCustomerDiscount(customer: Customer): Double

    override fun calculateCustomerDiscount(customer: Customer): Double {
        val initial = localCalculateCustomerDiscount(customer)
        return dependantRules
                .filter { this.match(customer) && it.match(customer) }
                .fold(initial, operation = { discount, rule -> rule.calculateCustomerDiscount(customer, discount) })
    }
}

interface CompositeDiscountRule : IDiscountMatcher {
    fun calculateCustomerDiscount(customer: Customer, acc: Double): Double
}

class RulesDiscountEvaluator(private val rules: List<IDiscountRule>) {
    constructor(rule: IDiscountRule) : this(listOf(rule))

    fun calculateCustomerDiscount(customer: Customer) =
            rules
                    .filter { it.match(customer) }
                    .fold(.0, operation = { discount, rule ->
                        Math.max(discount, rule.calculateCustomerDiscount(customer))
                    })
}