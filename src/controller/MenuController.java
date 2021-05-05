package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MenuController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
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

    //

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////
    public MenuController() {
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
    public void Rules() {

    }

    @FXML
    public void Contact() {

    }

    @FXML
    public void Leave() {

    }

}
