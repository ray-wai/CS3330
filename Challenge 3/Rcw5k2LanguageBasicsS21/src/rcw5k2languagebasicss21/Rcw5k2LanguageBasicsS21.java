/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2languagebasicss21;

//import java.time.LocalDateTime; //https://stackoverflow.com/questions/8150155/java-gethours-getminutes-and-getseconds

import java.util.ArrayList;

//import java.time.format.DateTimeFormatter; //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
//import java.util.Random; //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2LanguageBasicsS21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { //since main is static, you cannot call methods outside of main if the variables passed are dynamic
        // TODO code application logic here
        ArrayList<String> list = new ArrayList<>();
list.add("New York"); 

ArrayList<String> list1 = list;

list.add("Atlanta"); 
list1.add("Dallas"); 

System.out.println(list1);

         
        //variable declarations
        Data info = new Data();
        
        //variables set with no-arg constructor
        info.charCompare();
        info.qualityCheck();
        info.gasFeeCalculator();
        
        //using setters
        info.setSunny(false);
        info.setWarm(false);
        info.weatherAnalysis();
        
        //using setters and printing in the same function
        info.getAndPrintHour();
        
        //using getters and setters
        info.setGrade();
        System.out.printf("Grade = %.4f\n", info.getGrade());
        info.analyzeGrade();
        
        info.setCount(2);
        for(info.getCount(); info.getCount() <= 10; info.incrementCount()){
            if(info.getCount() % 3 == 0) System.out.println("Count: " + info.getCount());
        }
        
        info.setCountDown(10);
        while (info.getCountDown() > 0){
            System.out.println("Count Down: " + info.getCountDown());
            info.decrementCountDown();
        }
        System.out.println("Houston, we have a lift off!");
        
        info.setPawPrint("rcw5k2");
        info.setGreeting("Hi");
        info.invokeMe(info.getPawPrint(), info.getGreeting());
    }
    
    

}

