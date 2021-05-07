package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Map;

public class ReloadController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Stage stage;
    private String theme;
    private String police;
    private int score;
    private Map<String, Image> imageMap;
    private boolean victory;

    // FXML
    @FXML private ImageView _background;
    @FXML private ImageView _logo;
    @FXML private ImageView _option;
    @FXML private ImageView _contact;
    @FXML private ImageView _rule;
    @FXML private ImageView _leave;
    @FXML private ImageView _oui;
    @FXML private ImageView _non;

    @FXML private Label _status;

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
        SetStatus(GetVictory());
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

    private void SetVictory (boolean victory) {
        this.victory = victory;
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
    public void SetStatus(boolean victory) {

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
    * Get the status of the party from the controller
    */
    private boolean GetVictory () {
        return this.victory;
    }

    /////////////////////////////////////////////////////////////
    // FXML Method
    /////////////////////////////////////////////////////////////

    @FXML
    public void Option() {

    }

    @FXML
    public void Play() {

    }

    @FXML
    public void Oui() {

    }

    @FXML
    public void Non() {

    }

    @FXML
    public void Rules() {

    }

    @FXML
    public void Contact() {

    }

    @FXML
    public void Leave() {

    }

}
