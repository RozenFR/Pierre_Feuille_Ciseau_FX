package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class ReloadController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Stage stage; // Reference to actual stage
    private String theme; // Theme to display
    private String police; // Police to display
    private int score; // Max Score Settings
    private Map<String, Image> imageMap; // Contains All Image from ext/img
    private boolean victory; // Victory or not of the player

    // FXML
    @FXML private ImageView _background; // Background Image of the view
    @FXML private ImageView _logo; // Logo Image of the view
    @FXML private ImageView _option; // Option Image of the view
    @FXML private ImageView _contact; // Contact Image of the view
    @FXML private ImageView _rule; // Rule Image of the view
    @FXML private ImageView _leave; // Leave Image of the view
    @FXML private ImageView _oui; // Confirm Image of the view
    @FXML private ImageView _non; // Back Image of the view

    @FXML private Label _status; // Label status of the view (Victory or Defeat)

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public ReloadController(Stage stage, Map<String, Image> imageMap, String theme, String police, int score, boolean victory) {
        SetStage(stage);
        SetImageMap(imageMap);
        SetTheme(theme);
        SetPolice(police);
        SetScore(score);
        SetVictory(victory);
    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {
        SetImg();
        SetStatus();
    }

    /////////////////////////////////////////////////////////////
    // Setter Method
    /////////////////////////////////////////////////////////////

    /*
    * Set the stage on the controller
    */
    private void SetStage(Stage stage) {
        this.stage = stage;
    }

    /*
    * Set the image map on the controller
    */
    private void SetImageMap(Map<String, Image> imageMap) {
        this.imageMap = imageMap;
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
    * Set the Score on the controller
    */
    private void SetScore(int score) {
        this.score = score;
    }

    /*
     * Set if victory on the controller
     */
    private void SetVictory (boolean victory) {
        this.victory = victory;
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
                this._background.setImage(GetImageMap().get("bg-dark"));
                if (isArial())
                    view.getStylesheets().add(getClass().getResource("/style/dark - Arial.scss").toExternalForm());
                else if (isTNR())
                    view.getStylesheets().add(getClass().getResource("/style/dark - Times New Roman.scss").toExternalForm());
            }
            else if (isLight()) {
                this._background.setImage(GetImageMap().get("bg"));
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
    * Set all the image on the View
    */
    private void SetImg() {
        if (GetTheme().equalsIgnoreCase("dark"))
            this._background.setImage(GetImageMap().get("bg-dark"));
        else if (GetTheme().equalsIgnoreCase("light"))
            this._background.setImage(GetImageMap().get("bg"));
        this._logo.setImage(GetImageMap().get("Logo"));
        this._rule.setImage(GetImageMap().get("rules"));
        this._leave.setImage(GetImageMap().get("cross"));
        this._option.setImage(GetImageMap().get("gear"));
        this._contact.setImage(GetImageMap().get("question mark"));
        this._oui.setImage(GetImageMap().get("yes"));
        this._non.setImage(GetImageMap().get("no"));
    }

    /*
    * Set the Image on the View
    */
    public void SetStatus() {
        if (GetVictory()) {
            this._status.setText("VICTOIRE");
            this._status.setStyle("-fx-text-fill: orange");
        }
        else {
            this._status.setText("DEFAITE");
            this._status.setStyle("-fx-text-fill: red");
        }
    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    /*
     * Get the stage from the controller
     */
    private Stage GetStage() {
        return this.stage;
    }

    /*
     * Get the image map from the controller
     */
    private Map<String, Image> GetImageMap() {
        return this.imageMap;
    }

    /*
     * Get the theme from the controller
     */
    private String GetTheme() {
        return this.theme;
    }

    /*
     * Get the police from the controller
     */
    private String GetPolice() {
        return this.police;
    }

    /*
     * Get the Score from the controller
     */
    private int GetScore() {
        return this.score;
    }

    /*
    * Get the status of the game from the controller
    */
    private boolean GetVictory () {
        return this.victory;
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
        OptionController option = new OptionController(GetStage(), GetTheme(), GetPolice(), GetScore());
        loader.setController(option);
        StackPane view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());

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
            try {
                SetStyle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * FXML Method actionned when Yes Button is clicked
     * Switch Scene to Game Scene and launch a new game
     */
    @FXML
    public void Oui() throws IOException {
        // Setup view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Game.fxml"));
        GameController gameController = new GameController(GetStage(), GetImageMap(), GetTheme(), GetPolice(), GetScore());
        loader.setController(gameController);
        Parent view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());

        GetStage().setScene(new Scene(view));
    }

    /*
     * FXML Method actionned when No Button is clicked
     * Switch Scene to Menu Scene
     */
    @FXML
    public void Non() throws IOException {
        // Setup view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
        MenuController menuController = new MenuController(GetStage(), GetImageMap(), GetTheme(), GetPolice(), GetScore());
        loader.setController(menuController);
        Parent view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());

        GetStage().setScene(new Scene(view));
    }

    /*
     * FXML Method actionned when Rules Button is clicked
     * Return a dialog box with the rules
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
     * Return a dialog box with all the information to contact someone
     */
    @FXML
    public void Contact() throws IOException {
        Dialog<String> dialog = new Dialog<>();

        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Contact.fxml"));
        ContactController contactController = new ContactController();
        loader.setController(contactController);
        Parent view = loader.load();
        view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());

        dialog.getDialogPane().setContent(view);

        ButtonType btnApply = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Retour", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnApply, btnCancel);

        dialog.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType b) {
                if (b == btnApply)
                    return contactController.Ok();
                return contactController.Cancel();
            }
        });

        Optional<String> result = dialog.showAndWait();

        if (!result.get().isBlank())
            System.out.println("Successfully left Contact Dialog");
        else
            System.out.println("Cancel Contact Dialog");
    }

    /*
     * FXML Method actionned when Leave Button is clicked
     * Exit Application
     */
    @FXML
    public void Leave() {
        Platform.exit();
    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous Method
    /////////////////////////////////////////////////////////////

    /*
     * Return true if theme is dark
     */
    public boolean isDark() {
        return GetTheme().equalsIgnoreCase("dark");
    }

    /*
     * Return true if theme is light
     */
    public boolean isLight() {
        return GetTheme().equalsIgnoreCase("light");
    }

    /*
     * Return true if police is arial
     */
    public boolean isArial() {
        return GetPolice().equalsIgnoreCase("arial");
    }

    /*
     * Return true if police is Times New Roman
     */
    public boolean isTNR() {
        return GetPolice().equalsIgnoreCase("times new roman");
    }

}
