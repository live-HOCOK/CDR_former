package checkInputs;

import javafx.scene.control.TextField

class ParceInputField {

    companion object {
        fun replaceDigit(text: TextField) {
            text.setText(text.getText().replace("[^\\d]".toRegex(), ""))
            text.positionCaret(text.getText().length)
        }
    }

}