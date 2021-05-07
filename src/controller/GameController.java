package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.GestionJeu;

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

    // Game
    private GestionJeu game; // Reference to the Game

    // FXML
    @FXML private ImageView _background; // Reference to Background on the view
    @FXML private ImageView _logo; // Reference to logo on the view
    @FXML private ImageView _optionImg; // Reference to option Image on the view
    @FXML private ImageView _contactImg; // Reference to contact Image on the view
    @FXML private ImageView _playerChoice; // Reference to player's choice Image on the view
    @FXML private ImageView _iaChoice; // Reference to ia's choice on the view
    @FXML private ImageView _ruleImg; // Reference to rule Image on the view
    @FXML private ImageView _leaveImg; // Reference to leave Image on the view
    @FXML private ImageView _cisorBtnImg; // Reference to cisor Button Image on the view
    @FXML private ImageView _rockBtnImg; // Reference to rock Button Image on the view
    @FXML private ImageView _paperBtnImg; // Reference to paper Button Image on the view

    @FXML private Label _status; // Reference to status Label on the view
    @FXML private Label _maxScore1; // Reference to max score left rectangle Label on the view
    @FXML private Label _maxScore2; // Reference to max score right rectangle Label on the view
    @FXML private Label _playerScore; // Reference to player's score Label on the view
    @FXML private Label _iaScore; // Reference to ia's score Label on the view

    @FXML private ProgressBar _playerProgress; // Reference to player's Progress Bar on the View
    @FXML private ProgressBar _iaProgress; // Reference to ia's Progress Bar on the View

    @FXML private Button _cisorBtn; // Reference to cisor Button on the view
    @FXML private Button _rockBtn; // Reference to rock Button on the view
    @FXML private Button _paperBtn; // Reference to paper Button on the view

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public GameController(Stage stage, Map<String, Image> imageMap, String theme, String police, int score) {
        // Default
        SetImageMap(imageMap);
        SetStage(stage);
        SetTheme(theme);
        SetPolice(police);
        SetScore(score);
        // Game
        SetGame();
    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {
        SetDefaultImg();
        SetIMaxScore();
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
    * Set the game on the controller
    */
    private void SetGame() {
        this.game = new GestionJeu();
        game.set_maxPointsGagnants(GetScore());
    }

    /*
     * Set the style on the view
     */
    private void SetStyle() {

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
    * Set the background on the view
    */
    private void SetDefaultImg() {
        if (GetTheme().equalsIgnoreCase("dark"))
            this._background.setImage(GetImageMap().get("bg-dark"));
        else if (GetTheme().equalsIgnoreCase("light"))
            this._background.setImage(GetImageMap().get("bg"));
        this._logo.setImage(GetImageMap().get("Logo"));
        this._ruleImg.setImage(GetImageMap().get("rules"));
        this._leaveImg.setImage(GetImageMap().get("cross"));
        this._optionImg.setImage(GetImageMap().get("gear"));
        this._contactImg.setImage(GetImageMap().get("question mark"));
        this._cisorBtnImg.setImage(GetImageMap().get("btn_cisor"));
        this._rockBtnImg.setImage(GetImageMap().get("btn_rock"));
        this._paperBtnImg.setImage(GetImageMap().get("btn_paper"));
    }

    /*
     * Set the image on the view
     */
    private void SetImg() throws FileNotFoundException {
        int nbPlayer = GetGame().get_nombreJoueur();

        if (nbPlayer == 0)
            this._playerChoice.setImage(GetImageMap().get("ciseaux"));
        else if (nbPlayer == 1)
            this._playerChoice.setImage(GetImageMap().get("papier"));
        else if (nbPlayer == 2)
            this._playerChoice.setImage(GetImageMap().get("pierre"));

        int nbIA = GetGame().get_nombreOrdi();

        if (nbIA == 0)
            this._iaChoice.setImage(GetImageMap().get("ciseaux"));
        else if (nbIA == 1)
            this._iaChoice.setImage(GetImageMap().get("papier"));
        else if (nbIA == 2)
            this._iaChoice.setImage(GetImageMap().get("pierre"));

    }

    /*
    * Set the Label Max Score on the view
    */
    private void SetIMaxScore() {
        this._maxScore1.setText(String.valueOf(GetScore()));
        this._maxScore2.setText(String.valueOf(GetScore()));
    }

    /*
     * Set the Label Score for ia and player on the view
     */
    private void SetIScore() {
        this._playerScore.setText(String.valueOf(GetGame().get_pointsJoueur()));
        this._iaScore.setText(String.valueOf(GetGame().get_pointsOrdi()));
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

    /*
    * Get the Game from the controller
    */
    public GestionJeu GetGame() {
        return this.game;
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
            SetGame();
            SetIMaxScore();
            SetIScore();
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
    public void Rock() throws InterruptedException {

        GetGame().set_nombreJoueur(2);
        GetGame().ChoixOrdinateur();
        GameCalculate();

    }

    /*
     * FXML Method actionned when Cisor Button is clicked
     * Select Cisor and continue game
     */
    @FXML
    public void Cisor() {
        GetGame().set_nombreJoueur(0);
        GetGame().ChoixOrdinateur();
        GameCalculate();
    }

    /*
     * FXML Method actionned when Paper Button is clicked
     * Select Paper and continue game
     */
    @FXML
    public void Paper() {

        GetGame().set_nombreJoueur(1);
        GetGame().ChoixOrdinateur();
        GameCalculate();

    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous
    /////////////////////////////////////////////////////////////

    /*
    * Change Scene if victory or defeat of the player
    */
    public void SwitchScene() throws IOException {
        if (GetGame().Gagnant()) {
            if (GetGame().JoueurGagne()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reload.fxml"));
                loader.setController(new ReloadController(GetStage(), GetImageMap(), GetTheme(), GetPolice(), GetScore(), true));
                Parent view = loader.load();
                view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());
                GetStage().setScene(new Scene(view));
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reload.fxml"));
                loader.setController(new ReloadController(GetStage(), GetImageMap(), GetTheme(), GetPolice(), GetScore(), false));
                Parent view = loader.load();
                view.getStylesheets().addAll(GetStage().getScene().getRoot().getStylesheets());
                GetStage().setScene(new Scene(view));
            }
        }
    }

    /*
    * Apply on view and Calculate Point
    */
    public void GameCalculate() {
        int rs = GetGame().CalculerPoints();

        if (rs == -1) {
            this._status.setText("Defaite");
            this._status.setTextFill(Color.RED);
        }
        else if (rs == 0) {
            this._status.setText("Egalite");
            this._status.setTextFill(Color.BLACK);
        }
        else if (rs == 1) {
            this._status.setText("Victoire");
            this._status.setTextFill(Color.ORANGE);
        }

        // Set Image on the view
        try {
            SetImg();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        final KeyFrame kf0 = new KeyFrame(Duration.seconds(0), e -> DisableButton());
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(2.5), e -> ResetImg());
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(2.5), e -> ResetStatus());
        final KeyFrame kf3 = new KeyFrame(Duration.seconds(2.5), e -> UpdateScore());
        final KeyFrame kf4 = new KeyFrame(Duration.seconds(2.5), e -> {
            try {
                SwitchScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        final KeyFrame kf5 = new KeyFrame(Duration.seconds(2.5), e -> EnableButton());
        final Timeline timeline = new Timeline(kf0, kf1, kf2, kf3, kf4, kf5);
        Platform.runLater(timeline::play);
    }

    /*
    * Disable paper, rock and cisor button on the view
    */
    public void DisableButton() {
        this._rockBtn.setDisable(true);
        this._cisorBtn.setDisable(true);
        this._paperBtn.setDisable(true);
    }

    /*
     * Enable paper, rock and cisor button on the view
     */
    public void EnableButton() {
        this._rockBtn.setDisable(false);
        this._cisorBtn.setDisable(false);
        this._paperBtn.setDisable(false);
    }

    /*
    * Reset the image choice ia and player on the view
    */
    public void ResetImg() {
        this._playerChoice.imageProperty().set(null);
        this._iaChoice.imageProperty().set(null);
    }

    /*
    * Reset the status message on the view
    */
    public void ResetStatus() {
        this._status.setText("");
    }

    /*
    * Update the score on the view
    */
    public void UpdateScore() {
        this._playerScore.setText(String.valueOf(GetGame().get_pointsJoueur()));
        this._playerProgress.setProgress(((double)GetGame().get_pointsJoueur()) / ((double)GetScore()));
        this._iaScore.setText(String.valueOf(GetGame().get_pointsOrdi()));
        this._iaProgress.setProgress(((double)GetGame().get_pointsOrdi())/((double)GetScore()));
    }

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
