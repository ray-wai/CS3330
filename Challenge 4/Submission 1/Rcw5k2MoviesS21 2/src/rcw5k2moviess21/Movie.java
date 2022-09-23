/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2moviess21;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author raymondwaidmann
 */
public class Movie {
    private String name;
    private String director;
    private String summary;
    private float rating = 0.0f;
    private double revenue = 0.0;
    private Genre genre;
    private final Calendar cal = new GregorianCalendar(1888,9,14); //via office hours with Xin
    private Date releaseDate = cal.getTime();
    private String runtime;
    private int version;
    public static int numOfMovies = 0; //https://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html
                                       //class fields utilize static and are non specifc to an object, but rather the class as a whole
//    private String revenueString = "";
//    private String value = "";
    
    //no arg constructor
    public Movie(){
        this.name = "";
        this.director = "";
        this.version = 0;
        Movie.numOfMovies++; //this.numOfMovies is replaced with the class reference Movie.numOfMovies since this is specifc to the object
    }
    
    //second constructor
    public Movie(String name, String director, String runtime){
        this();
        this.name = name;
        this.director = director;
        this.runtime = runtime;
    }
    
    //third constructor
    public Movie(String name, String director, String summary, Genre genre, Date releaseDate, String runtime){
        this(name, director, runtime);
        this.version = 1;
        this.summary = summary;
        this.genre = genre;
        this.releaseDate = releaseDate;
        
    }
    
    //fourth constructor
    public Movie(String name, String director, String summary, float rating, double revenue, Genre genre, Date releaseDate, String runtime){
        this(name, director, summary, genre, releaseDate, runtime);
        this.version = 1;
        this.rating = rating;
        this.revenue = revenue;
    }
    
    
    //methods
    public void setName(String name){
        this.name = name;
        incrementVersion();
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setDirector(String director){
        this.director = director;
    }
    
    public String getDirector(){
        return this.director;
    }
    
    public void setRating(float rating){
        this.rating = rating;
        incrementVersion();
    }
    
    public float getRating(){
        return this.rating;
    }
    
    public void setRevenue(double revenue){
        this.revenue = revenue;
        incrementVersion();
    }
    
    public double getRevenue(){
        return this.revenue;
    }
    
    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
        incrementVersion();
    }
    
    public Date getReleaseDate(){
        return this.releaseDate;
    }
    
    public void setGenre(Genre genre){
        this.genre = genre;
    }
    
    public Genre getGenre(){
        return this.genre;
    }
    
    public void setSummary(String summary){
        this.summary = summary;
        incrementVersion();
    }
    
    public String getSummary(){
        return this.summary;
    }
    
    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public String getRuntime(){
        return this.runtime;
    }       
            
    public void incrementVersion(){
        this.version++;
    }
    
    public int getVersion(){
        return this.version;
    }
    
    //playMovie
    //print movies runtime to the standard output
    //Output: The runtime of <name> is <runtime>, <Name> will end at 01-21-2021 7:10 PM
    public void playMovie(){
        
        //Iterating through the runtime string and extracting the two integers, hours = k, mins = n
        //Begin https://stackoverflow.com/questions/9742680/given-a-string-find-the-first-embedded-occurrence-of-an-integer
        //m and j are indecies within the runtime string
        //the indeces traverse until a non int is met (h)
        //parseInt snips the string into the traversed integer and stores the value in k (hours) and n (mins) respectively
        int j = 0;
        while (j < this.runtime.length() && Character.isDigit(this.runtime.charAt(j))) j++;
        int k = Integer.parseInt(this.runtime.substring(0,j));
        j+=1; //skipping over the "h" in runTime
        int m = j;
        while (m < this.runtime.length() && Character.isDigit(this.runtime.charAt(m))) m++;
        int n = Integer.parseInt(this.runtime.substring(j,m));
        //End stack overflow

        //Begin https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mm a");
        LocalDateTime now = LocalDateTime.now();  //current time
        LocalDateTime end = now.plusHours(k).plusMinutes(n); //https://www.baeldung.com/java-add-hours-date
                                                             //use of k and n to modify current time into movie end time
        //end Oracle doc usage
        
        System.out.println("The runtime of " + this.name + " is " + this.runtime);
        System.out.println(this.name + " will end at " + dtf.format(end));
        
        
    }

    void print(Movie movie) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); //java67.com/2015/06/how-to-format-number-in-java.html
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3); //End 
        SimpleDateFormat dtf = new SimpleDateFormat("MMM d, yyyy"); 
        
        System.out.printf("Name: " + movie.getName() + "\nDirector: " + 
                movie.getDirector() + "\nSummary: " + movie.getSummary() + 
                "\nGenre: " + movie.getGenre() + "\nRating: " + movie.getRating() +
                "\nRevenue: $");
        
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$               
//BEGIN BONUS: Formatting the revenue with the first 4 digits
//        double newRevenue = movie.getRevenue();
//        double trillion = 1000000000000d;
//        if (newRevenue >= trillion){
//            this.value = "trillion";
//            newRevenue = newRevenue/trillion;
//        }
//        else if (newRevenue <= trillion && newRevenue >= 1000000000){
//            this.value = "billion";
//            newRevenue = newRevenue/1000000000;
//        }
//        else if (newRevenue <= 1000000000 && newRevenue >= 1000000){
//            this.value = "million";
//            newRevenue = newRevenue/1000000;
//        }
//        else if (newRevenue <= 1000000 && newRevenue >= 1000){
//            this.value = "thousand";
//            newRevenue = newRevenue/1000;
//        }
//        
//        
//        //formatting to only the first 4 digits
//        //at this point we have newRevenue values of only 3 formats:
//            //#.### (if)
//            //##.### (else if)
//            //###.### (else)
//        //this section ensures that only 4 digits are represented in the revenueString
//        //https://www.java67.com/2014/06/how-to-format-float-or-double-number-java-example.html
//        if (newRevenue - 10 < 0){ //#.###
//            this.revenueString = String.format("%.3f", newRevenue);
//        }
//        
//        else if (newRevenue - 100 < 0){ //##.###
//            this.revenueString = String.format("%.2f", newRevenue); //##.##
//        }
//        
//        else{ //newRevenue - 1000 < 0) //###.###
//            this.revenueString = String.format("%.1f", newRevenue); //###.#
//        }
//
//        System.out.printf(this.revenueString + " " + this.value);
//END BONUS
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        System.out.printf(decimalFormat.format(movie.getRevenue())); //comment out this line in bonus
                
        System.out.println("\nRelease Date: " + 
                dtf.format(movie.getReleaseDate()) + "\nRuntime: " + movie.getRuntime() + 
                "\nVersion: " + movie.getVersion());  
        movie.playMovie();
    }
    
}