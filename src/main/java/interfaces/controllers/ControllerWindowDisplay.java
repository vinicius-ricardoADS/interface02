package interfaces.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerWindowDisplay {

    @FXML
    private Button btnClose;
    @FXML
    private Label lbResult;

    public void close(ActionEvent actionEvent) {
        final Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void setCharNumber(int numberOfChars) {
        lbResult.setText(String.valueOf(numberOfChars));
    }
}
