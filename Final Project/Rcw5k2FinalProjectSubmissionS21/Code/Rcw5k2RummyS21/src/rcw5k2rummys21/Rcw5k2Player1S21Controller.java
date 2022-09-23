/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author raymondwaidmann
 *
 */

//
//     whitespace so Player1 and Player2 Controllers line numbers match
//


public class Rcw5k2Player1S21Controller extends PlayerControllerSuperClass implements Initializable {

    //arraylists of the imageviews and text fields in the GUI
    //arrayList clicked keeps track of card swapping commands from listener methods on the GUI
    ArrayList<ImageView> imageViewArray = null; 
    ArrayList<Text> textArray = null;
    ArrayList<Boolean> clicked = null;
    Image tempImage = null;
    
    //models
    private PlayerModel player1Model; 
    public static Rcw5k2Player2S21Controller p2Controller;
    
    @FXML
    private ImageView cardImage1;
    @FXML
    private ImageView cardImage2;
    @FXML
    private ImageView cardImage3;
    @FXML
    private ImageView cardImage4;
    @FXML
    private ImageView cardImage5;
    @FXML
    private ImageView cardImage6;
    @FXML
    private ImageView cardImage7;
    @FXML
    private ImageView drawDown;
    @FXML
    private ImageView drawUp;
    @FXML
    private Text textBox1;  
    @FXML
    private Text card1TopText;
    @FXML
    private Text card2TopText;
    @FXML
    private Text card3TopText;
    @FXML
    private Text card4TopText;
    @FXML
    private Text card5TopText;
    @FXML
    private Text card6TopText;
    @FXML
    private Text card7TopText;
    @FXML
    private Text card8TopText;
    @FXML
    private Text card1BottomText;
    @FXML
    private Text card2BottomText;
    @FXML
    private Text card3BottomText;
    @FXML
    private Text card4BottomText;
    @FXML
    private Text card5BottomText;
    @FXML
    private Text card6BottomText;
    @FXML
    private Text card7BottomText;
    @FXML
    private Text card8BottomText;
    @FXML
    private Button endTurnButton;
    @FXML
    private Button rummyButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        super.initializeDeck();

        imageViewArray = new ArrayList<>();
        imageViewArray.add(cardImage1);
        imageViewArray.add(cardImage2);
        imageViewArray.add(cardImage3);
        imageViewArray.add(cardImage4);
        imageViewArray.add(cardImage5);
        imageViewArray.add(cardImage6);
        imageViewArray.add(cardImage7);
        imageViewArray.add(drawUp);
        
