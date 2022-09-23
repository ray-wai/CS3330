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
public class GuestUser extends User {
    
    private static int numOfGuestUsers = 0; 
    
    public GuestUser(UserType userType){
        super(genUserId(), userType); 
        numOfGuestUsers++; 
    }
    
    protected GuestUser(String userId, UserType userType){
        super(userId, userType);
        numOfGuestUsers++;
    }
    
    public static final int getNumOfGuestUsers(){
        return GuestUser.numOfGuestUsers; 
    }   

    @Override
    public String printUserInfo() {
        return String.format("User ID: %s, User Type: " + userType, userId);
    }

    @Override
    public String getUsername() {
        return username; 
    }

    @Override
    public Boolean setUsername(String username) {
        this.username = username; 
        
        return !this.username.isEmpty(); 
    }
    
    MemberUser register(String username, String password){
        MemberUser member = new MemberUser(userId, userType.MEMBER, username, password);
        
        return member;
    }

    @Override
    public double bookPurchase(double bookPrice, double moneyPaid) {
        System.out.println("Book Price: " + bookPrice);
        
        return super.change(bookPrice, moneyPaid); 
    }

    @Override
    public double coffeePurchase(double coffeePrice, double moneyPaid) {
        System.out.println("Food Price " + coffeePrice);
        
        return super.change(coffeePrice, moneyPaid);
    }

    @Override
    public double sandwichPurchase(double foodPrice, double moneyPaid) {
        System.out.println("Food Price " + foodPrice);
        
        return super.change(foodPrice, moneyPaid);
    }
}