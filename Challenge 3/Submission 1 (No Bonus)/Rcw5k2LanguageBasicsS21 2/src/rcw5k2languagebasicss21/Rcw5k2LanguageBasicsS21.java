/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2languagebasicss21;

import java.time.LocalDateTime; //https://stackoverflow.com/questions/8150155/java-gethours-getminutes-and-getseconds
import java.time.format.DateTimeFormatter; //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
import java.util.Random; //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2LanguageBasicsS21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //variable declarations
        char c1 = 'c';
        char c2 = 99;
        short qualityScore = 61;
        float miles = 150f;
        float milesPerGallon = 27.5f; //f "casts" to float
        float gasPrice = 2.34f;
        boolean sunny = false;
        boolean warm = false;
        int hour = LocalDateTime.now().getHour(); //https://stackoverflow.com/questions/8150155/java-gethours-getminutes-and-getseconds
        Random r = new Random(); //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
        double grade = 4.0 * r.nextDouble();
        String greeting = "Hi";
        String myPawPrint = "rcw5k2";
        
        //Compare c1 and c2 to see if they are the same
        if(Character.compare(c1, c2) == 0) System.out.printf("%c and %c are the same.\n", c1, c2); //https://www.javatpoint.com/post/java-character-compare-method
        else System.out.printf("%c and %c are NOT the same.\n", c1, c2);

        //Quality Score Check
        if(qualityScore >= 0 && qualityScore <= 60) System.out.println("The quality is bad.");
        else System.out.println("Good quality.");
        
        //gasFee
        float gasFee = (miles/milesPerGallon)*gasPrice;
        System.out.printf("Total gas fee = %.3f\n", gasFee);
        
        //Weather Analysis
        if (sunny && warm) System.out.println("Go swimming.");
        else if (!sunny && warm) System.out.println("Go hiking.");
        else System.out.println("Stay home and code.");
        
        //hour Analysis
        //https://stackoverflow.com/questions/10873590/using-switch-statement-with-a-range-of-value-in-each-case 
        switch (hour) { 
            case 5: case 6: case 7: case 8: case 9: case 10:
                System.out.println("The current time is " + hour +  " in the MORNING.");
                break;
            case 11: case 12: case 13: case 14: case 15: case 16:
                System.out.println("The current time is " + hour +  " in the AFTERNOON.");
                break;
            case 17: case 18: case 19: case 20: case 21: case 22:
                System.out.println("The current time is " + hour +  " in the EVENING.");
                break;
            case 23: case 0: case 1: case 2: case 3: case 4:
                System.out.println("The current time is " + hour +  " in the NIGHT.");
                break;
            default:
                System.out.println("You have the wrong time.");
                break;
        }
        
        //grade Analysis
        System.out.printf("Grade = %.4f\n", grade);
        System.out.printf("The student's GPA grade is");
        if (grade < 0.70) System.out.printf(" an F");
        if (grade >= 0.70 && grade < 1.70) System.out.printf(" a D- to D+");
        if (grade >= 1.70 && grade < 2.70) System.out.printf(" a C- to C+");
        if (grade >= 2.70 && grade < 3.70) System.out.printf(" a B- to B+");
        if (grade >= 3.70) System.out.printf(" an A- to A+");
        System.out.println(" in the class.");
        
        //for loop counting
        for (int count = 2; count <= 10; count++) {
            if(count % 3 == 0) System.out.println("Count: " + count);
        }
        
        //while loop counting
        int countDown = 10;
        while (countDown > 0){
            System.out.println("Count Down: " + countDown);
            countDown--;
        }
        System.out.println("Houston, we have a lift off!");
        
        invokeMe(greeting, myPawPrint);
    }
    
    public static void invokeMe(String greeting, String myPawPrint)
    {
        //Hi, my pawprint is rcw5k2 and todays date is 12093712039 12038973PM
        //Begin https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");  //ASK ABOUT THIS IN OFFICE HOURS $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        LocalDateTime now = LocalDateTime.now();  
        System.out.println(greeting + ", my pawprint is " + myPawPrint + " and today's date is " + dtf.format(now));
    }

}
