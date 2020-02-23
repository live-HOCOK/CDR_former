package gui

import checkInputs.CheckInput.Companion.checkField
import checkInputs.InputError
import checkInputs.ParceInputField.Companion.replaceDigit
import converter.Converter
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.time.format.DateTimeFormatter

class Controller {
    @FXML
    lateinit var textMsisdn: TextField
    @FXML
    lateinit var welcomeLabel: Label
    @FXML
    lateinit var textMsisdnB: TextField
    @FXML
    lateinit var dateStarDate: DatePicker
    @FXML
    lateinit var textStartTime: TextField
    @FXML
    lateinit var textDuration: TextField
    @FXML
    lateinit var chPlusMinute: CheckBox
    @FXML
    lateinit var chUrgency: CheckBox

    // метод выполняющийся при нажатии на кнопку
    fun buttonClick(actionEvent: ActionEvent?) { // выполняем проверку, если хорошо то передаем на конвертацию текста
        if (checkField()) {
            val unparsedData = arrayOf(
                textMsisdn!!.text,
                textMsisdnB!!.text,
                dateStarDate!!.value.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
                textStartTime!!.text,
                textDuration!!.text, chPlusMinute!!.isSelected.toString(), chUrgency!!.isSelected.toString()
            )
            val convert = Converter()
            convert.convert(unparsedData, welcomeLabel!!)
        }
    }

    // ограничиваем ввод букв в некоторые поля
    fun replaceDigit() {
        replaceDigit(textMsisdn!!)
        replaceDigit(textMsisdnB!!)
        replaceDigit(textDuration!!)
    }

    // вызов проверки правильности введеной информации
    private fun checkField(): Boolean {
        val error =
            checkField(textMsisdn!!, textMsisdnB!!, dateStarDate!!, textStartTime!!, textDuration!!)
        return if (error === InputError.NO_ERROR) {
            true
        } else { // вывод ошибки
            welcomeLabel!!.text = error.textError
            false
        }
    }
}