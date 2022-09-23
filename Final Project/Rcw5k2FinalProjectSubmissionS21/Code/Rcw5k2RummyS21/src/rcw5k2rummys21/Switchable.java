/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Professor Wergeles
 */

public abstract class Switchable extends AbstractModel implements PropertyChangeListener{ //added extends stage; https://stackoverflow.com/questions/21073941/creating-multiple-stages-in-javafx

    static Player player1;
    static Player player2;
   
    public static Scene scene;
    public static final HashMap<String, Switchable> controllers = new HashMap<>();
    
    private Parent root;  
    
    public static Switchable add(String name) {
        Switchable controller; 
        
        controller = controllers.get(name); 
        
        if(controller == null){
            try {
                FXMLLoader loader = new FXMLLoader(Switchable.class.getResource(name + ".fxml"));
                Parent root = loader.load();
                controller = loader.getController(); 
                controller.setRoot(root); 
                controllers.put(name, controller); 
            } catch (IOException ex) {
                Logger.getLogger(Switchable.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error loading " + name + ".fxml \n\n " + ex); 
                controller = null; 
            } catch (Exception ex){
                System.out.println("Error loading " + name + ".fxml \n\n " + ex); 
                controller = null; 
            }
        }
        return controller; 
    }
    
    public void switchTo(String name) throws IOException {
        
        if(name.equals("Rcw5k2PlayS21")){ //play the game (Main menu is unaccessbile once game has begun
            //instantiating the player class two times allows us to create multiple stages
            player1 = new Player(1);
            player2 = new Player(2);
            Rcw5k2Player2S21Controller.p1Controller = ((Rcw5k2Player1S21Controller)player1.getController());
            Rcw5k2Player1S21Controller.p2Controller = ((Rcw5k2Player2S21Controller)player2.getController());
        }
        else{
            Switchable controller = controllers.get(name); 

            if(controller == null){
                controller = add(name); 
            }

            if(controller != null){
                if(scene != null){
                    scene.setRoot(controller.getRoot());
                }
            }
        }

    }
    
    public void setRoot(Parent root) {
        this.root = root;
    }
    
    public Parent getRoot() {
        return root;
    }
    
    public static Switchable getControllerByName(String name) {
        return controllers.get(name);
    }

}