package main.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.converter.Converter;
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


    public void buttonClick(ActionEvent actionEvent) {
        welcomeLabel.setText("nichego");
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

    private boolean checkField() {
        InputError error = CheckInput.Companion.checkField(textMsisdn, textMsisdnB,dateStarDate,textStartTime,textDuration);
        if (error==InputError.NO_ERROR) {
            return true;
        }
        else {
            welcomeLabel.setText(error.getTextError());
            return false;
        }
    }

}
