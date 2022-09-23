/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2gpacalculators21;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author raymondwaidmann
 */

//$$$$$$$$$$$$$$$$$$$$$$$$$$ASK THE TA IN OFFICE HOURS IF SENDING ERROR MESSAGES TO AN ALERT BOX INSTEAD OF THE TEXT AREA COUNTS FOR BONUS

public class Rcw5k2GPACalculatorS21 extends Application {
    
    public int width = 500;
    public int height = 425;
    public String title = "GPA Calculator";
    private String fontStyle = "Times New Roman";
    public int textFieldWidth = 350;
    public double grade1;
    public double grade2;
    public double grade3;
    public double grade4;
    public double average;
    public String letterGrade;
    public int numOfScores;
    public boolean error;
    public boolean error2;
    
    @Override
    public void start(Stage primaryStage) {

        DecimalFormat df = new DecimalFormat("##.##"); //https://stackoverflow.com/questions/10332546/truncate-a-float-and-a-double-in-java/18172458
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(15, 15, 15, 15));
        
        //label1; //Course 1:
        Label course1 = new Label("Course 1:");
        course1.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(course1, 0, 0, 1, 1); //connecting to the scene graph; https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html#add-javafx.scene.Node-int-int-int-int-

        //textfield1; //score1
        TextField score1 = new TextField(); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextField.html
        score1.setPrefWidth(textFieldWidth); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Region.html#setPrefWidth-double-
        root.add(score1, 1, 0, 1, 1);

        //label2; //Course 2:
        Label course2 = new Label("Course 2:");
        course2.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(course2, 0, 1, 1, 1); 

        //textfield2; //score2
        TextField score2 = new TextField(); 
        score2.setPrefWidth(textFieldWidth); 
        root.add(score2, 1, 1, 1, 1);
        
        //label3; //Course 3:
        Label course3 = new Label("Course 3:");
        course3.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(course3, 0, 2, 1, 1); 

        //textfield3; //score3
        TextField score3 = new TextField(); 
        score3.setPrefWidth(textFieldWidth); 
        root.add(score3, 1, 2, 1, 1);
        
        //label4; //Course 4:
        Label course4 = new Label("Course 4:");
        course4.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(course4, 0, 3, 1, 1); 

        //textfield4; //score4
        TextField score4 = new TextField(); 
        score4.setPrefWidth(textFieldWidth); 
        root.add(score4, 1, 3, 1, 1);
        
        //label5; //information
        Label information = new Label("Information:");
        information.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(information, 0, 4, 2, 1); 

        //textArea; //infoArea
        TextArea infoArea = new TextArea(); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextArea.html
        infoArea.setPrefWidth(textFieldWidth);
        infoArea.setPrefRowCount(3);
        infoArea.setWrapText(true);
        infoArea.setEditable(false);
        root.add(infoArea, 0, 5, 2, 1);

        //Vbox
        VBox vBox1 = new VBox(10); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);
        
        //Calculate Average Score Button
        Button button1 = new Button("Calculate Average Score"); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html#Button--
        button1.setMaxWidth(Double.MAX_VALUE);
        button1.setOnAction((ActionEvent e) -> { //https://www.w3schools.com/java/java_lambda.asp //usage of lambda expression
            error = errorCheckNonCharNonNull(score1.getText(), score2.getText(), score3.getText(), score4.getText());
            error2 = errorCheckOutOfRange(grade1, grade2, grade3, grade4);
            if (error == false){
                infoArea.setText("Error; enter non-character and/or non-empty values for course grades!");
                showError(infoArea.getText());
            }else if (error2 == false){
                infoArea.setText("Error; enter grade values between 0-100!");
                showError(infoArea.getText());
            }else {
                infoArea.setText("Your average score is: ((" + df.format(grade1) + " + " + df.format(grade2) + " + " +
                    df.format(grade3) + " + " + df.format(grade4) + ")/4) = " + df.format(average));
            }
            
        });
        
        //Calculate GPA Button
        Button button2 = new Button("Calculate GPA");
        button2.setMaxWidth(Double.MAX_VALUE);
        button2.setOnAction((ActionEvent e) -> { //https://www.w3schools.com/java/java_lambda.asp //usage of lambda expression
            error = errorCheckNonCharNonNull(score1.getText(), score2.getText(), score3.getText(), score4.getText());
            error2 = errorCheckOutOfRange(grade1, grade2, grade3, grade4);
            if (error == false){
                infoArea.setText("Error; enter non-character and/or non-empty values for course grades!");
                showError(infoArea.getText());
            } else if (error2 == false){
                infoArea.setText("Error; enter grade values between 0-100!");
                showError(infoArea.getText());
            } else {
                if(average >= 87) letterGrade = "A";
                if(average < 87 && average >= 77) letterGrade = "B";
                if(average < 77 && average >= 67) letterGrade = "C";
                if(average < 67 && average >= 60) letterGrade = "D";
                if(average < 60) letterGrade = "F";
                infoArea.setText("Your GPA is: \n" + letterGrade);
            }

        });
        
        //Alert Button
        Button button3 = new Button("Alert");
        button3.setMaxWidth(Double.MAX_VALUE);
        button3.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(AlertType.INFORMATION); //https://code.makery.ch/blog/javafx-dialogs-official/
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            alert.setContentText(infoArea.getText());
            alert.showAndWait();
        });
        
        //Clear All Button
        Button button4 = new Button("Clear All");
        button4.setMaxWidth(Double.MAX_VALUE);
        button4.setOnAction((ActionEvent e) -> {
            grade1 = 0;
            grade2 = 0;
            grade3 = 0;
            grade4 = 0;
            average = 0;
            letterGrade = "";
            score1.setText("");
            score2.setText("");
            score3.setText("");
            score4.setText("");
            infoArea.setText("");
        });

        vBox1.getChildren().addAll(button1, button2, button3, button4); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html#getChildren--
        root.add(vBox1, 0, 6, 2, 1);

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
        
        }
    
    //method that checks to see if the input scores are doubles and non empty
        //returns true if all the scores are non null doubles without any chars 
        //returns false otherwise
    //discussed implementation of these methods with Junlin during office hours
    public boolean errorCheckNonCharNonNull(String score1, String score2, String score3, String score4){
        boolean errorTag = false;
        try{ ////https://www.w3schools.com/java/java_try_catch.asp
                numOfScores = 0;
                grade1 = Double.parseDouble(score1); //https://www.geeksforgeeks.org/double-parsedouble-method-in-java-with-examples/
                numOfScores++;
                grade2 = Double.parseDouble(score2);
                numOfScores++;
                grade3 = Double.parseDouble(score3); //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$REPEATED CODE FROM BUTTON 1
                numOfScores++;
                grade4 = Double.parseDouble(score4);
                numOfScores++;
                
                average = (grade1+grade2+grade3+grade4)/numOfScores;
                
                errorTag = true;
        }
        
        catch(NumberFormatException errorlocal){
            errorTag = false;
        }
        
        return errorTag;
    }
    
    //method that checks to see if the input scores are within the range 0-100
        //returns true if all the scores are acceptable
        //returns false otherwise
    //discussed implementation of these methods with Junlin during office hours
    public boolean errorCheckOutOfRange(double score1, double score2, double score3, double score4){
        boolean errorTag = false;
        if(score1 >= 0 && score1 <= 100){ 
            if(score2 >= 0 && score2 <= 100){
                if(score3 >= 0 && score3 <= 100){
                    if(score4 >= 0 && score4 <= 100){
                        errorTag = true;
                    }
                }
            }
        }
        return errorTag;
    }
    
    //method that displays an alert if an error occurs
    public void showError(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
