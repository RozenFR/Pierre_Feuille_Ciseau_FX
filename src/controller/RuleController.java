package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class RuleController {

    /////////////////////////////////////////////////////////////
    // Variable
    /////////////////////////////////////////////////////////////

    // Default
    private Map<String, Image> imageMap;

    // FXML
    @FXML private ImageView _cisorImg;
    @FXML private ImageView _paperImg;
    @FXML private ImageView _rockImg;
    @FXML private ImageView _scoreImg;

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public RuleController(Map<String, Image> imageMap) {
        SetImageMap(imageMap);
    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {
        SetImg();
    }

    /////////////////////////////////////////////////////////////
    // Setter Method
    /////////////////////////////////////////////////////////////

    /*
    * Set Image Map on the controller
    */
    private void SetImageMap(Map<String, Image> imageMap) {
        this.imageMap = imageMap;
    }

    /*
    * Set all images on the view
    */
    private void SetImg() {
        // Cisor
        this._cisorImg.setImage(GetImageMap().get("ciseaux"));
        // Paper
        this._paperImg.setImage(GetImageMap().get("papier"));
        // Rock
        this._rockImg.setImage(GetImageMap().get("pierre"));
        // Score
        this._scoreImg.setImage(GetImageMap().get("score"));
    }

    /////////////////////////////////////////////////////////////
    // Getter Method
    /////////////////////////////////////////////////////////////

    public Map<String, Image> GetImageMap() {
        return this.imageMap;
    }

    /////////////////////////////////////////////////////////////
    // Miscellaneous Method
    /////////////////////////////////////////////////////////////

    /*
     * Method called on previous controller to get positive result
     */
    public String Ok() {
        return "Ok";
    }

    /*
     * Method called on previous controller to get negative result
     */
    public String Cancel() {
        return "";
    }
}
