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
public class Record {
    private final String RECORD_ID;
    private String bookId;
    private String userId;
    private RecordState state;
    static int numOfRecords = 0;
    
    private Record(){
        Record.numOfRecords++; 
        this.RECORD_ID = genRecordId();
        this.state = RecordState.BORROWING;
    }
    
    public Record(String userId, String bookId){
        this(); 
        this.userId = userId;
        this.bookId = bookId;  
    }

    public String getRecordId() {
        return this.RECORD_ID;
    }

    String getBookId() {
        return bookId;
    }

    void setBookId(String bookId) {
        this.bookId = bookId;
    }

    protected String getUserId() {
        return userId;
    }

    protected void setUserId(String userId) {
        this.userId = userId;
    }

    public RecordState getState() {
        return state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }
    
    private static String genRecordId(){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        
        Random random=new Random();  
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < 20; ++i){
          int number = random.nextInt(62);
          sb.append(str.charAt(number));
        }
        
        return sb.toString();
    }
    
    public final String getRecordInfo(){
        return String.format("Record Id: %s, User ID: %s, Book Id: %s, Record State: " + state, RECORD_ID, userId, bookId);
    }
}