/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.ArrayList;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */
public class MemberUser extends User implements MemberUserAction {
    
    protected String password;
    private final ArrayList<Record> RECORD_LIST = new ArrayList<>();
    public static final double DISCOUNT = 0.85;
    String emailAddress;
    private static int numOfMemberUsers = 0; 
    
    
    
    public MemberUser(UserType userType, String username, String password){
        super(genUserId(), userType); 
        this.username = username;
        this.password = password;
        MemberUser.numOfMemberUsers++;
    }
    
    MemberUser(String userId, UserType userType, String username, String password){
        super(userId, userType);
        this.username = username;
        this.password = password;
        MemberUser.numOfMemberUsers++;  
    }
    
    public static final int getNumOfMemberUsers(){
        return MemberUser.numOfMemberUsers; 
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

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    protected String getEmailAddress() {
        return emailAddress;
    }

    protected void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<Record> getRecordList() {
        return RECORD_LIST;
    }

    @Override
    public double bookPurchase(double bookPrice, double moneyPaid) {
        System.out.println("Book Price: " + bookPrice * DISCOUNT);
        
        return super.discountChange(bookPrice, moneyPaid, DISCOUNT);
    }

    @Override
    public double coffeePurchase(double coffeePrice, double moneyPaid) {
        System.out.println("Food Price " + coffeePrice * DISCOUNT);
        
        return super.discountChange(coffeePrice, moneyPaid, DISCOUNT);
    }

    @Override
    public double sandwichPurchase(double foodPrice, double moneyPaid) {
        System.out.println("Food Price " + foodPrice * DISCOUNT);
        
        return super.discountChange(foodPrice, moneyPaid, DISCOUNT);
    }

    @Override
    public UserType borrowBook(String bookId) {
        Record record = new Record(userId, bookId);
        RECORD_LIST.add(record);
        
        return super.userType; 
    }

    @Override
    public Boolean returnBook(String recordId) {
        for(Record record : RECORD_LIST){
            if(record.getRecordId().equals(recordId)){
                record.setState(RecordState.RETURNED);
                return true; 
            }   
        }
        return false; 
    }

    @Override
    public Boolean resetPassword(String password, String oldPassword) {
        if(oldPassword.equals(this.password)){
            this.password = password;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Double rentStudyRoom(String roomId, Double price, Double moneyPaid) {
        System.out.println("User " + userId + " has rent room:" + roomId); 
        
        return super.discountChange(price, moneyPaid, DISCOUNT); 
    }
    
    @Override
    public String printUserInfo(){
        return String.format("User ID: %s, Username: %s, Password: %s , User Type: " + userType, userId, username, password);
    }
}