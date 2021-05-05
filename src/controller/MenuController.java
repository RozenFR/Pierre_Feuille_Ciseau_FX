package controller;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class MenuController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Stage stage;
    private String theme;
    private String police;
    private int score;

    // FXML
    @FXML private ImageView _background;
    @FXML private ImageView _optionImg;
    @FXML private ImageView _logo;
    @FXML private ImageView _playImg;
    @FXML private ImageView _ruleImg;
    @FXML private ImageView _contactImg;
    @FXML private ImageView _leaveImg;

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public MenuController(Stage stage, String theme, String police, int score) {
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

    private void SetStage(Stage stage) {
        this.stage = stage;
    }

    private void SetTheme(String theme) {
        this.theme = theme;
    }

    private void SetPolice(String police) {
        this.police = police;
    }

    private void SetScore(int score) {
        this.score = score;
    }

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

    private void SetImg() throws FileNotFoundException {

        // Background
        InputStream streamBG = getClass().getResourceAsStream("/ext/img/bg.jpg");
        Image bg = new Image(streamBG);
        this._background.setImage(bg);

        // option
        InputStream streamOption = getClass().getResourceAsStream("/ext/img/gear.png");
        Image option = new Image(streamOption);
        this._optionImg.setImage(option);

        // Logo
        InputStream streamLogo = getClass().getResourceAsStream("/ext/img/Logo.png");
        Image logo = new Image(streamLogo);
        this._logo.setImage(logo);

        // Play
        InputStream streamPlay = getClass().getResourceAsStream("/ext/img/play.png");
        Image play = new Image(streamPlay);
        this._playImg.setImage(play);

        // Rules
        InputStream streamRule = getClass().getResourceAsStream("/ext/img/rules.png");
        Image rule = new Image(streamRule);
        this._ruleImg.setImage(rule);

        // Contact
        InputStream streamContact = getClass().getResourceAsStream("/ext/img/question mark.png");
        Image contact = new Image(streamContact);
        this._contactImg.setImage(contact);

        // Leave
        InputStream streamLeave = getClass().getResourceAsStream("/ext/img/cross.png");
        Image leave = new Image(streamLeave);
        this._leaveImg.setImage(leave);

    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    public Stage GetStage() {
        return this.stage;
    }

    public ImageView GetBG() {
        return this._background;
    }

    public String GetTheme() {
        return theme;
    }

    public String GetPolice() {
        return this.police;
    }

    public int GetScore() {
        return this.score;
    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous
    /////////////////////////////////////////////////////////////

    public boolean isDark() {
        return GetTheme().equalsIgnoreCase("dark");
    }

    public boolean isLight() {
        return GetTheme().equalsIgnoreCase("light");
    }

    public boolean isArial() {
        return GetPolice().equalsIgnoreCase("arial");
    }

    public boolean isTNR() {
        return GetPolice().equalsIgnoreCase("Times New Roman");
    }

    /////////////////////////////////////////////////////////////
    // FXML Method
    /////////////////////////////////////////////////////////////

    @FXML
    public void Option() throws IOException {

        Dialog<String> dialog = new Dialog<>();

        // Setup FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Option.fxml"));
        OptionController option = new OptionController(GetTheme(), GetPolice(), GetScore());
        loader.setController(option);
        StackPane view = loader.load();
        view.getStylesheets().addAll(GetBG().getScene().getStylesheets());

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

    @FXML
    public void Play() {

    }

    @FXML
    public void Rules() {

    }

    @FXML
    public void Contact() {

    }

    @FXML
    public void Leave() {
        Platform.exit();
    }

}
