package fr.manu

interface IDiscountRule {
    fun calculateCustomerDiscount(customer: Customer): Double
}

class BirthdayDiscountRule : IDiscountRule {
    override fun calculateCustomerDiscount(customer: Customer): Double = if (customer.isBirthday) 0.1 else .0
}

class NewCustomerRule : IDiscountRule {
    override fun calculateCustomerDiscount(customer: Customer): Double = if (!customer.isKnown) 0.15 else .0
}

class SeniorDiscountRule : IDiscountRule {
    override fun calculateCustomerDiscount(customer: Customer): Double = if (customer.isSenior) 0.05 else .0
}

abstract class LoyalCustomerRule(private val yearsAsCustomer: Int, private val discount: Double) : IDiscountRule {
    override fun calculateCustomerDiscount(customer: Customer): Double =
            if (customer.hasBeenLoyalForYears(yearsAsCustomer)) {
                // On réutilise la règle de l'anniversaire plutôt que de dupliquer le code
                discount + BirthdayDiscountRule().calculateCustomerDiscount(customer)
            } else .0
}

class OneYearLoyalCustomerRule() : LoyalCustomerRule(1, 0.1)
class FiveYearsLoyalCustomerRule() : LoyalCustomerRule(5, 0.12)
class TenYearsLoyalCustomerRule() : LoyalCustomerRule(10, 0.2)