/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

import java.util.ArrayList;

/**
 *
 * @author raymondwaidmann
 */
public class PlayerModel {
    //creating the hand that contains the 7 cards in play
    //hand ArrayList and (Boolean) Card.inHand are both necessary:
        //hand allows us to quickly and easily access the cards in hand without iterating through the entire deck
        //Card.inHand allows us to quickly check the next card in shuffledDeck and skip it if it is in hand
    
        //both allow for the other to not have to iterate through using a for loop
        //more memory required, faster runtime...
    ArrayList<Card> hand = null;
    
    //constructor
    public PlayerModel(){
        hand = new ArrayList<>();
    }
    
    public int getHandNumber(int i){
        return hand.get(i).getNumber();
    }
    
    public int getSuitNumber(int i){
        return hand.get(i).getSuit();
    }
    
    public void addCard(Card card){ //fire property change
        hand.add(card);
    }
    
    public void setHand(int i, Card card){ //changing the model; firePropertyChange
        hand.set(i, card);
    }
    
    public Card getCard(int i){
        return hand.get(i);
    }
    
    public void clearHand(){ //fire property change
        hand.clear();
    }
}
