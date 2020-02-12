package main.gui

import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import main.gui.InputError.*


class CheckInput () {


    companion object {
        fun checkField(msisdn: TextField, msisdnB: TextField, date: DatePicker, time: TextField, duration: TextField): InputError {
            when {
                msisdn.text.isEmpty() -> return NO_MSISDN
                msisdnB.text.isEmpty() -> return NO_MSISDN_B
                date.value == null -> return NO_DATE
                time.text.isEmpty() -> return NO_TIME
                duration.text.isEmpty() -> return NO_DURATION
                !checkMsisdn(msisdn.text) -> return INCORRECT_MSISDN
                else -> return NO_ERROR
            }
        }

        fun checkMsisdn(msisdn: String): Boolean {
            if (msisdn.length != 9) return false
            //if (msisdn.contains(""))
            return true
        }
    }
}