package fr.manu

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDate.now
import java.time.LocalDateTime

class CustomerTest {

    @Test
    fun isBirthday() {
        assertThat(Customer(dateOfBirth = now().minusYears(30)).isBirthday).isTrue()
        assertThat(Customer(dateOfBirth = now().minusYears(30).minusMonths(3)).isBirthday).isFalse()
    }

    @Test
    fun isSenior() {
        assertThat(Customer(dateOfBirth = now().minusYears(66)).isSenior).isTrue()
        assertThat(Customer(dateOfBirth = now().minusYears(64)).isSenior).isFalse()
        assertThat(Customer(dateOfBirth = now().minusYears(65).plusDays(1)).isSenior).isFalse()
    }

    @Test
    fun hasBeenLoyalForYears() {
        val customerTemplate = Customer(dateOfBirth = now().minusYears(40).plusMonths(5))

        assertThat(customerTemplate.copy(dateOfFirstPurchase = LocalDateTime.now().minusYears(10).minusDays(1)).hasBeenLoyalForYears(10)).isTrue()
        assertThat(customerTemplate.copy(dateOfFirstPurchase = LocalDateTime.now().minusYears(10)).hasBeenLoyalForYears(10)).isTrue()
        assertThat(customerTemplate.copy(dateOfFirstPurchase = LocalDateTime.now().minusYears(10).plusDays(1)).hasBeenLoyalForYears(10)).isFalse()
        assertThat(customerTemplate.copy(dateOfFirstPurchase = null).hasBeenLoyalForYears(10)).isFalse()
    }
}