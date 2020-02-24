package converter;

import javafx.scene.control.Label


class Converter() {

    fun arrayFormater (unparsedData: Array<String>, label: Label) {
        var parsedData: String

        parsedData = convertString(unparsedData)
        label.text = parsedData
    }

    fun convertString(unparsedData: Array<String>): String {
        return unparsedData.joinToString(",")
    }


}