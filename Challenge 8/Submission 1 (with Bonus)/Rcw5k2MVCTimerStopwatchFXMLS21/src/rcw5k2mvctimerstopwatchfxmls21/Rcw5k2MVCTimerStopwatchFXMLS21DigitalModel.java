/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2mvctimerstopwatchfxmls21;

import java.text.DecimalFormat;

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2MVCTimerStopwatchFXMLS21DigitalModel extends Rcw5k2MVCTimerStopwatchFXMLS21AbstractModel {
    
    DecimalFormat doubleDf = new DecimalFormat("00.00"); 
    DecimalFormat df = new DecimalFormat("00");
    
    private int minute;
    private int second;
    private int centisecond;
    
    private int recordCounter;
    private int recordTicks;
    private int recordsSecond;
    private int recordsCentisecond;
    
    private double average;
    
    private int timerTime;
    private int timerSecond;
    private int timerCentisecond;
    private boolean timesUp;
    
    private int yRecord;
    private int yAverage;
    
    private boolean sixtyFlag;
    
    public Rcw5k2MVCTimerStopwatchFXMLS21DigitalModel(){
        recordCounter = 0;
        average = 0.0;
        timerTime = 0;
        timesUp = false;
        
        timerSecond = 0;
        timerCentisecond = 0;
        
        yRecord = 5;
        yAverage = 5;
        
        sixtyFlag = false;
    }
    
    public void record(){
        //average calculation
        average *= recordCounter;
        double doubleLap;
        if (recordsCentisecond >= 10) { //without this if statement, lap time of 2.08 would show as 2.80 since recordCentisecond would simply be 8. 
            doubleLap = Double.parseDouble(recordsSecond + "." + recordsCentisecond);
        } else {
            doubleLap = Double.parseDouble(recordsSecond + ".0" + recordsCentisecond);
        }
        average += doubleLap;
        recordCounter++;
        average /= recordCounter;
        
        String newLap = ("Lap: " + String.format("%02d", recordsSecond) + "." + String.format("%02d", recordsCentisecond));
        firePropertyChange("Lap", null, newLap);
        
        String newAvgLap = ("Avg. Lap: "+ doubleDf.format(average));
        firePropertyChange("AvgLap", null, newAvgLap);
        
        //while loops adjust the yaxes of the charts if necessary
        while(doubleLap >= yRecord){ 
            yRecord += 5;
            firePropertyChange("RecordChartYAxis", null, yRecord);
        }
        
        while(average >= yAverage){
            yAverage += 5;
            firePropertyChange("AverageChartYAxis", null, yAverage);
        }
        
        firePropertyChange("RecordChart", Integer.toString(recordCounter), doubleLap);
        firePropertyChange("AverageChart", Integer.toString(recordCounter), average);
        
        recordTicks = 0;
    }
    
    @Override
    public void update(){
        super.update();
        centisecond = ticks%100;
        second = (ticks/100)%60;
        minute = ticks/6000;
        String newDigital = (df.format(minute) + ":" + df.format(second) + "." + df.format(centisecond));
        firePropertyChange("Digital", null, newDigital);

        //display values for records
            //Note that recordTicks gets reset to 0 whenever the record button is pressed
        recordTicks+=(100*tickTimeInSeconds);
        recordsCentisecond = recordTicks%100;
        recordsSecond = (recordTicks/100)%60;

        //display values for timer
        timerCentisecond = 100 - centisecond;
        timerSecond = timerTime - second - 1;
        if(timerCentisecond == 100) { //edge conditions require slight modifications
            timerCentisecond = 0;
            if (timerSecond != -1) { //if statement required to make sure that timer ends at the correct time
                timerSecond += 1;
            } 
            if(sixtyFlag) timerSecond = -1;
            //if statement executes when 60 second timer has exactly 1.00 seconds left; the next time line 109 occurs is at 0.00, in which line 114 properly
            //sets timer second so that line 127 executes correctly. 
            if(timerTime == 60 && timerSecond == 1){ //is timer is 60, line 108 does not execute quite correctly; this if statement ensures proper execution
                sixtyFlag = true;
            }
            
        }

        String newTimer = ("Timer: " + df.format(timerSecond) + "." + df.format(timerCentisecond));

        if(!timesUp){
            firePropertyChange("Timer", null, newTimer);
            if (timerSecond == -1){ //timer reaches 0
                if (timerCentisecond == 0){
                    firePropertyChange("Timer", null, "Times Up!");
                    timesUp = true;
                }
            }
        }
    }
    
    @Override
    public void reset(){
        firePropertyChange("Digital", null, "00:00.00");
        firePropertyChange("Lap", null, "Lap: 00.00");
        firePropertyChange("AvgLap", null, "Avg. Lap: 00.00");
        firePropertyChange("ResetButtons", null, null);
        
        average = 0;
        yRecord = 5;
        yAverage = 5;
        
        if(ticks!=0){
            firePropertyChange("Timer", null, "Timer: --:--");
            firePropertyChange("ResetCharts", null, null);
            firePropertyChange("TextDialog", null, null); //BONUS, repromting user for new input of timer time 

            if (timerTime > 60 || timerTime == 0){
                timesUp = true;
                firePropertyChange("Timer", null, "Times Up!");
            } else {
                timesUp = false;
                String newTimer = ("Timer: " + df.format(timerTime) + ".00");
                firePropertyChange("Timer", null, newTimer);
            }
        }
        
        sixtyFlag = false;
        recordTicks = 0;
        recordCounter = 0;
        super.reset(); //resetting ticks needs to be done after the if statement ~15 lines up (super.reset() resets ticks = 0)
    }
    
    public boolean getTimesUp(){
        return timesUp;
    }
    
    public int getYRecord(){
        return yRecord;
    }
    
    public int getYAverage(){
        return yAverage;
    }
    
    public void setTimerTime(int timerTime){
        this.timerTime = timerTime;
    }
    
    public void setTimesUp(boolean timesUp){
        this.timesUp = timesUp;
    }
    
}
