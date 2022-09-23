/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2mvctimerstopwatchfxmls21;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author raymondwaidmann
 */

//step 2: controller needs to implement the property change listener interface!!!!
public class Rcw5k2MVCTimerStopwatchFXMLS21ViewController implements Initializable, PropertyChangeListener {
      
    @FXML
    private ImageView dialImageView;
    @FXML
    private ImageView handImageView;
    @FXML
    private Text timeText;
    @FXML
    private Text timerText;
    @FXML
    private Text lapText;
    @FXML
    private Text avgLapText;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private LineChart<?, ?> recordChart;
    @FXML
    private AreaChart<?, ?> averageChart;
    @FXML
    private NumberAxis recordYAxis;
    @FXML
    private CategoryAxis recordXAxis;
    @FXML
    private NumberAxis averageYAxis;
    @FXML
    private CategoryAxis averageXAxis;
    
    Rcw5k2MVCTimerStopwatchFXMLS21AnalogModel model;
    Rcw5k2MVCTimerStopwatchFXMLS21DigitalModel digitalModel;
    
    //properly located in controller! Wergeles office hours...
    private XYChart.Series recordSeries;
    private XYChart.Series averageSeries;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = new Rcw5k2MVCTimerStopwatchFXMLS21AnalogModel();
        model.addPropertyChangeListener(this); //this is ok here and not at the bottom; Wergeles office hours...
        digitalModel = new Rcw5k2MVCTimerStopwatchFXMLS21DigitalModel();
        digitalModel.addPropertyChangeListener(this);
        
        handImageView.setRotate(0);
        
        recordSeries = new XYChart.Series(); 
        averageSeries = new XYChart.Series();
        initializeCharts();
        recordChart.getData().add(recordSeries); 
        averageChart.getData().add(averageSeries);
        
        textDialog();
        model.setupTimer();
        digitalModel.setupTimer();
    }    

    @FXML
    private void startStopButton(ActionEvent event) {
        if(model.isRunning() && digitalModel.isRunning()){ //STOP
            model.stop();
            digitalModel.stop();
            recordResetButton.setText("Reset");
            startStopButton.setText("Start");
            startStopButton.setStyle("-fx-background-color: #00ff00");
        } else { //START
            model.start();
            digitalModel.start();
            recordResetButton.setText("Record");
            startStopButton.setText("Stop");
            startStopButton.setStyle("-fx-background-color: #ff0000");
        }
    }

    @FXML
    private void recordResetButton(ActionEvent event) {
        if(model.isRunning() && digitalModel.isRunning()){ //RECORD
            if(!digitalModel.getTimesUp()){ 
                digitalModel.record();
            } else {
                showError("Time is up... No more records...");
            }
            
        } else { //RESET
            model.reset();
            digitalModel.reset(); //order is somewhat important here; if both resets are called, digitalModel must be called first
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Analog")){
            handImageView.setRotate(Double.parseDouble(evt.getNewValue().toString()));
        } else if(evt.getPropertyName().equals("Digital")){
            timeText.setText(evt.getNewValue().toString());
        } else if(evt.getPropertyName().equals("Timer")){
            timerText.setText(evt.getNewValue().toString());
        } else if(evt.getPropertyName().equals("Lap")){
            lapText.setText(evt.getNewValue().toString());
        } else if(evt.getPropertyName().equals("AvgLap")){
            avgLapText.setText(evt.getNewValue().toString());
        } else if(evt.getPropertyName().equals("ResetButtons")){
            startStopButton.setText("Start");
            startStopButton.setStyle("-fx-background-color: #00ff00");
            recordResetButton.setText("Record"); 
        } else if(evt.getPropertyName().equals("ResetImage")){
            handImageView.setRotate(0);
        } else if(evt.getPropertyName().equals("RecordChartYAxis")){
            recordYAxis.setUpperBound(Double.parseDouble(evt.getNewValue().toString()));
        } else if(evt.getPropertyName().equals("AverageChartYAxis")){
            averageYAxis.setUpperBound(Double.parseDouble(evt.getNewValue().toString()));
        } else if(evt.getPropertyName().equals("RecordChart")){
            recordSeries.getData().add(new XYChart.Data(evt.getOldValue().toString(), evt.getNewValue())); 
        } else if(evt.getPropertyName().equals("AverageChart")){
            averageSeries.getData().add(new XYChart.Data(evt.getOldValue().toString(), evt.getNewValue())); 
        } else if(evt.getPropertyName().equals("ResetCharts")){
            recordSeries.getData().clear();
            recordChart.getData().clear(); //https://stackoverflow.com/questions/12093556/javafx-2-x-how-to-remove-xy-line-chart-once-plotted/13053403
            recordChart.getData().add(recordSeries); 
            averageSeries.getData().clear();
            averageChart.getData().clear(); //https://stackoverflow.com/questions/12093556/javafx-2-x-how-to-remove-xy-line-chart-once-plotted/13053403
            averageChart.getData().add(averageSeries); 
            initializeCharts();
        } else if(evt.getPropertyName().equals("TextDialog")){
            textDialog();
        }
        
    }
    
    //Properly located in Controller! Wergeles Office Hours
    public void showError(String message){ 
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void initializeCharts(){
        recordChart.setAnimated(false);
        recordYAxis.setAutoRanging(false); 
        recordYAxis.setLowerBound(0);
        recordYAxis.setUpperBound(digitalModel.getYRecord());
        recordChart.setLegendVisible(false);
        
        averageChart.setAnimated(false);
        averageYAxis.setAutoRanging(false);
        averageYAxis.setLowerBound(0); 
        averageYAxis.setUpperBound(digitalModel.getYAverage());
        averageChart.setLegendVisible(false);
    }
    
    public void textDialog(){
        //Getting User Input for the timer
        TextInputDialog startTimeDialog = new TextInputDialog(); //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextInputDialog.html
        startTimeDialog.setTitle("Timer Start Time Set Up");
        startTimeDialog.setHeaderText("Set up the start time:");
        startTimeDialog.setContentText("Please set up the start time (Integer):");
        boolean legalTime = false;
        int tempTime = 0;
        
        while(!legalTime){ //this loop went over with Xin during the developer night
            Optional<String> input = startTimeDialog.showAndWait(); //showAndWait method returns the input entered bu the user or NULL if nothing is entered
            if(input.isPresent()){
                if((input.get().matches("[0-9]+") && input.get().length() > 0) == false){
                    showError("Insert a Positive Integer!");
                } else{
                    tempTime = Integer.parseInt(input.get());
                    digitalModel.setTimerTime(tempTime);
                    legalTime = true; //need a setter for this as well
                }
            } else { //user cancels or closes out of input dialog
                System.exit(0); //https://www.geeksforgeeks.org/system-exit-in-java/
            }
        }
        
        if(tempTime > 60 || tempTime ==0) { //if input time is greater than 60, time is always up
            digitalModel.setTimesUp(true);
            timerText.setText("Times Up!");
        } else {
            DecimalFormat df = new DecimalFormat("00");
            timerText.setText("Timer: " + df.format(tempTime) + ".00");
        }
    }
    
}  

