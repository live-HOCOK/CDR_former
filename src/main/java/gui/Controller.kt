package gui

import checkInputs.CheckInput.Companion.checkField
import checkInputs.InputError
import checkInputs.ParceInputField
import exporter.ExportToCSV
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*

import java.time.format.DateTimeFormatter

class Controller {
    @FXML
    lateinit var textMsisdn: TextField
    @FXML
    lateinit var welcomeLabel: Label
    @FXML
    lateinit var operatorName: Label
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

    val parse = ParceInputField()

    // метод выполняющийся при нажатии на кнопку
    @FXML
    fun buttonClick(actionEvent: ActionEvent?) { // выполняем проверку, если хорошо то передаем на конвертацию текста
        if (checkField()) {
            val data: String = "${textMsisdn.text},${textMsisdnB.text}," +
                    dateStarDate.value.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")) +
                    ",${textStartTime.text},${textDuration.text},${if (chPlusMinute.isSelected) "1" else "0"}," +
                    if (chUrgency.isSelected) "1," else "0," + if (chUrgency.isSelected) "1" else "0"
            welcomeLabel.text = data //debug
            val export = ExportToCSV()
            export.createCSV(data, operatorName.text)
        }
    }

    // ограничиваем ввод букв в некоторые поля
    @FXML
    fun replaceDigit() {
        parse.replaceDigit(textMsisdn)
        parse.replaceDigit(textMsisdnB)
        parse.replaceDigit(textDuration)
    }

    //данный метод запускается после загрузки fxml
    @FXML
    fun initialize() {
        showOperatorDialog()
        //добавить лиснеры для полей
        textStartTime.textProperty().addListener { observable, oldValue, newValue -> run {
            val replaceValue : String = parse.parseTime(oldValue, newValue)
            if (replaceValue != newValue)
                Platform.runLater{ -> run {
                    textStartTime.text = replaceValue
                    textStartTime.positionCaret(textStartTime.text.length)
                }
        }

        }}
    }

    // вызов проверки правильности введеной информации
    private fun checkField(): Boolean {
        val error =
            checkField(textMsisdn, textMsisdnB, dateStarDate, textStartTime, textDuration)
        return if (error === InputError.NO_ERROR) {
            true
        } else { // вывод ошибки
            welcomeLabel.text = error.textError
            false
        }
    }

    private fun showOperatorDialog(): TextInputDialog? {
        val dialog = TextInputDialog()
        dialog.headerText = "Enter you name"
        val result = dialog.showAndWait()
        result.ifPresent { name: String -> setOperatorName(name) }
        if (result.isPresent) {
            return dialog
        } else {
            return showOperatorDialog()
        }
    }

    private fun setOperatorName(name: String) {
        if (!name.isEmpty()) {
            operatorName.text = name
        } else {
            showOperatorDialog()
        }
    }
}