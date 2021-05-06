package application;

import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    final static String [] IMG_FOLDER = {"bg", "score", "chifoumi", "ciseaux", "cross", "gear", "Logo",
            "papier", "pierre", "play", "question mark", "rules", "btn_rock", "btn_cisor", "btn_paper",
            "bg-dark"};

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    private Map<String, Image> imageMap;

    private String theme;
    private String police;
    private int score;

    /////////////////////////////////////////////////////////////
    // Main
    /////////////////////////////////////////////////////////////

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Set Image in Map
        SetImageMap();
        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        loader.setController(new MenuController(stage, GetImageMap(), "light", "arial", 5));
        Parent view = loader.load();
        view.getStylesheets().clear();
        view.getStylesheets().add(getClass().getResource("/style/light - Arial.scss").toExternalForm());

        stage.setTitle("Shifumi - CC 2021");

        stage.setScene(new Scene(view));
        stage.show();
    }

    /////////////////////////////////////////////////////////////
    // Setter Method
    /////////////////////////////////////////////////////////////

    /*
    * Set All image in ext in a map
    */
    private void SetImageMap() {
        Map<String, Image> imageViewMap = new HashMap<>();
        for(String fileName : IMG_FOLDER) {
            InputStream stream = getClass().getResourceAsStream("/ext/img/" + fileName + ".png");
            Image img = new Image(stream);
            imageViewMap.put(fileName, img);
        }
        this.imageMap = imageViewMap;
    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    /*
    * Get Image Map from the controller
    */
    public Map<String, Image> GetImageMap() {
        return this.imageMap;
    }

}
