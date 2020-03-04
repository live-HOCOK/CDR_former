package checkInputs;

import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import checkInputs.InputError.*
import java.lang.RuntimeException


class CheckInput () {

    // проверка на возможные ошибки
    fun checkField(msisdn: TextField, msisdnB: TextField, date: DatePicker, time: TextField, duration: TextField): InputError {
        return when {
            msisdn.text.isEmpty() -> NO_MSISDN
            msisdnB.text.isEmpty() -> NO_MSISDN_B
            date.value == null -> NO_DATE
            time.text.isEmpty() -> NO_TIME
            duration.text.isEmpty() -> NO_DURATION
            checkMsisdn(msisdn.text) -> INCORRECT_MSISDN
            checkMsisdnB(msisdnB.text) -> INCORRECT_MSISDN_B
            checkStarTime(time.text) -> INCORRECT_START_TIME
            checkMsisdnPrefix(msisdn.text) -> INCORRECT_PREFIX
            else -> NO_ERROR
        }
    }

    //check msisdn on lenght
    fun checkMsisdn(msisdn: String): Boolean {
        return msisdn.length != 9
    }

    //check msisdn prefix
    fun checkMsisdnPrefix(msisdn: String): Boolean {
        val filialPrefix = FilialPrefix()
        return !filialPrefix.prefixList.contains(prefixOfMsisdn(msisdn))
    }

    //get prefix on full msisdn
    fun prefixOfMsisdn(msisdn: String): String {return msisdn.substring(0,2)}

    fun checkMsisdnB(msisdnB: String): Boolean {
        return msisdnB.length != 9 && msisdnB.length != 13
    }

    fun checkStarTime(startTime: String): Boolean{
        val regex = "((^[0-1]\\d)|(^2[1-3])):[0-5]\\d:[0-5]\\d$".toRegex()
        return regex.find(startTime) == null
    }
}