/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles
 */
public class AnalogStopWatch {
    
    private double secondsElapsed = 0.0;
    private double tickTimeInSeconds; //how to change the resolution of the watch
    private final double angleDeltaPerSeconds = 6; //degrees per tick
    
    private Timeline timeline; //making these variables local so that we can access them in other classes if necessary
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    //constructor
    public AnalogStopWatch(){
        setupUI();
        setupTimer(); //bonus requires timer set up to be completed after setting a tick time
    }
    
    public void setupUI(){
        rootContainer = new StackPane(); //literally a stack, possibly not the best choice for this application; GridPane is probably better for challenge
        dialImageView = new ImageView(); //putting images in the stackpane (on top of each other)
        handImageView = new ImageView();
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        
        //creating buttons
        HBox controlsButtons = new HBox();
        Button startStopButton = new Button("Stop");
        Button resetRecordButton = new Button("Record");
        startStopButton.setMaxWidth(Double.MAX_VALUE);
        resetRecordButton.setMaxWidth(Double.MAX_VALUE);
        
        controlsButtons.setAlignment(Pos.BOTTOM_CENTER);
        controlsButtons.setSpacing(10);
        controlsButtons.setPadding(new Insets(25, 25, 25, 25));
        controlsButtons.getChildren().addAll(resetRecordButton, startStopButton); //add buttons to the box
        
        rootContainer.getChildren().addAll(controlsButtons, dialImageView, handImageView); //bonus implementationrootContainer.getChildren().addAll(controlsButtons, dialImageView, handImageView); //bonus implementation
        controlsButtons.toFront(); //bonus http://java-buddy.blogspot.com/2013/07/move-node-to-front.html
        
        //buttons controls
        resetRecordButton.setOnAction((ActionEvent event) -> {
            if(!isRunning()){
                timeline.stop();
                handImageView.setRotate(0);
                secondsElapsed = 0;
                resetRecordButton.setText("Record");
                startStopButton.setText("Start");
            }
        });
        
        startStopButton.setOnAction((ActionEvent event) -> {
            if(isRunning()){
                timeline.pause();
                resetRecordButton.setText("Reset");
                startStopButton.setText("Start");

            }
            else{
                start();
                resetRecordButton.setText("Record");
                startStopButton.setText("Stop");
            }

        });
    }
    
    public void setupTimer(){
        keyFrame = new KeyFrame(Duration.millis(1000 * tickTimeInSeconds), (ActionEvent event) -> { //every 1000 ms a new keyframe is created
            update();
        }); 
        
        timeline = new Timeline(keyFrame);
        
        //set cycle count; call play method (2 requirements to run animation)
        timeline.setCycleCount(Animation.INDEFINITE); //can use Animation.INDEFINITE for infinite cycles

    }
    
    private void update(){
            //360 degrees, 60 tick marks, each tick 6 degrees
            secondsElapsed += tickTimeInSeconds;
            double rotation = secondsElapsed * angleDeltaPerSeconds;
            handImageView.setRotate(rotation);
    }
    
    //lecture 10 continued
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        
        return false;
    }
    
    //getters and setters for scene
    public Parent getRootContainer(){ //works for anytype of pane (Polymorshism, parent is the superclass)
        return rootContainer;
    }
    
    public Double getWidth(){ //double is primitive, Double is class double
        if(dialImage != null){
            return dialImage.getWidth();
        }
        else{
            return 0.0;
        }
    }
    
    public Double getHeight(){ //double is primitive, Double is class double
        if(dialImage != null) return dialImage.getHeight();
        else return 0.0;
    }
    
    
    //bonus opportunity; bug
    public void setTickTimeInSeconds(Double tickTimeInSeconds){
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
                if (null != timeline.getStatus())switch (timeline.getStatus()) {
                    case PAUSED: case STOPPED: //stop
                            timeline.play();
                        break;

                    default:
                        break;
        }
    }
    
    public void start(){
        timeline.play();
//        startStopButton.setText("Stop"); //buttons need to be global
    }
    
}
