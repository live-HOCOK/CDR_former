package checkInputs

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class CheckInputTest {
    private val checkInput = CheckInput()

//    @Test
//    fun checkMsisdn() {
//        assertTrue(checkInput.checkMsisdn("111"))
//        assertFalse(checkInput.checkMsisdn("123456789"))
//    }
//
//    @Test
//    fun checkMsisdnPrefix() {
//        assertFalse(checkInput.checkMsisdnPrefix("711111"))
//        assertTrue(checkInput.checkMsisdnPrefix("1111111"))
//        assertTrue(checkInput.checkMsisdnPrefix("5411111"))
//    }
//
//    @Test
//    fun checkMsisdnB() {
//        assertTrue(checkInput.checkMsisdnB("123456"))
//        assertTrue(checkInput.checkMsisdnB("1234567890"))
//        assertFalse(checkInput.checkMsisdnB("123456789"))
//        assertFalse(checkInput.checkMsisdnB("1234567890123"))
//    }
//
//    @Test
//    fun prefixOfMsisdn() {
//        assertEquals( "71", checkInput.prefixOfMsisdn("711111"))
//    }
//
//    @Test
//    fun checkStartTime() {
//        assertFalse(checkInput.checkStarTime("11:22:11"))
//        assertTrue(checkInput.checkStarTime("11:22:1"))
//        assertTrue(checkInput.checkStarTime("11:22"))
//        assertTrue(checkInput.checkStarTime("11:22:"))
//        assertTrue(checkInput.checkStarTime("31:22:11"))
//        assertTrue(checkInput.checkStarTime("25:22:11"))
//        assertTrue(checkInput.checkStarTime("21:70:11"))
//        assertTrue(checkInput.checkStarTime("21:40:81"))
//    }
}