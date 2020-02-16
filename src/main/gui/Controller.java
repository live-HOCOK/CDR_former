package main.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.converter.Converter;
import main.checkInput.*;
import java.time.format.DateTimeFormatter;


public class Controller {

    public TextField textMsisdn;
    public Label welcomeLabel;
    public TextField textMsisdnB;
    public DatePicker dateStarDate;
    public TextField textStartTime;
    public TextField textDuration;
    public CheckBox chPlusMinute;
    public CheckBox chUrgency;

    // метод выполняющийся при нажатии на кнопку
    public void buttonClick(ActionEvent actionEvent) {
        // выполняем проверку, если хорошо то передаем на конвертацию текста
        if (checkField()) {
            String[] unparsedData = {textMsisdn.getText(),
                    textMsisdnB.getText(),
                    dateStarDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
                    textStartTime.getText(),
                    textDuration.getText(),
                    String.valueOf(chPlusMinute.isSelected()),
                    String.valueOf(chUrgency.isSelected())};

            Converter convert = new Converter();
            convert.convert(unparsedData, welcomeLabel);
        }
    }

    // ограничиваем ввод букв в некоторые поля
    public void replaceDigit(){
        ParceInputField.Companion.replaceDigit(textMsisdn);
        ParceInputField.Companion.replaceDigit(textMsisdnB);
        ParceInputField.Companion.replaceDigit(textDuration);
    }

    // вызов проверки правильности введеной информации
    private boolean checkField() {
        InputError error = CheckInput.Companion.checkField(textMsisdn, textMsisdnB,dateStarDate,textStartTime,textDuration);
        if (error==InputError.NO_ERROR) {
            return true;
        }
        else {
            // вывод ошибки
            welcomeLabel.setText(error.getTextError());
            return false;
        }
    }

}
