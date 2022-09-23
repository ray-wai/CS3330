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
 * references: adapted from https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java
 */


public class Deck {
    
    private static final int DECK_SIZE = 52;
    public ArrayList<Card> shuffledDeck;
    
    //THIS CODE FOR SHUFFLED DECK
    public Deck() {
        ArrayList<Card> notShuffledDeck = new ArrayList<>();
        notShuffledDeck = new ArrayList<>();

        for (int i = 2; i < (DECK_SIZE/4)+2; ++i) {
                Card card = new Card(i, 'C');
                Card card2 = new Card(i, 'D');
                Card card3 = new Card(i, 'H');
                Card card4 = new Card(i, 'S');
                notShuffledDeck.add(card);
                notShuffledDeck.add(card2);
                notShuffledDeck.add(card3);
                notShuffledDeck.add(card4);
        }

        shuffledDeck = new ArrayList<>();

        while (notShuffledDeck.size() > 0) {
            int index = (int) (Math.random() * notShuffledDeck.size());
            shuffledDeck.add(notShuffledDeck.remove(index));
        }

        for (int i = 0; i < DECK_SIZE; i++){
            shuffledDeck.get(i).setIndexInShuffledDeck(i);
        }

//        //code to print out the order of deck for debugging
//        for (int i = 0; i < DECK_SIZE; i++){
//            System.out.println(shuffledDeck.get(i).getString());
//        }

    }
    
    
    
    
    
    
    
    
//    //THIS CODE FOR NON-SHUFFLED DECK (DEBUGGING)
//    public Deck() {
//            shuffledDeck = new ArrayList<>();
//
//        for (int i = 2; i < (DECK_SIZE/4)+2; ++i) {
//                Card card = new Card(i, 'C');
//                Card card2 = new Card(i, 'D');
//                Card card3 = new Card(i, 'H');
//                Card card4 = new Card(i, 'S');
//
//                shuffledDeck.add(card);
//                shuffledDeck.add(card2);
//                shuffledDeck.add(card3);
//                shuffledDeck.add(card4);
//        }
//        
//        for (int i = 0; i < DECK_SIZE; i++){
//            shuffledDeck.get(i).setIndexInShuffledDeck(i);
//        }
//
////        //code to print out the order of deck for debugging
////        for (int i = 0; i < DECK_SIZE; i++){
////            System.out.println(shuffledDeck.get(i).getString());
////        }
//         
//    }
    
}
