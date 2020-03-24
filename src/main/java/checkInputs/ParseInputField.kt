package checkInputs;

class ParseInputField {

    fun replaceDigit(oldText: String, newText: String): String {
        when {
            newText.length > oldText.length && newText.matches("^\\d+$".toRegex()) -> return newText
            newText.length > oldText.length && !newText.matches("^\\d+$".toRegex())-> return oldText
        }
        return newText
    }

    fun parseTime(oldText: String, newText: String): String {
        var replaceText: String = newText
        if (newText.length > oldText.length) {
            when {
                (newText.length == 2 || newText.length == 5) &&
                        (newText.matches("^([0-1]\\d)|(2[1-3])".toRegex()) ||
                                newText.matches("((^[0-1]\\d)|(^2[1-3])):[0-5]\\d".toRegex())) -> replaceText += ":"
                newText.matches("^([0-1]\\d?|2[0-3]?)".toRegex()) -> return replaceText
                newText.matches("^([0-1]\\d|2[0-3]):".toRegex()) -> return replaceText
                newText.matches("^([0-1]\\d|2[0-3]):[0-5]\\d?".toRegex()) -> return replaceText
                newText.matches("^([0-1]\\d|2[0-3]):[0-5]\\d:".toRegex()) -> return replaceText
                newText.matches("^([0-1]\\d|^[2][0-3]):[0-5]\\d:[0-5]?\\d?$".toRegex()) -> return replaceText

                else -> return oldText
            }
        }
        else {
            if (oldText.matches("^([0-1]\\d|2[0-3]):$".toRegex()) || oldText.matches("^([0-1]\\d|2[0-3]):[0-5]\\d:$".toRegex())) {
                replaceText = replaceText.substring(0, replaceText.length - 1)
            }
        }
        return replaceText
    }
}