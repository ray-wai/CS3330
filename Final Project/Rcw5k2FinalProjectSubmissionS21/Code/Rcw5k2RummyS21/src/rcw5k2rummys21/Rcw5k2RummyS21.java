/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.beans.PropertyChangeEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2RummyS21 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/welcomepage/Rcw5k2WelcomePageS21.fxml"));
        Scene scene = new Scene(root);
        
        Switchable.scene = scene; 
        Switchable switchable = new Switchable() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        switchable.switchTo("/welcomepage/Rcw5k2WelcomePageS21");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
