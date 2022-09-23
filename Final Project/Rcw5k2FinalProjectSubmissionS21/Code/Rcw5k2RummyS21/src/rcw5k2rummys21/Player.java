/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author raymondwaidmann
 */

//this class is called within switchable.java and allows us to create multiple stages for playing the game. 
public class Player extends AbstractModel {
    
    public Switchable controller;

    public Player(int x) throws IOException{
        
        Stage subStage = new Stage();
        
        FXMLLoader loader = null;

        Parent root;
        
        if(x == 1){
            loader = new FXMLLoader(getClass().getResource("Rcw5k2Player1S21.fxml"));
        }

        if(x == 2){
            loader = new FXMLLoader(getClass().getResource("Rcw5k2Player2S21.fxml"));
        }
        
        root = (Parent)loader.load();
        controller = loader.getController();
        
        Scene scene = new Scene(root);
        subStage.setScene(scene);
        subStage.show();

    }
    
    public Switchable getController(){
        return controller;
    }
}

