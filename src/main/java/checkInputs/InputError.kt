package checkInputs;

enum class InputError(val errCode: Int, val textError: String) {

    NO_ERROR (0, "Без ошибок"),
    NO_MSISDN(1, "Поле 'MSISDN' не заполнено"),
    NO_MSISDN_B(2, "Поле 'MSISDN абонента B' не заполнено"),
    NO_DATE(3, "Поле 'Дата' не заполнено"),
    NO_TIME(4,"Поле 'Время' не заполнено"),
    NO_DURATION(5, "Поле 'Длительность' не заполнено"),
    INCORRECT_MSISDN(6, "Поле 'MSISDN' не соответсвует маске (9 цифр)"),
    INCORRECT_MSISDN_B(7,"Поле 'Номер вызваемого абонента' должено быть 9 цифр для РУз или 13 символов для МТР"),
    INCORRECT_START_TIME(8,"Поле 'Время вызова' должно соответсвовать маскам ЧЧ:ММ:СС"),
    INCORRECT_PREFIX (9, "Неверный префикс абонента A")

}