package gui

import checkInputs.CheckInput.Companion.checkField
import checkInputs.InputError
import checkInputs.ParceInputField.Companion.replaceDigit
import converter.Converter
import javafx.event.ActionEvent
import javafx.scene.control.CheckBox
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.control.TextField
import java.time.format.DateTimeFormatter

class Controller {
    var textMsisdn: TextField? = null
    var welcomeLabel: Label? = null
    var textMsisdnB: TextField? = null
    var dateStarDate: DatePicker? = null
    var textStartTime: TextField? = null
    var textDuration: TextField? = null
    var chPlusMinute: CheckBox? = null
    var chUrgency: CheckBox? = null
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