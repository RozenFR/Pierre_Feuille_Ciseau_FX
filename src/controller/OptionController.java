package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OptionController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    String theme;
    String police;
    int score;

    @FXML private RadioButton _dark;
    @FXML private RadioButton _light;

    @FXML private Label _score;
    @FXML private Slider _slider;
    @FXML private ChoiceBox _polices;

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public OptionController(String theme, String police, int score) {
        this.theme = theme;
        this.police = police;
        this.score = score;
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

    private void SetChoiceBox(String police) {
        String [] polices = {"arial", "times new roman"};
        GetIPolices().getItems().addAll(FXCollections.observableArrayList(polices));

    }

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

    private void SetSliderScore(int score) throws Exception {
        if (score == 0)
            throw new Exception("SetSliderScore <score> : score need to be between 1 and 10.");
        GetIScore().setText(String.valueOf(score));
        GetISlider().setValue(score);
    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    public RadioButton GetIDark() {
        return this._dark;
    }

    public RadioButton GetILight() {
        return this._light;
    }

    public Label GetIScore() {
        return this._score;
    }

    public ChoiceBox GetIPolices() {
        return this._polices;
    }

    public Slider GetISlider() {
        return this._slider;
    }

    public String Ok() {
        this.theme = GetIDark().isSelected() ? "dark" : "light";
        this.police = GetIPolices().getValue() != null ? GetIPolices().getValue().toString() : "";
        this.score = (int) Math.round(Integer.parseInt(GetIScore().getText()));
        return "Ok";
    }

    public String Cancel() {
        return "";
    }

    public String GetTheme() {
        return this.theme;
    }

    public String GetPolice() {
        return this.police;
    }

    public int GetScore() {
        return this.score;
    }

    /////////////////////////////////////////////////////////////
    // FXML Method
    /////////////////////////////////////////////////////////////

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
}
