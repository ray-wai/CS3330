/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.beans.PropertyChangeEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author raymondwaidmann
 */
public class PlayerControllerSuperClass extends Switchable {
    //BEGIN CONTROLLERS (RCW5K2PLAYER1/2S21CONTROLLER.JAVA) SUPERCLASS CODE
    //static variables are "game properties"; properties and game data that are identical between players
    //i like to think of this as the "game model" which helps seperate the code from the playerModels (PlayerModel.java)
    
    //iterates through the shuffled discard deck of cards
    public static int counter = 0;
    
    //Creating the deck
    public static Deck deck;
    
    public static boolean initialized = false;
    
    public static boolean player1Turn = true;
    
    public static boolean turnTaken = false;
    
    public static boolean cardSwapped = false;
    
    public void initializeDeck(){
        if(initialized == false){
            deck = new Deck();
            initialized = true;
        }
    }
    
    public void resetDeck(){
        initialized = false;
        deck = null;
        initializeDeck();
    }
    
    public void increaseCounter(){
        counter++;
    }
    
    public int getCounter(){
        return PlayerControllerSuperClass.counter;
    }
    
    public void setCounter(int x){
        PlayerControllerSuperClass.counter = x;
    }
    
    public Deck getDeck(){
        return PlayerControllerSuperClass.deck;
    }
    
    public boolean getPlayer1Turn(){
        return PlayerControllerSuperClass.player1Turn;
    }
    
    public void setPlayer1Turn(){
        PlayerControllerSuperClass.player1Turn = true;
    }
    
    public void changePlayer1Turn(){
        if (PlayerControllerSuperClass.player1Turn == true){
            PlayerControllerSuperClass.player1Turn = false;
        }
        else {
            PlayerControllerSuperClass.player1Turn = true;
        }
    }
    
    public void turnTaken(){
        PlayerControllerSuperClass.turnTaken = true;
    }
    
    public void resetTurnTaken(){
        PlayerControllerSuperClass.turnTaken = false;
    }
    
    public boolean getTurnTaken(){
        return PlayerControllerSuperClass.turnTaken;
    }
    
    public void cardSwapped(){
        PlayerControllerSuperClass.cardSwapped = true;
    }
    
    public void resetCardSwapped(){
        PlayerControllerSuperClass.cardSwapped = false;
    }
    
    public boolean getCardSwapped(){
        return PlayerControllerSuperClass.cardSwapped;
    }
    
    //beginning of methods that are identical between controllers and do not modify view elements directly
    public boolean getRummy(PlayerModel model){
        boolean rummyTopNumber = true;
        boolean rummyTopStraight = true;
        boolean rummyBottomNumber = true;
        boolean rummyBottomStraight = true;
        boolean rummy = false;
        
        //check for set of 4; same number
        for(int i = 0; i < 3; i++){
            if(model.getHandNumber(i) != model.getHandNumber(i+1)){
                rummyTopNumber = false;
                break;
            }
        }
        
        //check for set of 4; straight
        for(int i = 0; i < 3; i++){
            if((model.getHandNumber(i) + 1) != model.getHandNumber(i+1)){
                rummyTopStraight = false;
                break;
            }
            
            if(model.getSuitNumber(i) != model.getSuitNumber(i+1)){
                rummyTopStraight = false;
                break;    
            }
        }
        
        //check for set of 3; same number
        for(int i = 4; i < 6; i++){
            if(model.getHandNumber(i) != model.getHandNumber(i+1)){
                rummyBottomNumber = false;
                break;
            }
        }
        
        //check for set of 3; straight
        for(int i = 4; i < 6; i++){
            if((model.getHandNumber(i) + 1) != model.getHandNumber(i+1)){
                rummyBottomStraight = false;
                break;
            }
            
            if(model.getSuitNumber(i) != model.getSuitNumber(i+1)){
                rummyBottomStraight = false;
                break;    
            }
        }
        
        if (rummyTopNumber == true || rummyTopStraight == true){
            if (rummyBottomNumber == true || rummyBottomStraight == true){
                rummy = true;
            }
        }
        
        return rummy;
    }
    
    public void endTurn(){
        changePlayer1Turn();
        resetTurnTaken();
        resetCardSwapped();
    }
    
    public void setImageView(ImageView iv, Image image){
        iv.setImage(image);
        iv.setFitHeight(1056*0.2);
        iv.setFitWidth(691*0.2);
    }
    
    public boolean isInHand(){
        return getDeck().shuffledDeck.get(getCounter()).getInHand();
    }
    
    public void setClickedArray(ArrayList<Boolean> clicked, int i) {
        for(int j = 0; j < 8; j++){
            clicked.set(j, Boolean.FALSE);
            if(j == i){
                clicked.set(j, Boolean.TRUE);
            }
        }
    }
    
    public Image getCardImage(Card card){
        String string = (System.getProperty("user.dir") + "/src/rcw5k2rummys21CardImages/" + card.getImage());
        InputStream is = null;
        try {
            is = new FileInputStream(string);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rcw5k2Player2S21Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(is);
        return image;
    }
    
    public Image getBackImage(){
        String string = (System.getProperty("user.dir") + "/src/rcw5k2rummys21CardImages/blue_back.png");
        InputStream is = null;
        try {
            is = new FileInputStream(string);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rcw5k2Player1S21Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(is);
        return image;
    }
    
    //https://code.makery.ch/blog/javafx-dialogs-official/
    public void displayAlert(int type, String header, String content){
        Alert alert;
        
        if(type == 1){
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
 
        alert.setTitle("ALERT");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
