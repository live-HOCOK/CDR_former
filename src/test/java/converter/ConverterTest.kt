package converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ConverterTest {
    private val converter = Converter()

    @Test
    fun convertTest() {
        val actualString = converter.convertString(arrayOf("msisdn", "8797878", "qweqweqwe"))
        val expectedString = "msisdn,8797878,qweqweqwe"
        assertEquals(expectedString, actualString)
    }
}