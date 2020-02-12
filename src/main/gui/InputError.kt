package main.gui

enum class InputError(val errCode: Int, val textError: String) {

    NO_ERROR (0, "Без ошибок"),
    NO_MSISDN(1, "Поле 'MSISDN' не заполнено"),
    NO_MSISDN_B(2, "Поле 'MSISDN абонента B' не заполнено"),
    NO_DATE(3, "Поле 'Дата' не заполнено"),
    NO_TIME(4,"Поле 'Время' не заполнено"),
    NO_DURATION(5, "Поле 'Длительность' не заполнено"),
    INCORRECT_MSISDN(6, "Поле 'MSISDN' не соответсвует маске (9 цифр)")

}