/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2timerstopwatchs21;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author raymondwaidmann
 */

//Labels should be used for text that will not change frequently
//Text Objects should be used for text that does change frequently
public class Rcw5k2TimerStopwatchS21 extends Application {
    
    private String appName = "StopWatch";
    
    @Override
    public void start(Stage primaryStage) {
        
        AnalogStopWatchS21 analog = new AnalogStopWatchS21();
        if (!analog.getTimerTimeError()){ //no error in the input text field
            Scene scene = new Scene(analog.getRootContainer(), analog.getWidth(), 
                    analog.getHeight());
            primaryStage.setTitle(appName);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            analog.setTickTimeInSeconds(0.01);       
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
