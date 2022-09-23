/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcomepage;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import rcw5k2rummys21.Switchable;

/**
 * FXML Controller class
 *
 * @author raymondwaidmann
 */
public class Rcw5k2WelcomePageS21Controller extends Switchable implements Initializable {

    @FXML
    private Button instructionsButton;
    @FXML
    private Button playButton;
    @FXML
    private Button aboutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void instructions(ActionEvent event) throws IOException {
        switchTo("/instructions/Rcw5k2InstructionsS21");
    }

    @FXML
    private void play(ActionEvent event) throws IOException {
        //https://stackoverflow.com/questions/25037724/how-to-close-a-java-window-with-a-button-click-javafx-project/41838183
        Stage stage = (Stage) playButton.getScene().getWindow();
        switchTo("Rcw5k2PlayS21");
        stage.close();
    }

    @FXML
    private void about(ActionEvent event) throws IOException {
        switchTo("/about/Rcw5k2AboutS21");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
}
