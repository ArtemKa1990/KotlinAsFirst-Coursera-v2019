package lesson3.task1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Assertions

import lesson3.task1.*

class MyTests {
    @Test
    fun gcdTest() {
        // Мои тесты
        Assertions.assertEquals(5, gcd(0, 5))
        Assertions.assertEquals(12, gcd(12, 24))
        Assertions.assertEquals(3, gcd(15, 48))
        Assertions.assertEquals(2, gcd(16, 90))
        Assertions.assertEquals(643, gcd(1286, 643))
        Assertions.assertEquals(5, gcd(0, 5))
        // Тесты из учебного курса
        Assertions.assertEquals(13, gcd(13, 13))
        Assertions.assertEquals(2, gcd(2, 8))
        Assertions.assertEquals(2, gcd(6, 8))
        Assertions.assertEquals(3, gcd(39, 75))
        Assertions.assertEquals(1, gcd(13579, 98631))

    }
}