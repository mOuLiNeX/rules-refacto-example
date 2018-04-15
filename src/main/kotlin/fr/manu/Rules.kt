package fr.manu

class BirthdayDiscountRule : IDiscountRule {
    override fun match(customer: Customer): Boolean = customer.isBirthday
    override fun calculateCustomerDiscount(customer: Customer): Double = 0.1
}

class NewCustomerRule : IDiscountRule {
    override fun match(customer: Customer): Boolean = !customer.isKnown
    override fun calculateCustomerDiscount(customer: Customer): Double = 0.15
}

class SeniorDiscountRule : IDiscountRule {
    override fun match(customer: Customer): Boolean = customer.isSenior
    override fun calculateCustomerDiscount(customer: Customer): Double = 0.05
}

abstract class LoyalCustomerRule(private val yearsAsCustomer: Int, private val discount: Double) : ComposableDiscountRule() {
    override fun match(customer: Customer): Boolean = customer.hasBeenLoyalForYears(yearsAsCustomer)

    // On ne se soucie plus de la règle de l'anniversaire qui est traitée à part
    override fun localCalculateCustomerDiscount(customer: Customer) = discount
}

class OneYearLoyalCustomerRule : LoyalCustomerRule(1, 0.1)
class FiveYearsLoyalCustomerRule : LoyalCustomerRule(5, 0.12)
class TenYearsLoyalCustomerRule : LoyalCustomerRule(10, 0.2)

class LoyalCustomerBirthdayRule : CompositeDiscountRule {
    override fun match(customer: Customer): Boolean = customer.isBirthday
    override fun calculateCustomerDiscount(customer: Customer, discount: Double): Double = discount + 0.1
}