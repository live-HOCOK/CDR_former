package main.checkInput

import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import main.checkInput.InputError.*
import java.lang.RuntimeException


class CheckInput () {

    // проверка на возможные ошибки
    companion object {
        fun checkField(msisdn: TextField, msisdnB: TextField, date: DatePicker, time: TextField, duration: TextField): InputError {
            when {
                msisdn.text.isEmpty() -> return NO_MSISDN
                msisdnB.text.isEmpty() -> return NO_MSISDN_B
                date.value == null -> return NO_DATE
                time.text.isEmpty() -> return NO_TIME
                duration.text.isEmpty() -> return NO_DURATION
                checkMsisdn(msisdn.text) -> return INCORRECT_MSISDN
                checkMsisdnB(msisdnB.text) -> return INCORRECT_MSISDN_B
                checkStarTime(time.text) -> return INCORRECT_START_TIME
                checkMsisdnPrefix(msisdn.text) -> return INCORRECT_PREFIX
                else -> return NO_ERROR
            }
        }

        fun checkMsisdn(msisdn: String): Boolean {
            return msisdn.length != 9
        }

        fun checkMsisdnPrefix(msisdn: String): Boolean {
            return try {
                FilialPrefix.valueOf(("P" + msisdn[0].toString() + msisdn[1].toString())) == null
            } catch (e: RuntimeException){
                true
            }

        }

        fun checkMsisdnB(msisdnB: String): Boolean {
            return msisdnB.length != 9 && msisdnB.length != 13
        }

        fun checkStarTime(startTime: String): Boolean{
            val regex = "((^[0-1]\\d)|(^2[1-3]))[:,.,,,/][0-5]\\d([:,.,,,/][0-5]\\d)?$".toRegex()
            return regex.find(startTime) == null
        }
    }
}