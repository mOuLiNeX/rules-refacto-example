package fr.manu

private val rules = listOf<DiscountRule>(
        birthdayDiscountRule,
        seniorDiscountRule,
        newCustomerRule,
        oneYearLoyalCustomerRule,
        fiveYearsLoyalCustomerRule,
        tenYearsLoyalCustomerRule,
        oneYearLoyalCustomerBirthdayRule,
        fiveYearsLoyalCustomerBirthdayRule,
        tenYearsLoyalCustomerBirthdayRule
)

fun calculateDiscount(customer: Customer) = RulesDiscountEvaluator(rules).calculateCustomerDiscount(customer)
