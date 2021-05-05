package application;

import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        loader.setController(new MenuController());
        Parent view = loader.load();
        view.getStylesheets().clear();
        view.getStylesheets().add(getClass().getResource("/style/light - Arial.scss").toExternalForm());

        stage.setTitle("Shifumi - CC 2021");

        stage.setScene(new Scene(view));
        stage.show();
    }
}
