package gui

import checkInputs.CheckInput
import checkInputs.ParseInputField
import common.Loggers
import exporter.ExportToCSV
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import org.apache.logging.log4j.Logger
import java.lang.reflect.Field
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess


class Controller {
    @FXML
    lateinit var textMsisdn: TextField
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
    @FXML
    lateinit var buttonForm: Button
    @FXML
    lateinit var tooltipButton: Tooltip

    private val parse = ParseInputField()
    private val checkInput = CheckInput()
    private val export = ExportToCSV()

    companion object {
        val logger: Logger = Loggers.fileLogger
    }

    // метод выполняющийся при нажатии на кнопку
    @FXML
    fun buttonClick() { // выполняем проверку, если хорошо то передаем на конвертацию текста
        if (checkField()) {
            val data: String = "${textMsisdn.text},${textMsisdnB.text}," +
                    dateStarDate.value.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")) +
                    ",${textStartTime.text.replace(
                        ":",
                        "."
                    )},${textDuration.text},${if (chPlusMinute.isSelected) "1" else "0"}," +
                    if (chUrgency.isSelected) "1," else "0," + if (chUrgency.isSelected) "1" else "0"
            export.createCSV(data)
        }
    }

    //данный метод запускается после загрузки fxml
    @FXML
    fun initialize() {
        showOperatorDialog()
        //добавить лиснеры для полей
        textStartTime.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue : String = parse.parseTime(oldValue, newValue)
            checkInput.checkStarTime(textStartTime)
            if (replaceValue != newValue)
                Platform.runLater{ -> run {
                    textStartTime.text = replaceValue
                    textStartTime.positionCaret(textStartTime.text.length)
                }
        }
            installTooltip()
        } }
        textMsisdn.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            checkInput.checkMsisdn(textMsisdn)
            if (replaceValue != newValue)
                textMsisdn.text = replaceValue
            installTooltip()
        } }
        textMsisdnB.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            checkInput.checkMsisdnB(textMsisdnB)
            if (replaceValue != newValue)
                textMsisdnB.text = replaceValue
            installTooltip()
        } }
        textDuration.textProperty().addListener { _, oldValue, newValue -> run {
            val replaceValue: String = parse.replaceDigit(oldValue, newValue)
            checkInput.checkDuration(textDuration)
            if (replaceValue != newValue)
                textDuration.text = replaceValue
            installTooltip()
        } }
    }

    private fun hackTooltipStartTiming(tooltip: Tooltip, time: Double) { //code from stackoverflow
        try {
            val fieldBehavior: Field = tooltip.javaClass.getDeclaredField("BEHAVIOR")
            fieldBehavior.setAccessible(true)
            val objBehavior: Any = fieldBehavior.get(tooltip)
            val fieldTimer: Field = objBehavior.javaClass.getDeclaredField("activationTimer")
            fieldTimer.setAccessible(true)
            val objTimer = fieldTimer.get(objBehavior) as Timeline
            objTimer.keyFrames.clear()
            objTimer.keyFrames.add(KeyFrame(javafx.util.Duration(time)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun installTooltip() {
        tooltipButton.text = ""
        if (checkInput.arrayError.size > 0) {
            hackTooltipStartTiming(tooltipButton, 0.0)
            checkInput.arrayError.forEach {
                tooltipButton.text += it.textError + "\n"
            }
        }
        else {
            hackTooltipStartTiming(tooltipButton, 2000.0)
            tooltipButton.text = "Сформировать CSV"
        }
    }

    // вызов проверки правильности введеной информации
    private fun checkField(): Boolean {
        checkInput.checkAll(textMsisdn,textMsisdnB,dateStarDate,textStartTime,textDuration)
        installTooltip()
        return checkInput.arrayError.size == 0
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
            export.operator = name
        } else {
            showOperatorDialog()
        }
    }
}