        clicked = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            clicked.add(false);
        }
        
        player1Model = new PlayerModel(); //instatiating the model
        for(int i = 0; i < 8; i++){
            initializeHand(i);
        }
        
        textArray = new ArrayList<>();
        textArray.add(card1TopText);
        textArray.add(card2TopText);
        textArray.add(card3TopText);
        textArray.add(card4TopText);
        textArray.add(card5TopText);
        textArray.add(card6TopText);
        textArray.add(card7TopText);
        textArray.add(card8TopText);
        textArray.add(card1BottomText);
        textArray.add(card2BottomText);
        textArray.add(card3BottomText);
        textArray.add(card4BottomText);
        textArray.add(card5BottomText);
        textArray.add(card6BottomText);
        textArray.add(card7BottomText);
        textArray.add(card8BottomText);
        
    }   
    
    @FXML
    private void draw(MouseEvent event) {
        if(getPlayer1Turn() == false){
            textBox1.setText("PLAYER 2'S TURN; NOT YOURS (DRAW)");
        } else if(getTurnTaken() == true){
            textBox1.setText("CARD ALREADY DRAWN ON THIS TURN, SWAP WITH DISCARD OR PASS AND END TURN (DRAW)");
        } else if(getCardSwapped() == true){
            textBox1.setText("CARD ALREADY SWAPPED THIS TURN, END TURN OR CLAIM RUMMY!!!");
        } else {
            if (getCounter() == 52) { 
                setCounter(0);
            }

            while (isInHand() == true){
                increaseCounter();
            }

            Card card = getDeck().shuffledDeck.get(getCounter());
            increaseCounter();
            tempImage = getCardImage(card);
            setImageView(drawUp, tempImage);
            turnTaken();
            }

    }
    
    @FXML
    private void card1Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 0);
    }

    @FXML
    private void card2Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 1);
    }
    
    @FXML
    private void card3Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 2);
    }

    @FXML
    private void card4Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 3);
    }

    @FXML
    private void card5Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 4);
    }

    @FXML
    private void card6Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 5);
    }

    @FXML
    private void card7Clicked(MouseEvent event) throws InterruptedException {
        cardClicked(clicked, 6);
    }
    
    @FXML
    private void card8Clicked(MouseEvent event) throws InterruptedException {
        if(getPlayer1Turn() == false){
            textBox1.setText("PLAYER 2'S TURN; NOT YOURS (DISCARD)");
        } else{
            cardClicked(clicked, 7);
        }
    }
    
    @FXML
    private void rummy(ActionEvent event) {
        if (getRummy(player1Model) == true){
            displayAlert(0, "CONGRATULATIONS", "RUMMY!!! PLAYER 1 WINS!!!");
            reset();
        }
        else {
            textBox1.setText("NOT RUMMY!!! TRY AGAIN!!!");
        }
    }

    @FXML
    private void endTurn(ActionEvent event) {
        if(getPlayer1Turn() == false){
            textBox1.setText("PLAYER 2'S TURN; NOT YOURS (END TURN)");
        } else if(getTurnTaken() == false){
            textBox1.setText("TURN NOT TAKEN, SWAP WITH DISCARD, DRAW AND SWAP, OR DRAW AND PASS AND END TURN (END TURN)");
        } else{
            p2Controller.p1SetImageView(tempImage);
            p2Controller.p1UpdateEndTurnButton();
            textBox1.setText("TURN ENDED, PLAYER 2'S TURN");
            super.endTurn();
            endTurnButton.setText("PLAYER 2'S TURN");
            
            setClickedArray(clicked, 9);
            for(int i = 0; i < 8; i++){
                textArray.get(i).setText("");
                textArray.get(i+8).setText("");
            }
            
        }
        
    }
    
    public void initializeHand(int localCounter){
        Card card;
        if (localCounter == 7){
            card = getDeck().shuffledDeck.get(14);
        } else {
            card = getDeck().shuffledDeck.get(getCounter());
        }
        
        //adding the card to the hand (skipping card 7 since it is the first card drawn from the deck when the game starts)
        if (localCounter < 7){
            card.setInHand(true);
            player1Model.addCard(card);
        }

        tempImage = getCardImage(card);
        setImageView(imageViewArray.get(localCounter), tempImage); 

        if (localCounter == 7) { //only runs on the last time
            setImageView(drawDown, getBackImage());
        }
        
        if (localCounter != 7){
            increaseCounter();
        }
        
        turnTaken();
    }

    private void cardClicked(ArrayList<Boolean> clicked, int index) {
        Boolean sentinel = false;
        String mostRecentClickedCard = "none";

        //iterating through the 8 face up cards on the table
        for(int i = 0; i < 8; i++){

            //if you already clicked the card you just clicked (reset)
            if (clicked.get(index) == true){ 
                setClickedArray(clicked, 9);
                textBox1.setText("");

                textArray.get(index).setText("");
                textArray.get(index+8).setText("");
                sentinel = true;
                break;
            }

            //if a different card has been clicked previously (swap)
            if(clicked.get(i) == true){
                setClickedArray(clicked, 9);
                textBox1.setText("");

                textArray.get(i).setText("");
                textArray.get(i+8).setText("");

                ImageView iv1 = imageViewArray.get(i);
                ImageView iv2 = imageViewArray.get(index);

                Card card1;
                Card card2;

                //if the card previously selected is the one from the drawUp pile
                if (i == 7){ 
                    if(getCardSwapped() == true){
                        textBox1.setText("CARD ALREADY SWAPPED THIS TURN, END TURN OR CLAIM RUMMY!!!");
                        return;
                    }
                    cardSwapped();
                    turnTaken();
                    mostRecentClickedCard = "notDrawUp";
                    card1 = getDeck().shuffledDeck.get(getCounter()-1);   
                    card2 = player1Model.getCard(index);

                    card1.setInHand(true);
                    card2.setInHand(false);

                    //https://howtodoinjava.com/java/collections/arraylist/swap-two-elements-arraylist/
                    Collections.swap(getDeck().shuffledDeck, card1.getIndexInShuffledDeck(), card2.getIndexInShuffledDeck());

                    //appropriately changing the index of each of the cards that got swapped in the shuffled deck
                    int temp = card1.getIndexInShuffledDeck();
                    card1.setIndexInShuffledDeck(card2.getIndexInShuffledDeck());
                    card2.setIndexInShuffledDeck(temp);

                    player1Model.setHand(index, card1);
                }

                //if the most recent card clicked is from the drawUp pile
                else if (index == 7){ 
                    if(getCardSwapped() == true){
                        textBox1.setText("CARD ALREADY SWAPPED THIS TURN, END TURN OR CLAIM RUMMY!!!");
                        return;
                    }
                    cardSwapped();
                    turnTaken();
                    mostRecentClickedCard = "DrawUp";
                    card1 = player1Model.getCard(i);
                    card2 = getDeck().shuffledDeck.get(getCounter()-1);

                    card1.setInHand(false);
                    card2.setInHand(true);

                    //https://howtodoinjava.com/java/collections/arraylist/swap-two-elements-arraylist/
                    Collections.swap(getDeck().shuffledDeck, card1.getIndexInShuffledDeck(), card2.getIndexInShuffledDeck());

                    //appropriately changing the index of each of the cards that got swapped in the shuffled deck
                    int temp = card1.getIndexInShuffledDeck();
                    card1.setIndexInShuffledDeck(card2.getIndexInShuffledDeck());
                    card2.setIndexInShuffledDeck(temp);

                    player1Model.setHand(i, card2);
                }

                //swapping cards within the hand
                else {
                    card1 = player1Model.getCard(i); //click the discard, then the hand error //i is the card selected, index is the card most recently clicked
                    card2 = player1Model.getCard(index); //click a card, then the discard error

                    player1Model.setHand(i, card2);
                    player1Model.setHand(index, card1);
                }

                Image image1 = getCardImage(card1);
                Image image2 = getCardImage(card2);

                setImageView(iv1, image2); 
                if(mostRecentClickedCard.equals("DrawUp")){
                    tempImage = image1;
                }
                setImageView(iv2, image1); 
                if(mostRecentClickedCard.equals("notDrawUp")){
                    tempImage = image2;
                }

                sentinel = true;
                break;
            }
        }

        //if no card has been previously clicked (enable swap)
        if(sentinel == false){
            setClickedArray(clicked, index);
            textBox1.setText("SELECT A CARD TO SWAP WITH THE ONE SELECTED, OR CLICK THE SELECTED CARD TO DESELECT IT");

            textArray.get(index).setText("CARD SELECTED");
            textArray.get(index+8).setText("CARD SELECTED");
        }
    }
    
    private void reset(){
        setCounter(0);
        resetDeck();

        for(int i = 0; i < 8; i++){
            clicked.set(i, false);
        }
        
        player1Model.clearHand();
        for(int i = 0; i < 8; i++){
            initializeHand(i);
        }
        
        endTurnButton.setText("END TURN");
        textBox1.setText("WELCOME TO RUMMY!! PLAYER 1 BEGINS THE GAME");
        
        p2Controller.p1Reset();
        
    }
    
    public void p2SetImageView(Image image){
        setImageView(drawUp, image);
    }
    
    public void p2UpdateEndTurnButton(){
        endTurnButton.setText("END TURN");
        textBox1.setText("PLAYER 2 FINISHED THEIR TURN; YOUR TURN!!!");
    }
    
    public void p2Reset(){
        reset();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}