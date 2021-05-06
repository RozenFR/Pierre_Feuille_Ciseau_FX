package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class ContactController {

    /////////////////////////////////////////////////////////////
    // Constructor
    /////////////////////////////////////////////////////////////

    public ContactController() {

    }

    /////////////////////////////////////////////////////////////
    // Initializer
    /////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {

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
