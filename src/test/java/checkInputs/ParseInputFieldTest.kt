package checkInputs

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ParseInputFieldTest {
    private val testClass = ParseInputField()

    @Test
    private fun replaceDigit() {
        assertEquals("1", testClass.replaceDigit("1","1d"))
        assertEquals("11", testClass.replaceDigit("11", "1d1"))
        assertEquals("11", testClass.replaceDigit("11","d11"))
        assertEquals("1", testClass.replaceDigit("11", "1"))
    }
}