/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.Random;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */
public abstract class User implements UserAction {
    String userId;
    String username;
    private static int numOfUsers = 0;
    protected UserType userType;
    
    private User() {
        User.numOfUsers++;
    }
    
    protected User(String userId, UserType userType){
        this(); 
        this.userId = userId;
        this.userType = userType;
    }
    
    public static final int getNumOfUsers() {
        return numOfUsers;
    }
    
    abstract String getUsername(); 
    
    public abstract Boolean setUsername(String username); 
    

    public String getUserId() {
        return userId;
    }

    void setupUserId() {
        userId = genUserId();
    }

    protected UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public abstract String printUserInfo();
    
    protected static String genUserId(){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        
        Random random=new Random();  
        
        StringBuilder sb=new StringBuilder();
        
        for(int i = 0; i < 20; ++i){
          int number = random.nextInt(62);
          sb.append(str.charAt(number));
        }
        
        return sb.toString();
    }
    
    Double change(Double price, Double moneyPaid){
        return moneyPaid - price; 
    }
    
    Double discountChange(Double price, Double moneyPaid, Double discount){
        return moneyPaid - (price * discount); 
    }
}