/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import rcw5k2rummys21.Switchable;

/**
 * FXML Controller class
 *
 * @author raymondwaidmann
 */
public class Rcw5k2InstructionsS21Controller extends Switchable implements Initializable {

    @FXML
    private Button mainMenuButton;
    @FXML
    private WebView webView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        webView.getEngine().load("https://youtu.be/ZR4nrzRJuSE"); 
    }    

    @FXML
    private void mainMenu(ActionEvent event) throws IOException {
        switchTo("/welcomepage/Rcw5k2WelcomePageS21");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
