package interfaces.view;

import interfaces.controllers.ControllerWindowDisplay;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowDisplay {

    public void showAndWait(int numberOfChars){
        FXMLLoader fxmlLoader = new FXMLLoader();
        final Pane graph;
        try {
            graph = fxmlLoader.load(Objects.requireNonNull(WindowProduto.class.getResource("windowDisplay.fxml")).openStream());
            final ControllerWindowDisplay controllerWindowPlay = fxmlLoader.getController();

            controllerWindowPlay.setCharNumber(numberOfChars);

            Scene scene = new Scene(graph, 600, 260);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("Reversed info");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
