package interfaces.controllers;

import interfaces.view.WindowDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerWindowProduto {

    @FXML
    private TextField txtInput;
    @FXML
    private Label lbReversed;

    public void reverse(ActionEvent actionEvent) {
        final String input = txtInput.getText();
        StringBuilder builder = new StringBuilder(input);
        lbReversed.setText(builder.reverse().toString());

        WindowDisplay windowDisplay = new WindowDisplay();
        windowDisplay.showAndWait(input.length());

    }

}
