package fr.manu

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.Before
import org.junit.Test
import java.lang.Math.random

class RulesTest {
    private lateinit var client: Customer

    @Before
    fun setUp() {
        client = mock()
    }

    @Test
    fun applyBirthdayRule() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(BirthdayDiscountRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun dontApplyBirthdayRule() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(BirthdayDiscountRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applySeniorRule() {
        // GIVEN
        whenever(client.isSenior).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(SeniorDiscountRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.05)
    }

    @Test
    fun dontApplySeniorRule() {
        // GIVEN
        whenever(client.isSenior).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(SeniorDiscountRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyNewCustomerRule() {
        // GIVEN
        whenever(client.isKnown).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(NewCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.15)
    }

    @Test
    fun dontApplyNewCustomerRule() {
        // GIVEN
        whenever(client.isKnown).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(NewCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyOneYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(OneYearLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun dontApplyOneYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(OneYearLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyFiveYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(FiveYearsLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.12)
    }

    @Test
    fun dontApplyFiveYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(FiveYearsLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyTenYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(true)

        // WHEN
        val discount = RulesDiscountEvaluator(TenYearsLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.2)
    }

    @Test
    fun dontApplyTenYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(false)

        // WHEN
        val discount = RulesDiscountEvaluator(TenYearsLoyalCustomerRule()).calculateCustomerDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyLoyalCustomerBirthdayRule() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(true)
        val discount = random()

        // WHEN
        val rule = LoyalCustomerBirthdayRule()

        // THEN
        assertThat(rule.match(client)).isTrue()
        assertThat(rule.calculateCustomerDiscount(client, discount) - discount)
                .isCloseTo(.1, within(.0000001)) // Floating point
    }

    @Test
    fun dontApplyLoyalCustomerBirthdayRule() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(false)
        val discount = random()

        // WHEN
        val rule = LoyalCustomerBirthdayRule()

        // THEN
        assertThat(rule.match(client)).isFalse()
    }

}