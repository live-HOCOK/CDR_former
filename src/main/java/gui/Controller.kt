package gui

import checkInputs.CheckInput
import checkInputs.InputError
import checkInputs.ParseInputField
import common.Loggers
import exporter.ExportToCSV
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.StageStyle
import org.apache.logging.log4j.Logger

import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

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

    private val parse = ParseInputField()

    companion object {
        val logger: Logger = Loggers.fileLogger
    }

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

    //данный метод запускается после загрузки fxml
    @FXML
    fun initialize() {
        showOperatorDialog()
        //добавить лиснеры для полей
        textStartTime.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue : String = parse.parseTime(oldValue, newValue)
            if (replaceValue != newValue)
                Platform.runLater{ -> run {
                    textStartTime.text = replaceValue
                    textStartTime.positionCaret(textStartTime.text.length)
                }
        }
        } }
        textMsisdn.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            if (replaceValue != newValue)
                textMsisdn.text = replaceValue
        } }
        textMsisdnB.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            if (replaceValue != newValue)
                textMsisdnB.text = replaceValue
        } }
        textDuration.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            if (replaceValue != newValue)
                textDuration.text = replaceValue
        } }
    }

    // вызов проверки правильности введеной информации
    private fun checkField(): Boolean {
        val checkInput = CheckInput()
        val error: InputError =
            checkInput.checkField(textMsisdn, textMsisdnB, dateStarDate, textStartTime, textDuration)
        return if (error === InputError.NO_ERROR) {
            true
        } else { // вывод ошибки
            welcomeLabel.text = error.textError
            false
        }
    }

    private fun showOperatorDialog(): TextInputDialog? {
        val dialog = TextInputDialog()
        dialog.graphic = null
        dialog.headerText = "Enter you name"
        logger.debug("showOperatorDialog")
        val result = dialog.showAndWait()
        result.ifPresent { name: String -> setOperatorName(name) }
        return if (result.isPresent) {
            dialog
        } else {
            exitProcess(-1)
        }
    }

    private fun setOperatorName(name: String) {
        if (name.isNotEmpty()) {
            operatorName.text += name
        } else {
            showOperatorDialog()
        }
    }
}