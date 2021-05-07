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
    // Constant
    /////////////////////////////////////////////////////////////

    final static String [] IMG_FOLDER = {"bg", "score", "chifoumi", "ciseaux", "cross", "gear", "Logo",
            "papier", "pierre", "play", "question mark", "rules", "btn_rock", "btn_cisor", "btn_paper",
            "bg-dark", "yes", "no"};

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    private Map<String, Image> imageMap; // Map of all image in ext/img folder

    private String theme; // Default Theme
    private String police; // Default Police
    private int score; // Default Score

    /////////////////////////////////////////////////////////////
    // Main
    /////////////////////////////////////////////////////////////

    public static void main(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Default Setter
        SetImageMap();
        SetTheme();
        SetPolice();
        SetScore();

        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
        loader.setController(new MenuController(stage, GetImageMap(), GetTheme(), GetPolice(), GetScore()));
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
    * Set All image in ext in a map on the controller
    */
    private void SetImageMap() {
        Map<String, Image> imageViewMap = new HashMap<>();
        for(String fileName : IMG_FOLDER) {
            InputStream stream = getClass().getResourceAsStream("/ext/img/" + fileName + ".png");
            assert stream != null;
            Image img = new Image(stream);
            imageViewMap.put(fileName, img);
        }
        this.imageMap = imageViewMap;
    }

    /*
    * Set the theme on the controller
    */
    private void SetTheme() {
        this.theme = "light";
    }

    /*
     * Set the Police on the controller
     */
    private void SetPolice() {
        this.police = "arial";
    }

    /*
     * Set the Score on the controller
     */
    private void SetScore() {
        this.score = 5;
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

    /*
     * Get the theme from the controller
     */
    private String GetTheme() {
        return this.theme;
    }

    /*
     * Get the Police from the controller
     */
    private String GetPolice() {
        return this.police;
    }

    /*
     * Get the score from the controller
     */
    private int GetScore() {
        return this.score;
    }

}
