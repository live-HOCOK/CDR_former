package main.converter

import javafx.scene.control.Label


class Converter() {

    fun convert (unparsedData: Array<String>, label: Label) {
        var parsedData: String

        var msisdn: String = unparsedData[0]
        var msisdnB: String = unparsedData[1]
        var startDate: String = unparsedData[2]
        var startTime: String = unparsedData[3]
        var duration: String = unparsedData[4]
        var plusMinute: String = convertBoolean(unparsedData[5])
        var urgency: String = convertBoolean(unparsedData[6])

        parsedData = "$msisdn,$msisdnB,$startDate,$startTime,$duration,$plusMinute,$urgency"
        label.text = parsedData
    }

    private fun convertBoolean(bolString: String): String{
        return if (bolString.equals("true")) "1" else "0"
    }

}