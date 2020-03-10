package checkInputs;

import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import checkInputs.InputError.*
import java.lang.RuntimeException


class CheckInput() {
    val arrayError = HashSet<InputError>()
        get() = field

    //check msisdn
    fun checkMsisdn(msisdn: TextField) {
        val filialPrefix = FilialPrefix()
        if (msisdn.text.isNotEmpty()) { //on null
            arrayError.remove(NO_MSISDN)
            if (msisdn.length == 9) //on length
                arrayError.remove(INCORRECT_MSISDN)
            else arrayError.add(INCORRECT_MSISDN)
            if (filialPrefix.prefixList.contains(prefixOfMsisdn(msisdn.text))) //on prefix
                arrayError.remove((INCORRECT_PREFIX))
            else arrayError.add(INCORRECT_PREFIX)
        } else arrayError.add(NO_MSISDN)
    }

    //get prefix on full msisdn
    fun prefixOfMsisdn(msisdn: String): String {
        return if (msisdn.length > 2) msisdn.substring(0, 2) else msisdn
    }

    fun checkMsisdnB(msisdnB: TextField) {
        if (msisdnB.text.isNotEmpty()) {
            arrayError.remove(NO_MSISDN_B)
            if (msisdnB.length == 9 || msisdnB.length == 13)
                arrayError.remove(INCORRECT_MSISDN_B)
            else arrayError.add((INCORRECT_MSISDN_B))
        } else arrayError.add(NO_MSISDN_B)
    }

    fun checkDate(date: DatePicker) {
        if (date.value != null) arrayError.remove(NO_DATE)
            else arrayError.add(NO_DATE)
    }

    fun checkStarTime(startTime: TextField) {
        val regex = "((^[0-1]\\d)|(^2[1-3])):[0-5]\\d:[0-5]\\d$".toRegex()
        if (startTime.text.isNotEmpty()) {
            arrayError.remove(NO_TIME)
            if (regex.find(startTime.text) != null) arrayError.remove(INCORRECT_START_TIME)
                else arrayError.add(INCORRECT_START_TIME)
        } else arrayError.add(NO_TIME)
    }

    fun checkDuration(duration: TextField) {
        if (duration.text.isNotEmpty())
            arrayError.remove(NO_DURATION)
        else
            arrayError.add((NO_DURATION))
    }

    fun checkAll (msisdn: TextField, msisdnB: TextField, date: DatePicker, startTime: TextField, duration: TextField) {
        checkMsisdn(msisdn)
        checkMsisdnB(msisdnB)
        checkDate(date)
        checkStarTime(startTime)
        checkDuration(duration)
    }
}