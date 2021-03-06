package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OptionController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Stage stage; // Reference to actual stage
    private String theme; // Theme to display
    private String police; // police to display
    private int score; // Max Score Settings

    // FXML
    @FXML private RadioButton _dark; // Select theme dark if selected
    @FXML private RadioButton _light; // Select theme light if selected

    @FXML private Label _score; // Score Label on the view
    @FXML private Slider _slider; // Slider on the view
    @FXML private ChoiceBox _polices; // ChoiceBox on the View

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public OptionController(Stage stage, String theme, String police, int score) {
        SetStage(stage);
        SetTheme(theme);
        SetPolice(police);
        SetScore(score);
    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {
        SetChoiceBox(this.police);
        try {
            SetBTheme(this.theme);
            SetSliderScore(this.score);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    * Set the Theme on the Controller
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
    * Set the score on the controller
    */
    private void SetScore(int score) {
        this.score = score;
    }

    /*
    * Set the ChoiceBox on the view
    */
    private void SetChoiceBox(String police) {
        String [] polices = {"arial", "times new roman"};
        GetIPolices().getItems().addAll(FXCollections.observableArrayList(polices));
    }

    /*
    * Set the theme selected button on the view
    */
    private void SetBTheme(String theme) throws Exception {
        if (theme.equalsIgnoreCase("dark")) {
            GetILight().setSelected(false);
            GetIDark().setSelected(true);
        }
        else if (theme.equalsIgnoreCase("light")) {
            GetIDark().setSelected(false);
            GetILight().setSelected(true);
        }
    }

    /*
    * Set the score view on the view
    */
    private void SetSliderScore(int score) throws Exception {
        if (score == 0)
            throw new Exception("SetSliderScore <score> : score need to be between 1 and 10.");
        GetIScore().setText(String.valueOf(score));
        GetISlider().setValue(score);
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
        return this.theme;
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
    * Get the Dark Button from the view
    */
    public RadioButton GetIDark() {
        return this._dark;
    }

    /*
    * Get the Light Button from the view
    */
    public RadioButton GetILight() {
        return this._light;
    }

    /*
    * Get the Score Label from the view
    */
    public Label GetIScore() {
        return this._score;
    }

    /*
    * Get the ChoiceBox from the view
    */
    public ChoiceBox GetIPolices() {
        return this._polices;
    }

    /*
    * Get the Slider from the view
    */
    public Slider GetISlider() {
        return this._slider;
    }

    /////////////////////////////////////////////////////////////
    // FXML Method
    /////////////////////////////////////////////////////////////

    /*
    * FXML Method actionned when slider is modified
    * Update the score Label and update the slider (only integer)
    */
    @FXML
    public void UpdateScoreSlider() {
        double score = GetISlider().getValue();
        if (Math.floor(score) == 1) {
            GetIScore().setText("1");
            GetISlider().setValue(1);
        }
        else if (Math.floor(score) == 2) {
            GetIScore().setText("2");
            GetISlider().setValue(2);
        }
        else if (Math.floor(score) == 3) {
            GetIScore().setText("3");
            GetISlider().setValue(3);
        }
        else if (Math.floor(score) == 4) {
            GetIScore().setText("4");
            GetISlider().setValue(4);
        }
        else if (Math.floor(score) == 5) {
            GetIScore().setText("5");
            GetISlider().setValue(5);
        }
        else if (Math.floor(score) == 6) {
            GetIScore().setText("6");
            GetISlider().setValue(6);
        }
        else if (Math.floor(score) == 7) {
            GetIScore().setText("7");
            GetISlider().setValue(7);
        }
        else if (Math.floor(score) == 8) {
            GetIScore().setText("8");
            GetISlider().setValue(8);
        }
        else if (Math.floor(score) == 9) {
            GetIScore().setText("9");
            GetISlider().setValue(9);
        }
        else if (Math.floor(score) == 10) {
            GetIScore().setText("10");
            GetISlider().setValue(10);
        }
    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous Method
    /////////////////////////////////////////////////////////////

    /*
    * Method called on previous controller to get positive result
    */
    public String Ok() {
        SetTheme(GetIDark().isSelected() ? "dark" : "light");
        SetPolice(GetIPolices().getValue() != null ? GetIPolices().getValue().toString() : GetPolice());
        SetScore((int) Math.round(Integer.parseInt(GetIScore().getText())));
        return "Ok";
    }

    /*
    * Method called on previous controller to get negative result
    */
    public String Cancel() {
        return "";
    }
}
