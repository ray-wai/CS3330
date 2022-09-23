/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2mvctimerstopwatchfxmls21;

import java.text.DecimalFormat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author raymondwaidmann
 */

//step 1: extends abstractmodel !!!
public class Rcw5k2MVCTimerStopwatchFXMLS21AnalogModel extends Rcw5k2MVCTimerStopwatchFXMLS21AbstractModel {

    private double secondsElapsed;
    private final double angleDeltaPerSeconds; //degrees per second
    private double rotation;

    public Rcw5k2MVCTimerStopwatchFXMLS21AnalogModel(){
        secondsElapsed = 0.0;
        angleDeltaPerSeconds = 6;
        rotation = 0;
    }
    
    @Override
    public void update(){
        super.update();
        secondsElapsed += tickTimeInSeconds;
        rotation = secondsElapsed * angleDeltaPerSeconds; //360 degrees, 60 tick marks, each tick 6 degrees
        firePropertyChange("Analog", null, rotation);
    }

    @Override
    public void reset(){
        firePropertyChange("ResetImage", null, null);
        secondsElapsed = 0;
        super.reset();
    }
   
}

    