package main.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

public class Controller {


    public TextField textMsisdn;
    public Label welcomeLabel;

    public void buttonClick(ActionEvent actionEvent) {
        welcomeLabel.setText(textMsisdn.getText());
    }


}
