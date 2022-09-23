/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Professor Wergeles
 */
public class Rcw5k2MyNewStopWatchS21 extends Application {
    
    private String appName = "New Stopwatch";
    
    @Override
    public void start(Stage primaryStage) {

        AnalogStopWatch analog = new AnalogStopWatch();
        
        Scene scene = new Scene(analog.getRootContainer(), analog.getWidth(), analog.getHeight());        
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //bonus bug (both implementations of analog.setTickTimeInSeconds work 
//        analog.setTickTimeInSeconds(0.01);    
        analog.start();
        analog.setTickTimeInSeconds(0.01); 
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
