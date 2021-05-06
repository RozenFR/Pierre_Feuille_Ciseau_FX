package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class GameController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Stage stage; // Reference to window stage
    private String theme; // theme of the view
    private String police; // police of the view
    private int score; // score => points to win
    private Map<String, Image> imageMap; // Reference to all ext/img

    // FXML
    // TODO Set up all fxml variable

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public GameController(Stage stage, Map<String, Image> imageMap, String theme, String police, int score) {
        SetImageMap(imageMap);
        SetStage(stage);
        SetTheme(theme);
        SetPolice(police);
        SetScore(score);
    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() throws FileNotFoundException {
        SetImg();
    }

    /////////////////////////////////////////////////////////////
    // Setter Method
    /////////////////////////////////////////////////////////////

    /*
     * Set the stage reference on the controller
     */
    private void SetStage(Stage stage) {
        this.stage = stage;
    }

    /*
     * Set the theme on the controller
     */
    private void SetTheme(String theme) {
        this.theme = theme;
    }

    /*
     * Set the police on the controller
     */
    private void SetPolice(String police) {
        this.police = police;
    }

    /*
     * Set the score point on the controller
     */
    private void SetScore(int score) {
        this.score = score;
    }

    /*
     * Set Map of Image on the controller
     */
    private void SetImageMap(Map<String, Image> imageMap) {
        this.imageMap = imageMap;
    }

    /*
     * Set the style on the view
     */
    private void SetStyle() throws IOException {

        Stage stage = GetStage();

        Scene root = stage.getScene();

        Parent view = root.getRoot();

        view.getStylesheets().clear();

        if (!theme.isBlank()) {
            if (isDark()) {
                if (isArial())
                    view.getStylesheets().add(getClass().getResource("/style/dark - Arial.scss").toExternalForm());
                else if (isTNR())
                    view.getStylesheets().add(getClass().getResource("/style/dark - Times New Roman.scss").toExternalForm());
            }
            else if (isLight()) {
                if (isArial())
                    view.getStylesheets().add(getClass().getResource("/style/light - Arial.scss").toExternalForm());
                else if (isTNR())
                    view.getStylesheets().add(getClass().getResource("/style/light - Times New Roman.scss").toExternalForm());
            }
        }
        else
            view.getStylesheets().add(getClass().getResource("/style/dark - Arial.scss").toExternalForm());



        stage.setScene(root);

    }

    /*
     * Set the image on the view
     */
    private void SetImg() throws FileNotFoundException {

        // TODO Complete SetImg

    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    /*
     * Get the stage from the controller
     */
    public Stage GetStage() {
        return this.stage;
    }

    /*
     * Get the theme from the controller
     */
    public String GetTheme() {
        return theme;
    }

    /*
     * Get the police from the controller
     */
    public String GetPolice() {
        return this.police;
    }

    /*
     * Get the score from the controller
     */
    public int GetScore() {
        return this.score;
    }

    /*
     * Get Map of Image from the controller
     */
    public Map<String, Image> GetImageMap() {
        return this.imageMap;
    }

    /////////////////////////////////////////////////////////////
    // FXML Method
    /////////////////////////////////////////////////////////////

    /*
     * FXML Method actionned when Option Button is clicked
     * Return a dialog box and setup the new configuration on the actual controller
     */
    @FXML
    public void Option() throws IOException {

        Dialog<String> dialog = new Dialog<>();

        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Option.fxml"));
        OptionController option = new OptionController(GetTheme(), GetPolice(), GetScore());
        loader.setController(option);
        StackPane view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getStylesheets());

        dialog.getDialogPane().setContent(view);

        ButtonType btnApply = new ButtonType("Appliquer", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Retour", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnApply, btnCancel);

        dialog.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType b) {
                if (b == btnApply)
                    return option.Ok();
                return option.Cancel();
            }
        });

        Optional<String> result = dialog.showAndWait();

        if (!result.get().isBlank()) {
            SetScore(option.GetScore());
            SetTheme(option.GetTheme());
            SetPolice(option.GetPolice());
            SetStyle();
        }
    }

    /*
     * FXML Method actionned when Rule Button is clicked
     * Return a dialog box
     */
    @FXML
    public void Rules() throws IOException {
        Dialog<String> dialog = new Dialog<>();

        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Rule.fxml"));
        RuleController ruleController = new RuleController(GetImageMap());
        loader.setController(ruleController);
        StackPane view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());

        dialog.getDialogPane().setContent(view);

        ButtonType btnApply = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Retour", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnApply, btnCancel);

        dialog.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType b) {
                if (b == btnApply)
                    return ruleController.Ok();
                return ruleController.Cancel();
            }
        });

        Optional<String> result = dialog.showAndWait();

        if (!result.get().isBlank())
            System.out.println("Successfully left Rules Dialog");
        else
            System.out.println("Cancel Rules Dialog");
    }

    /*
     * FXML Method actionned when Contact Button is clicked
     * Return a dialog box
     */
    @FXML
    public void Contact() {
        // TODO Complete Contact
    }

    /*
     * FXML Method actionned when Leave Button is clicked
     * Close Application
     */
    @FXML
    public void Leave() {
        Platform.exit();
    }

    /*
    * FXML Method actionned when Rock Button is clicked
    * Select rock and continue game
    */
    @FXML
    public void Rock() {

    }

    /*
     * FXML Method actionned when Cisor Button is clicked
     * Select Cisor and continue game
     */
    @FXML
    public void Cisor() {

    }

    /*
     * FXML Method actionned when Paper Button is clicked
     * Select Paper and continue game
     */
    @FXML
    public void Paper() {

    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous
    /////////////////////////////////////////////////////////////

    /*
     * return true if theme is actually dark
     */
    public boolean isDark() {
        return GetTheme().equalsIgnoreCase("dark");
    }

    /*
     * return true if theme is actually light
     */
    public boolean isLight() {
        return GetTheme().equalsIgnoreCase("light");
    }

    /*
     * return true if police is actually arial
     */
    public boolean isArial() {
        return GetPolice().equalsIgnoreCase("arial");
    }
    /*
     * return true if police is actually Times New Roman
     */
    public boolean isTNR() {
        return GetPolice().equalsIgnoreCase("Times New Roman");
    }

}
