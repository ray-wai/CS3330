/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2mvctimerstopwatchfxmls21;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2MVCTimerStopwatchFXMLS21 extends Application {
    
    public String filename = "Rcw5k2MVCTimerStopwatchFXMLS21View.fxml";
    public String appName = "StopwatchMVC";
    
    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource(filename));
            Scene scene = new Scene(root);
            stage.setTitle(appName);
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
