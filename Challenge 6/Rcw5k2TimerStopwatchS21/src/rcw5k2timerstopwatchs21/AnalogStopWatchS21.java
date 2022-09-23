/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2timerstopwatchs21;

import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author raymondwaidmann
 */
public class AnalogStopWatchS21 {
    
    //global variables
    private double secondsElapsed = 0.0;
    private double tickTimeInSeconds = 0.01; //how to change the resolution of the watch (make it smooth)
    private final double angleDeltaPerSeconds = 6; //degrees per second
    private int ticks = 0;
    
    private Timeline timeline; //making these variables global so that we can access them in other classes if necessary
    private ImageView dialImageView;
    private ImageView handImageView;
    
    private final String fontStyle = "Times New Roman";
    private final int fontSizeLarge = 28;
    private final int fontSizeSmall = 20;
    
    private int minute;
    private int second;
    private int centisecond;
    
    private int timerSecond;
    private int timerCentisecond;   
    
    private Text record1;
    private Text record2;
    private Text record3;
    
    private Text totalTime1;
    private Text totalTime2;
    private Text totalTime3;
      
    private int records;
    private int recordTicks;
    private int recordsMinute;
    private int recordsSecond;
    private int recordsCentisecond;
    
    private Text timeText;
    private Text timerText;
    
    private boolean timesUp = false;
    
    private int timerTime = 0;
    private boolean timerTimeError = false;
    
    private GridPane rootContainer;
    private final double width = 425;
    private final double height = 800;
    
    public AnalogStopWatchS21(){
        setupUI();
        textDialog();
    }
    
