package fr.manu

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

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
        val discount = BirthdayDiscountRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun dontApplyBirthdayRule() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(false)

        // WHEN
        val discount = BirthdayDiscountRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applySeniorRule() {
        // GIVEN
        whenever(client.isSenior).thenReturn(true)

        // WHEN
        val discount = SeniorDiscountRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.05)
    }

    @Test
    fun dontApplySeniorRule() {
        // GIVEN
        whenever(client.isSenior).thenReturn(false)

        // WHEN
        val discount = SeniorDiscountRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyNewCustomerRule() {
        // GIVEN
        whenever(client.isKnown).thenReturn(false)

        // WHEN
        val discount = NewCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.15)
    }

    @Test
    fun dontApplyNewCustomerRule() {
        // GIVEN
        whenever(client.isKnown).thenReturn(true)

        // WHEN
        val discount = NewCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyOneYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(true)

        // WHEN
        val discount = OneYearLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun dontApplyOneYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(false)

        // WHEN
        val discount = OneYearLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyFiveYearLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(true)

        // WHEN
        val discount = FiveYearsLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.12)
    }

    @Test
    fun dontApplyFiveYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(false)

        // WHEN
        val discount = FiveYearsLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }

    @Test
    fun applyTenYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(true)

        // WHEN
        val discount = TenYearsLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.2)
    }

    @Test
    fun dontApplyTenYearsLoyalCustomerRule() {
        // GIVEN
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(false)

        // WHEN
        val discount = TenYearsLoyalCustomerRule().calculateCustomerDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.0)
    }
}