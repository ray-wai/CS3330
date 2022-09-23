/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rcw5k2mvctimerstopwatchfxmls21;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author raymondwaidmann
 * 
 * @references:
 *      entire abstract was taken from //https://www.oracle.com/technical-resources/articles/javase/mvc.html
 */

//step 0: copy from article !!!
public abstract class Rcw5k2MVCTimerStopwatchFXMLS21AbstractModel { //literally copy pasted from the oracle docs sample 2 

    protected PropertyChangeSupport propertyChangeSupport;
    
    protected final double tickTimeInSeconds; //how to change the resolution of the watch (make it smooth)
    protected Timeline timeline;
    protected int ticks;

    public Rcw5k2MVCTimerStopwatchFXMLS21AbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this); //instantiate property change support (constructor)
        tickTimeInSeconds = 0.01;
        ticks = 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    //this is how you sync the controller to the model
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
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
    
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void stop(){
        timeline.pause();
    }
    
    public void update(){
        ticks+=(100*tickTimeInSeconds); //total number of centiseconds, used to calculate centiseconds, seconds, and minute
    }
    
    public void reset(){
        ticks = 0;
    }
}