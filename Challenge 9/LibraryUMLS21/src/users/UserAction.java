/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */
public interface UserAction {
    double bookPurchase(double bookPrice, double moneyPaid);
    double coffeePurchase(double coffeePrice, double moneyPaid);
    double sandwichPurchase(double foodPrice, double moneyPaid); 
}
