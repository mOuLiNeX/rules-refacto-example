package fr.manu

import org.assertj.core.api.Assertions
import org.junit.Test
import java.time.LocalDate.now
import java.time.LocalDateTime

class DiscountCalculatorAcceptanceTest {
    @Test
    fun `Pour tous les + de 65 ans discount de 5%`() {
        // GIVEN
        val client = Customer(dateOfBirth = now().minusYears(66).minusMonths(2),
                dateOfFirstPurchase = LocalDateTime.now().minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.05)
    }

    @Test
    fun `Pour l'anniversaire discount de 10%`() {
        // GIVEN
        val client = Customer(dateOfBirth = now().minusYears(35),
                dateOfFirstPurchase = LocalDateTime.now().minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun `Pour un client connu depuis + d'1 an discount de 10%`() {
        // GIVEN
        val client = Customer(
                dateOfBirth = now().minusYears(35).minusMonths(4),
                dateOfFirstPurchase = LocalDateTime.now().minusYears(1).minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.1)
    }

    @Test
    fun `Pour un client connu depuis + de 5 ans discount de 12%`() {
        // GIVEN
        val client = Customer(
                dateOfBirth = now().minusYears(35).minusMonths(4),
                dateOfFirstPurchase = LocalDateTime.now().minusYears(5).minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.12)
    }

    @Test
    fun `Pour un client connu depuis + de 10 ans discount de 20%`() {
        // GIVEN
        val client = Customer(
                dateOfBirth = now().minusYears(35).minusMonths(4),
                dateOfFirstPurchase = LocalDateTime.now().minusYears(10).minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.2)
    }

    @Test
    fun `Pour tout achat à la date anniversaire d'un client connu depuis + d'1 an 10% de discount en +`() {
        // Connu depuis + d'1 an
        Assertions.assertThat(
                calculateDiscount(
                        Customer(
                                dateOfBirth = now().minusYears(35),
                                dateOfFirstPurchase = LocalDateTime.now().minusYears(1).minusDays(1)))
        ).isEqualTo(.1 + .1)

        // Connu depuis + de 5 ans
        Assertions.assertThat(
                calculateDiscount(
                        Customer(
                                dateOfBirth = now().minusYears(35),
                                dateOfFirstPurchase = LocalDateTime.now().minusYears(5).minusDays(1)))
        ).isEqualTo(.12 + .1)

        // Connu depuis + d'10 ans
        Assertions.assertThat(
                calculateDiscount(
                        Customer(
                                dateOfBirth = now().minusYears(35),
                                dateOfFirstPurchase = LocalDateTime.now().minusYears(10).minusDays(1)))
        ).isEqualTo(.2 + .1)
    }

    @Test
    fun `Pour tout nveau client discount of 15%`() {
        // GIVEN
        val client = Customer(dateOfBirth = now().minusYears(35).minusMonths(2).minusDays(1))

        // WHEN
        val discount = calculateDiscount(client)

        // THEN
        Assertions.assertThat(discount).isEqualTo(.15)
    }
}