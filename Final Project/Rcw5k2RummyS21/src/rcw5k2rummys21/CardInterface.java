/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2rummys21;

/**
 *
 * @author raymondwaidmann
 */
public interface CardInterface {
    int getNumber();
    char getSuit();
    String getString();
    String getImage();
    void setInHand(boolean x);
    boolean getInHand();
    int getIndexInShuffledDeck();
    void setIndexInShuffledDeck(int x);
}
