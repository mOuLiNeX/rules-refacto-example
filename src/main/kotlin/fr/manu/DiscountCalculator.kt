package fr.manu

private val rules = listOf(
        BirthdayDiscountRule(),
        SeniorDiscountRule(),
        NewCustomerRule(),
        OneYearLoyalCustomerRule().withDependantRule(LoyalCustomerBirthdayRule()),
        FiveYearsLoyalCustomerRule().withDependantRule(LoyalCustomerBirthdayRule()),
        TenYearsLoyalCustomerRule().withDependantRule(LoyalCustomerBirthdayRule())
)

fun calculateDiscount(customer: Customer) =
        RulesDiscountEvaluator(rules).calculateCustomerDiscount(customer)
