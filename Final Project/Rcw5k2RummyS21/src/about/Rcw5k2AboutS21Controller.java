/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package about;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import rcw5k2rummys21.Rcw5k2Player1S21Controller;
import rcw5k2rummys21.Switchable;

/**
 * FXML Controller class
 *
 * @author raymondwaidmann
 */
public class Rcw5k2AboutS21Controller extends Switchable implements Initializable, PropertyChangeListener { //step two; implements PropertyChangeListener Interface

    @FXML
    private Button mainMenuButton;
    @FXML
    private ImageView aboutImage;
    @FXML
    private Text textBox;
            
    AboutModel aboutModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        aboutModel = new AboutModel(); //instantiation occurs in the initialize method
        aboutModel.addPropertyChangeListener(this); //step three; connecting the model to the property change listener
    }    

    @FXML
    private void mainMenu(ActionEvent event) throws IOException {
        switchTo("/welcomepage/Rcw5k2WelcomePageS21");
    }

    @FXML
    private void aboutImageClicked(MouseEvent event) {
        aboutModel.updateImage();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //step fice; controller updates the display when the model is changed and calls firePropertyChange
        if(evt.getPropertyName().equals("UpdateAboutImageView")){
            Image image = aboutControllerGetImage(evt.getNewValue().toString());
            aboutImage.setImage(image);
            textBox.setText(evt.getOldValue().toString());
        }
    }
    
    public Image aboutControllerGetImage(String string){
        InputStream is = null;
        try {
            is = new FileInputStream(string);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rcw5k2Player1S21Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(is);
        return image;
    }
      
}
