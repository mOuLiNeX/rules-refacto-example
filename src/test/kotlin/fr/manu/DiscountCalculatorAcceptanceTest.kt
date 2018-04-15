package fr.manu

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class DiscountCalculatorAcceptanceTest {
    private lateinit var client: Customer

    @Before
    fun setUp() {
        client = mock()
    }

    @Test
    fun `Pour tous les + de 65 ans discount de 5%`() {
        // GIVEN
        whenever(client.isSenior).thenReturn(true)
        whenever(client.isKnown).thenReturn(true)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.05)
    }

    @Test
    fun `Pour l'anniversaire discount de 10%`() {
        // GIVEN
        whenever(client.isBirthday).thenReturn(true)
        whenever(client.isKnown).thenReturn(true)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun `Pour un client connu depuis + d'1 an discount de 10%`() {
        // GIVEN
        whenever(client.isKnown).thenReturn(true)
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(true)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun `Pour un client connu depuis + de 5 ans discount de 12%`() {
        // GIVEN
        whenever(client.isKnown).thenReturn(true)
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(true)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.12)
    }

    @Test
    fun `Pour un client connu depuis + de 10 ans discount de 20%`() {
        // GIVEN
        whenever(client.isKnown).thenReturn(true)
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(true)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.2)
    }

    @Test
    fun `Pour tout achat à la date anniversaire d'un client connu discount + 10%`() {
        whenever(client.isKnown).thenReturn(true)
        whenever(client.isBirthday).thenReturn(true)

        // Connu depuis + d'1 an
        whenever(client.hasBeenLoyalForYears(1)).thenReturn(true)
        assertThat(calculateDiscount(client)).isEqualTo(.1 + .1)

        // Connu depuis + de 5 ans
        whenever(client.hasBeenLoyalForYears(5)).thenReturn(true)
        assertThat(calculateDiscount(client)).isEqualTo(.12 + .1)

        // Connu depuis + d'10 ans
        whenever(client.hasBeenLoyalForYears(10)).thenReturn(true)
        assertThat(calculateDiscount(client)).isEqualTo(.2 + .1)
    }

    @Test
    fun `Pour tout nveau client discount of 15%`() {
        // GIVEN
        whenever(client.isKnown).thenReturn(false)

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        assertThat(discount).isEqualTo(.15)
    }
}