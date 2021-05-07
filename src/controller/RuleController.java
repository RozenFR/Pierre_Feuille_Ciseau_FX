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
    private Map<String, Image> imageMap; // Map that contains all image from ext/img

    // FXML
    @FXML private ImageView _cisorImg; // Reference to cisor Image on the view
    @FXML private ImageView _paperImg;// Reference to paper Image on the view
    @FXML private ImageView _rockImg; // Reference to rock Image on the view
    @FXML private ImageView _scoreImg; // Reference to score Image on the view

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

    /*
     * Get the map that contains All the image
     */
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
