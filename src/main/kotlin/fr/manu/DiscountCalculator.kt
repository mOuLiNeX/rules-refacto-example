package fr.manu

private val rules = listOf<IDiscountRule>(
        BirthdayDiscountRule(),
        SeniorDiscountRule(),
        NewCustomerRule(),
        OneYearLoyalCustomerRule(),
        FiveYearsLoyalCustomerRule(),
        TenYearsLoyalCustomerRule()
)

fun calculateDiscount(customer: Customer) =
        rules.fold(.0, operation = { discount, rule ->
            Math.max(discount, rule.calculateCustomerDiscount(customer))
        })
