package main.checkInput

import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import main.checkInput.InputError.*


class CheckInput () {


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
                else -> return NO_ERROR
            }
        }

        fun checkMsisdn(msisdn: String): Boolean {
            return msisdn.length != 9
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