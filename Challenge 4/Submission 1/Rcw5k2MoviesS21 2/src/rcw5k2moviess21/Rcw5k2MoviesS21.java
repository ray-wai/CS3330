/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2moviess21;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2MoviesS21 {

    
    static Calendar cal = Calendar.getInstance(); //global variable (Office hours w/ Xin)
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); //java67.com/2015/06/how-to-format-number-in-java.html
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3); //End 
        SimpleDateFormat dtf = new SimpleDateFormat("MMM d, yyyy");
        
        //Movie 1
        Movie movie1 = new Movie("Soul", "Pete Docter and Kemp Powers", "1h40m"); //1h40m
        movie1.setGenre(Genre.ANIMATION);
        movie1.setSummary("After landing the gig of a lifetime, a New York jazz pianist"
                + " suddenly finds himself trapped in a strange land between Earth and"
                + " the afterlife.");
        movie1.setRating(8.1f);
        movie1.setRevenue(82700000d); 
        cal.set(2020, 11, 25);
        movie1.setReleaseDate(cal.getTime()); //using getTime returns a Date
        
        //Movie 2
        cal.set(2017, 5, 21);
        Movie movie2 = new Movie("Transformers: The Last Night", "Michael Bay", "A"
                + " deadly threat from Earth's history reappears and a hunt for a"
                + " lost artifact takes place between Autobots and Decepticons, while"
                + " Optimus Prime encounters his creator in space.", 5.2f, 602800000d, 
                Genre.ACTION, cal.getTime(), "2h34m");
        
        //Movie 3
        cal.set(1994, 6, 6);
        Movie movie3 = new Movie("Forrest Gump", "Robert Zemeckis", "The presidencies" 
                + " of Kennedy and Johnson, the events of Vietnam, Watergate and other"
                + " historical events unfold through the perspective of an Alabama man"
                + " with an IQ of 75, whose only desire is to be reunited with his"
                + " childhood sweetheart.", Genre.DRAMA, cal.getTime(), "2h22m");
        movie3.setRating(8.8f);
        movie3.setRevenue(679800000d);
        
        //Movie 4
        Movie movie4 = new Movie();
        movie4.setName("The Godfather");
        movie4.setDirector("Francis Ford Coppola");
        movie4.setSummary("An organized crime dynasty's aging patriarch transfers"
                + " control of his clandestine empire to his reluctant son. ");
        movie4.setGenre(Genre.DRAMA);
        movie4.setRating(9.2f);
        movie4.setRevenue(287258196d);
        cal.set(1972, 2, 24);
        movie4.setReleaseDate(cal.getTime());
        movie4.setRuntime("2h55m");

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//BEGIN COMMENTED OUT CODE PART OF REGULAR ASSIGNMENT
//Printing movies 1 and 2 with created methods
        System.out.println("MOVIE 1:\nName: " + movie1.getName() + "\nDirector: " + 
                movie1.getDirector() + "\nSummary: " + movie1.getSummary() + 
                "\nGenre: " + movie1.getGenre() + "\nRating: " + movie1.getRating() +
                "\nRevenue: $" + decimalFormat.format(movie1.getRevenue()) + "\nRelease Date: " + 
                dtf.format(movie1.getReleaseDate()) + "\nRuntime: " + movie1.getRuntime() + 
                "\nVersion: " + movie1.getVersion());   
        movie1.playMovie();
        
        System.out.println("\nMOVIE 2:\nName: " + movie2.getName() + "\nDirector: " + 
                movie2.getDirector() + "\nSummary: " + movie2.getSummary() + 
                "\nGenre: " + movie2.getGenre() + "\nRating: " + movie2.getRating() +
                "\nRevenue: $" + decimalFormat.format(movie2.getRevenue()) + "\nRelease Date: " + 
                dtf.format(movie2.getReleaseDate()) + "\nRuntime: " + movie2.getRuntime() + 
                "\nVersion: " + movie2.getVersion());   
        movie2.playMovie();
//END COMMENTED OUT CODE PART OF REGULAR ASSIGNMENT
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//BEGIN BONUS
//print methods are used for movies 1 and 2 in the bonus because it was
//more efficient to implement the revenue bonus within the Movie class
//(code only written once within the movie class as opposed to 3 times total)
        
       
//        System.out.println("\nMovie 1:");
//        movie1.print(movie1);
//        
//        System.out.println("\nMovie 2:");
//        movie2.print(movie2);
//END BONUS
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        //Printing movies 3 and 4 using the created print method
        System.out.println("\nMovie 3:");
        movie3.print(movie3);
        
        System.out.println("\nMovie 4:");
        movie4.print(movie4);
        
        System.out.println("\nNumber of Movies: " + Movie.numOfMovies);
    }
    
}
