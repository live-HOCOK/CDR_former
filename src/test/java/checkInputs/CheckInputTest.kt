package checkInputs

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

import kotlin.test.assertTrue

internal class CheckInputTest {


    @Test
    fun checkMsisdn() {
        assertTrue(CheckInput.checkMsisdn("111"))
    }

    @Test
    fun checkMsisdnPrefix() {
        assertFalse(CheckInput.checkMsisdnPrefix("71"))
    }
}