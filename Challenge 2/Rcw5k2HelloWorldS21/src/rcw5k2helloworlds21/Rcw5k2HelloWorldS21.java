/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2helloworlds21;

/**
 *
 * @author raymondwaidmann
 */

import java.time.format.DateTimeFormatter;  //https://www.javatpoint.com/java-get-current-date
import java.time.LocalDateTime;    //https://www.javatpoint.com/java-get-current-date

public class Rcw5k2HelloWorldS21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World!");
        int myCourseNum = 3330;
        
        invokeMe(myCourseNum);
    }
    
    public static void invokeMe(int CourseNum) {
        System.out.println("My Course Number is " + CourseNum); //https://www.geeksforgeeks.org/system-out-println-in-java/
        
        //Begin https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd yyyy h:mm a");
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("Today's date is " + dtf.format(now));  
        
        DateTimeFormatter dotw = DateTimeFormatter.ofPattern("EEEE"); //https://stackoverflow.com/questions/5121976/is-there-a-date-format-to-display-the-day-of-the-week-in-java
        System.out.println("Today is: " + dotw.format(now));
        //End of Oracle Website Usage
        
    }
}
