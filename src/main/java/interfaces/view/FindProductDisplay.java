package interfaces.view;

import interfaces.controllers.FindProductController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FindProductDisplay {

    public void showAndWait() {
        final FXMLLoader loader = new FXMLLoader();
        final Pane graph;
        try {
            graph = loader.load(Objects.requireNonNull(WindowTable.class.getResource("find-product.fxml")).openStream());
            final FindProductController findProductController = loader.getController();

            Scene scene = new Scene(graph, 600, 260);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Find Product");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
