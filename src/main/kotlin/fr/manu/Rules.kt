package fr.manu

import java.util.function.Function
import java.util.function.Predicate

val birthdayDiscountRule = DiscountRule({ it.isBirthday }, { 0.1 })

val newCustomerRule = DiscountRule({ !it.isKnown }, { 0.15 })

val seniorDiscountRule = DiscountRule({ it.isSenior }, { 0.05 })

private fun loyalCustomerMatcher(yearsAsCustomer: Int): Predicate<Customer> = Predicate { it.hasBeenLoyalForYears(yearsAsCustomer) }
private fun loyalCustomerTransformer(discount: Double): Function<Customer, Double> = Function { discount }

val oneYearLoyalCustomerRule = DiscountRule(loyalCustomerMatcher(1), loyalCustomerTransformer(0.1))
val fiveYearsLoyalCustomerRule = DiscountRule(loyalCustomerMatcher(5), loyalCustomerTransformer(0.12))
val tenYearsLoyalCustomerRule = DiscountRule(loyalCustomerMatcher(10), loyalCustomerTransformer(0.2))

// Composition of predicate/function with Java8 ;-)
fun loyalCustomerBirthdayRule(loyalCustomerRule: DiscountRule) = DiscountRule(
        matcher = loyalCustomerRule.matcher.and(birthdayDiscountRule.matcher),
        transformer = loyalCustomerRule.transformer.andThen { discount: Double -> discount + 0.1 })

val oneYearLoyalCustomerBirthdayRule = loyalCustomerBirthdayRule(oneYearLoyalCustomerRule)
val fiveYearsLoyalCustomerBirthdayRule = loyalCustomerBirthdayRule(fiveYearsLoyalCustomerRule)
val tenYearsLoyalCustomerBirthdayRule = loyalCustomerBirthdayRule(tenYearsLoyalCustomerRule)
