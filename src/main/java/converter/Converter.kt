package converter;

import javafx.scene.control.Label


class Converter() {

    fun convert(unparsedData: Array<String>, label: Label) {
        label.text = "asasd"//?

    }

    fun convertString(unparsedData: Array<String>): String {
        var string = ""

        for (s in unparsedData) {
            var newS = ""
            if (unparsedData.lastIndex != unparsedData.indexOf(s)) {
                newS = s.plus(",")
            } else {
                string = string.plus(s)
            }

            string = string.plus(newS)
        }
        return string
    }


}