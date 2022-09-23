/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

/**
 *
 * @author raymondwaidmann
 * Each of the 52 card objects is unique and inserted into an arraylist deck. 
 *      number: 2-10, J(11), Q(12), K(13), A(14)
 *      suit: C, D, H, S
 *      inHand: boolean value that signals whether the card is in a players hand (true) or in the deck (false)
 *      indexInShuffledDeck: integer value that is altered during the program to ensure the order of cards in the deck is maintained appropriately. 
 */

public class Card implements CardInterface{
    
    private final int number;
    private final char suit;
    private boolean inHand;
    int indexInShuffledDeck;
    
    public Card(int number, char suit){
        this.number = number;
        this.suit = suit;
        inHand = false;
        indexInShuffledDeck = 0;
    }
    
    //getters, no setters are needed since the deck will never change
    @Override
    public int getNumber(){
        return this.number;
    }
    
    @Override
    public char getSuit(){
        return this.suit;
    }
    
    @Override
    public String getString(){
        String numberString = Integer.toString(this.number);
        return (numberString + this.suit);
    }
    
    @Override
    public String getImage(){
        return(getString() + ".png");
    }
    
    @Override
    public void setInHand(boolean x){
        this.inHand = x;
    }
    
    @Override
    public boolean getInHand(){
        return this.inHand;
    }
    
    @Override
    public int getIndexInShuffledDeck(){
        return this.indexInShuffledDeck;
    }
    
    @Override
    public void setIndexInShuffledDeck(int x){
        this.indexInShuffledDeck = x;
    }
    
}
