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
public interface MemberUserAction {
    UserType borrowBook(String bookId);
    Boolean returnBook(String recordId);
    Boolean resetPassword(String password, String oldPassword);
    Double rentStudyRoom(String roomId, Double price, Double moneyPaid);
}
