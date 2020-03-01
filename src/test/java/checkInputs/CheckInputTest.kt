package checkInputs

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class CheckInputTest {
    private val checkInput = CheckInput()

    @Test
    fun checkMsisdn() {
        assertTrue(checkInput.checkMsisdn("111"))
    }

    @Test
    fun checkMsisdnPrefix() {
        assertFalse(checkInput.checkMsisdnPrefix("71"))
    }

    @Test
    fun checkMsisdnB() {
        assertFalse(checkInput.checkMsisdnB("123456789"))
        assertFalse(checkInput.checkMsisdnB("1234567890123"))
    }
}