    //initializing the UI and providing button controls
    public void setupUI(){
        //***** BEGIN UI *****
        //root
            rootContainer = new GridPane();
            rootContainer.setAlignment(Pos.BASELINE_CENTER);
            rootContainer.setHgap(10);
            rootContainer.setVgap(10);
            rootContainer.setPadding(new Insets(15, 15, 15, 15));

        //Analog stopwatch
            dialImageView = new ImageView(); //putting images in the stackpane (on top of each other)
            handImageView = new ImageView();
            StackPane imageStack = new StackPane(); //without using a stackpane, the images do not line up correctly
            imageStack.getChildren().add(dialImageView);
            imageStack.getChildren().add(handImageView);
            rootContainer.add(imageStack, 0, 0);
            Image dialImage = new Image(getClass().getResourceAsStream("clockface.png"));
            Image handImage = new Image(getClass().getResourceAsStream("hand.png"));
            dialImageView.setImage(dialImage);
            handImageView.setImage(handImage);

        //creating buttons
            HBox controlsButtons = new HBox();
            Button rightButton = new Button("Start");
            Button leftButton = new Button("Record");
            rightButton.setMaxWidth(Double.MAX_VALUE);
            leftButton.setMaxWidth(Double.MAX_VALUE);
            rightButton.setStyle("-fx-background-color: #00ff00");

            controlsButtons.setAlignment(Pos.BOTTOM_CENTER);
            controlsButtons.setSpacing(10);
            controlsButtons.setPadding(new Insets(25, 25, 25, 25));
            controlsButtons.getChildren().addAll(leftButton, rightButton); //add buttons to the box
            rootContainer.add(controlsButtons, 0, 1);

        //Digital Time and Timer
            timeText = new Text();
            timeText.setText("00:00.00");
            timeText.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeLarge));

            timerText = new Text();
            timerText.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeLarge));

            VBox digitalTime = new VBox();
            digitalTime.setAlignment(Pos.TOP_CENTER);
            digitalTime.setSpacing(10);
            digitalTime.setPadding(new Insets(25, 25, 25, 25));
            digitalTime.getChildren().addAll(timeText, timerText);
            rootContainer.add(digitalTime, 0, 2);

        //Record
            Label lapTime = new Label();
            lapTime.setText("Lap Time");
            lapTime.setAlignment(Pos.CENTER);
            lapTime.setFont(Font.font(fontStyle, FontWeight.BOLD, fontSizeSmall));

            record1 = new Text();
            record1.setText("Rec 00 +00:00.00");
            record1.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            record2 = new Text();
            record2.setText("Rec 00 +00:00.00");
            record2.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            record3 = new Text();
            record3.setText("Rec 00 +00:00.00");
            record3.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            VBox recordVBox = new VBox();
            recordVBox.setAlignment(Pos.TOP_CENTER);
            recordVBox.setSpacing(10);
            recordVBox.setPadding(new Insets(25, 25, 25, 25));
            recordVBox.getChildren().addAll(lapTime, record1, record2, record3);     

        //Total time
            Label totalTime = new Label();
            totalTime.setText("Total Time");
            totalTime.setAlignment(Pos.CENTER);
            totalTime.setFont(Font.font(fontStyle, FontWeight.BOLD, fontSizeSmall));

            totalTime1 = new Text();
            totalTime1.setText("00:00.00");
            totalTime1.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            totalTime2 = new Text();
            totalTime2.setText("00:00.00");
            totalTime2.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            totalTime3 = new Text();
            totalTime3.setText("00:00.00");
            totalTime3.setFont(Font.font(fontStyle, FontWeight.NORMAL, fontSizeSmall));

            VBox totalTimeVBox = new VBox();
            totalTimeVBox.setAlignment(Pos.TOP_CENTER);
            totalTimeVBox.setSpacing(10);
            totalTimeVBox.setPadding(new Insets(25, 25, 25, 25));
            totalTimeVBox.getChildren().addAll(totalTime, totalTime1, totalTime2, 
                    totalTime3);

        //inserting the two VBoxes for Lap Time and Total Time into an HBox then inserting the HBox to the Gridpane
            HBox lapAndTotalHBox = new HBox();
            lapAndTotalHBox.setAlignment(Pos.TOP_CENTER);
            lapAndTotalHBox.setPadding(new Insets(25, 25, 25, 25));
            lapAndTotalHBox.getChildren().addAll(recordVBox, totalTimeVBox); //add buttons to the box
            rootContainer.add(lapAndTotalHBox, 0, 3);
        //***** END UI *****
        
        //Button Behavior
        //https://stackoverflow.com/questions/43345443/constantly-check-if-timeline-is-stopped-trigger-action
        //I thought that simply using the case method for STOPPED, PAUSED, and RUNNING was more intuitive than 
            //creating the isRunning method so I decided to do it this way; both work equally as well
            leftButton.setOnAction((ActionEvent event) -> { //Record/Reset Button
                if (null != timeline.getStatus())switch (timeline.getStatus()) {
                    case STOPPED: case PAUSED: //reset
                        leftButton.setText("Record");
                        reset();
                        break;

                    case RUNNING: //record
                        if(timesUp){
                            showError("Time is up... No more records...");
                        }else{
                            records++;
                            recordTicks = 0;
                            if (records % 3 == 1){
                                record1.setText("Rec " + String.format("%02d", records) + " +" + String.format("%02d", recordsMinute) + ":" + 
                                        String.format("%02d", recordsSecond) + "." + String.format("%02d", recordsCentisecond));
                                totalTime1.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + "." + String.format("%02d", centisecond));
                            }else if (records % 3 == 2){
                                record2.setText("Rec " + String.format("%02d", records) + " +" + String.format("%02d", recordsMinute) + ":" + 
                                        String.format("%02d", recordsSecond) + "." + String.format("%02d", recordsCentisecond));
                                totalTime2.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + "." + String.format("%02d", centisecond));
                            }else{
                                record3.setText("Rec " + String.format("%02d", records) + " +" + String.format("%02d", recordsMinute) + ":" + 
                                        String.format("%02d", recordsSecond) + "." + String.format("%02d", recordsCentisecond));
                                totalTime3.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + "." + String.format("%02d", centisecond));
                            }
                            break;
                        }
                        default:
                            break;
                }
            });

            rightButton.setOnAction((ActionEvent event) -> { //Start/Stop Button
                if (null != timeline.getStatus())switch (timeline.getStatus()) {
                    case STOPPED: case PAUSED: //start
                        leftButton.setText("Record");
                        rightButton.setText("Stop");
                        rightButton.setStyle("-fx-background-color: #ff0000");
                        timeline.play();
                        break;

                    case RUNNING: //stop
                        leftButton.setText("Reset");
                        rightButton.setText("Start");
                        rightButton.setStyle("-fx-background-color: #00ff00");
                        timeline.pause();
                        break;

                    default:
                        break;
                }
            });
    }
    
    //prompt user for timer input
    public void textDialog(){
        //Getting User Input for the timer
        TextInputDialog startTimeDialog = new TextInputDialog(); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextInputDialog.html
        startTimeDialog.setTitle("Timer Start Time Set Up");
        startTimeDialog.setHeaderText("Set up the start time:");
        startTimeDialog.setContentText("Please set up the start time (Integer):");
        Optional<String> input = startTimeDialog.showAndWait(); //showAndWait method returns the input entered bu the user or NULL if nothing is entered

        if(input.isPresent() == false){ //if the user cancels the TextInputDialog
            timerTimeError = true;
            return;
        } else { //if user presses "OK" on TextInputDialog
            try{
                timerTime = Integer.parseInt(input.get());
                if (timerTime <= 0){ //negative integer
                    showError("Insert a Positive Integer!");
                    timerTimeError = true;
                    return;
                }
            }
            catch (NumberFormatException errorlocal){ //non integer input
                showError("Insert an Integer!");
                timerTimeError = true;
                return; //ending the program after the error is shown
            }
        }

        if(timerTime > 60) { //if input time is greater than 60, time is always up
            timesUp = true;
            timerText.setText("Time's Up!");
        } else {
            timerText.setText("Timer: " + String.format("%02d", timerTime) + ".00");
        }
    }
    
    //digital display for time and timer
    public void setupTimer(){
        //creating an animation (Keyframe and Timeline; 2 requirements to make)
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 * tickTimeInSeconds), 
                (ActionEvent event) -> { //every 1000 ms a new keyframe is created
            update();
        }); 
        
        timeline = new Timeline(keyFrame);
        
        //set cycle count; call play method (2 requirements to run animation)
        timeline.setCycleCount(Animation.INDEFINITE); //can use Animation.INDEFINITE for infinite cycles
    }
    
    //update method for setupTimer method
    public void update(){
        ticks+=(100*tickTimeInSeconds); //total number of centiseconds, used to calculate centiseconds, seconds, and minute
            
            //rotating the hand on the stopwatch
            secondsElapsed += tickTimeInSeconds;
            double rotation = secondsElapsed * angleDeltaPerSeconds; //360 degrees, 60 tick marks, each tick 6 degrees
            handImageView.setRotate(rotation);
            
            //display values for digital output
            centisecond = ticks%100;
            second = (ticks/100)%60;
            minute = ticks/6000;
            
            //display values for records
                //Note that recordTicks gets reset to 0 whenever the record button is pressed
            recordTicks+=(100*tickTimeInSeconds);
            recordsCentisecond = recordTicks%100;
            recordsSecond = (recordTicks/100)%60;
            recordsMinute = recordTicks/6000;
            
            //display values for timer
            timerCentisecond = 100 - centisecond;
            timerSecond = timerTime - second - 1;
            if(timerCentisecond == 100) { //edge conditions require slight modifications
                timerCentisecond = 0;
                if (timerSecond != -1) timerSecond += 1; //if statement required to make sure that timer ends at the correct time
            }
            
            //digital and timer output
            //stackoverflow.com/questions/12421444/how-to-format-a-number-0-9-to-display-with-2-digits-its-not-a-date
            timeText.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + "." + String.format("%02d", centisecond));
            
            //timer reaches 0
            if(!timesUp){
                timerText.setText("Timer: " + String.format("%02d", timerSecond) + "." + String.format("%02d", timerCentisecond));
                if (timerSecond == -1){ 
                    if (timerCentisecond == 0){
                        timerText.setText("Time's Up!");
                        timesUp = true;
                    }
                }
            }
    }
    
    //allows us to call analog.getRootContainer(), analog.getWidth(), and analog.getHeight() in start method
    public Parent getRootContainer(){ //works for anytype of pane (Polymorshism, parent is the superclass)
        return rootContainer;
    }
    
    public Double getWidth(){ //double is primitive, Double is class double
        return width;
    }
    
    public Double getHeight(){ //double is primitive, Double is class double
        return height;
    }
    
    //setting the tick time, we have to initialize the timer (and timeline) only after tickTimeInSeconds has been set to avoid the "Spinny" stopwatch bug
    public void setTickTimeInSeconds(Double tickTimeInSeconds){
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer(); //timer initialization
    }
    
    public boolean getTimerTimeError(){
        return timerTimeError;
    }
    
    //reset method 
    public void reset(){
        timeText.setText("00:00.00");
        timerText.setText("Timer: " + String.format("%02d", timerTime) + ".00");
        records = 0;
        record1.setText("Rec 00 +00:00.00");
        record2.setText("Rec 00 +00:00.00");
        record3.setText("Rec 00 +00:00.00");
        totalTime1.setText("00:00.00");
        totalTime2.setText("00:00.00");
        totalTime3.setText("00:00.00");
        handImageView.setRotate(0);
        secondsElapsed = 0;
        ticks = 0;
        recordTicks = 0;
        if (timerTime > 60){
            timesUp = true;
            timerText.setText("Time's Up!");
        } else {
            timesUp = false;
        }
        
    }
    
    //method that displays an alert if an error occurs
    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    

    
}
