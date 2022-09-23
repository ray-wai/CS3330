/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2languagebasicss21;

import java.time.LocalDateTime; //https://stackoverflow.com/questions/8150155/java-gethours-getminutes-and-getseconds
import java.time.format.DateTimeFormatter;
import java.util.Random; ////https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range

/**
 *
 * @author raymondwaidmann
 */
public class Data {
    
    //variable declarations
        public char c1; //originally had public static char 1; error in main
        public char c2;
        public short qualityScore;
        public float miles;
        public float milesPerGallon; 
        public float gasPrice;
        public boolean sunny;
        public boolean warm;
        public int hour; 
        public double grade;
        public int count;
        public int countDown;
        public String greeting;
        public String myPawPrint;
        
    //no arg-constructor (default values)
    //used to set default values
        public Data(){
            this.c1 = 'c';
            this.c2 = 99;
            this.qualityScore = 61;
            this.miles = 150f;
            this.milesPerGallon = 27.5f; //f "casts" to float
            this.gasPrice = 2.34f;
        }
        
    //Getters + Setters
        public void setSunny(boolean x){
            this.sunny = x;
        }
   
        public void setWarm(boolean x){
            this.warm = x;
        }

        public void getAndPrintHour() { //setter plus print function all in one
            this.hour = LocalDateTime.now().getHour();
            switch(this.hour) {
                case 5: case 6: case 7: case 8: case 9: case 10:
                    System.out.println("The current time is " + this.hour + " in the MORNING.");
                    break;
                case 11: case 12: case 13: case 14: case 15: case 16:
                    System.out.println("The current time is " + this.hour + " in the AFTERNOON.");
                    break;
                case 17: case 18: case 19: case 20: case 21: case 22:
                    System.out.println("The current time is " + this.hour + " in the EVENING.");
                    break;
                case 23: case 0: case 1: case 2: case 3: case 4:
                    System.out.println("The current time is " + this.hour + " in the NIGHT.");
                    break;
            }
            
        }
        
        public void setGrade(){
            Random r = new Random(); //https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
            this.grade = 4.0 * r.nextDouble();
        }
        
        public double getGrade(){
            return this.grade;
        }
        
        public void setCount(int count){
            this.count = count;
        }
        
        public int getCount(){
            return this.count;
        }
        
        public void incrementCount(){
            this.count += 1;
        }
        
        public void setCountDown(int countDown){
            this.countDown = countDown;
        }
        
        public int getCountDown(){
            return this.countDown;
        }
        
        public void decrementCountDown(){
            this.countDown -= 1;
        }
        
        public void setPawPrint(String myPawPrint){
            this.myPawPrint = myPawPrint;
        }
        
        public void setGreeting(String greeting){
            this.greeting = greeting;
        }
        
        public String getPawPrint(){
            return this.myPawPrint;
        }
        
        public String getGreeting(){
            return this.greeting;
        }
        
    //Methods   
        public void charCompare() {
            if(this.c1 != this.c2) System.out.printf("%c and %c are NOT the same.\n", this.c1, this.c2);        
            else System.out.printf("%c and %c are the same.\n", this.c1, this.c2); //https://www.javatpoint.com/post/java-character-compare-method
        }
        
        public void qualityCheck() {
            if (this.qualityScore >= 0 && this.qualityScore <= 60) System.out.println("The quality is bad.");
            else System.out.println("Good quality.");
        }
        
        public void gasFeeCalculator() {
            float gasFee = (this.miles/this.milesPerGallon)*this.gasPrice;
            System.out.printf("Total gas fee = %.3f\n", gasFee);
        }
        
        public void weatherAnalysis() {
            if (this.sunny && this.warm) System.out.println("Go swimming.");
            else if (!this.sunny && this.warm) System.out.println("Go hiking.");
            else System.out.println("Stay home and code.");
        }
        
        public void analyzeGrade() {
            System.out.printf("The student's GPA grade is");
            if (this.grade < 0.70) System.out.printf(" an F");
            if (this.grade >= 0.70 && this.grade < 1.70) System.out.printf(" a D- to D+");
            if (this.grade >= 1.70 && this.grade < 2.70) System.out.printf(" a C- to C+");
            if (this.grade >= 2.70 && this.grade < 3.70) System.out.printf(" a B- to B+");
            if (this.grade >= 3.70) System.out.printf(" a A- to A+");
            System.out.println(" in the class.");
        }
        
        public void invokeMe(String pawPrint, String greeting){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(greeting + ", my pawprint " + pawPrint + " and today's date is " + dtf.format(now));
        }

}
