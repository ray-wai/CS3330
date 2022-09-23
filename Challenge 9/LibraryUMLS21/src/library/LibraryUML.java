/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import users.GuestUser;
import users.MemberUser;
import users.PremiumUser;
import users.Record;
import users.User;
import users.UserType;

/**
 *
 * @author Junlin Wang, TA at cs3330@missouri.edu
 */
public class LibraryUML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MemberUser user1 = new MemberUser(UserType.MEMBER, "Junlin Wang", "tempPassword");
        System.out.println("Change for " + user1.getUsername() + "'s book purchase: " 
                + user1.bookPurchase(2.10, 3.00));
        
        System.out.println("Change for " + user1.getUsername() +"'s coffee purchase: " 
                + user1.coffeePurchase(3.50, 4.00));
        System.out.println(user1.printUserInfo());
        
        GuestUser user2 = new GuestUser(UserType.GUEST); 
        System.out.println("Change for Guest User " + user2.getUserId() 
                + " coffee purchase: " + user2.coffeePurchase(3.50, 3.50));
        System.out.println(user2.printUserInfo());
        
        MemberUser user3 = new MemberUser(UserType.SUPER_ADMIN, "Professor Wergeles", "tempPassword"); 
        user3.borrowBook("1234");
        user3.borrowBook("2345"); 
        System.out.println(user3.getUsername() + " borrowed the following books: ");
        for(Record record : user3.getRecordList()){
            System.out.println(record.getRecordInfo());
        }
        
        user3.returnBook(user3.getRecordList().get(0).getRecordId());
        
        System.out.println(user3.getUsername() + " borrowed the following books: ");
        
        for(Record record : user3.getRecordList()){
            System.out.println(record.getRecordInfo());
        }
       
        System.out.println("Total Number of Guest Users: " + GuestUser.getNumOfGuestUsers());
        System.out.println("Total Number of Member Users: " + MemberUser.getNumOfMemberUsers()); 
        System.out.println("Total Number of Users: " + User.getNumOfUsers());
        
        
    }
}