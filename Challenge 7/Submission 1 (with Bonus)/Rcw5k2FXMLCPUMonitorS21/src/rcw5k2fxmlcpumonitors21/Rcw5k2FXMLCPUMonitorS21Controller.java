/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2fxmlcpumonitors21;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author raymondwaidmann
 */
public class Rcw5k2FXMLCPUMonitorS21Controller implements Initializable {
    
    private Timeline timeline;
    private final DecimalFormat df = new DecimalFormat("#.##");
    private double cpu;
    private double cpuMax = 0;
    private double cpuMean = 0;
    private int counter = 0;
    private int recordCounter = 0;
    private long memory = 0;
     
    private XYChart.Series recordSeries;
    private XYChart.Series meanSeries;

    @FXML
    private ImageView guageImage;
    @FXML
    private ImageView handImage;
    @FXML
    private Text percentCPU;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private Button memoryButton;
    @FXML
    private Text peakCPU;
    @FXML
    private Text meanCPU;
    @FXML
    private Text memoryText;
    @FXML
    private NumberAxis recordYAxis;
    @FXML
    private CategoryAxis recordXAxis;
    @FXML
    private NumberAxis meanYAxis;
    @FXML
    private CategoryAxis meanXAxis;
    @FXML
    private LineChart<?, ?> recordChart;
    @FXML
    private AreaChart<?, ?> meanChart;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handImage.setRotate(195);
        
        recordSeries = new XYChart.Series(); 
        meanSeries = new XYChart.Series();
        initializeCharts();
        recordChart.getData().add(recordSeries); 
        meanChart.getData().add(meanSeries);
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), (ActionEvent event) -> { //every 1000 ms a new keyframe is created
            cpu = this.getCPUUsage();
            memory = this.getFreeMemory();
            if(!Double.isNaN(cpu)){ //https://stackoverflow.com/questions/1456566/how-do-you-test-to-see-if-a-double-is-equal-to-nan
                counter++;
                updateHandAndDigital();
                checkCPUMax();
                updateAverage();
                updateAverageChart();
            }
        }); 
        
        timeline = new Timeline(keyFrame);

        timeline.setCycleCount(Animation.INDEFINITE); //set cycle count; call play method (2 requirements to run animation)
    }    

    @FXML
    private void startStopButton(ActionEvent event) {
        if(isRunning()){ //STOP
            stop();
        } else { //START
            start();
        }
        
    }

    @FXML
    private void recordResetButton(ActionEvent event) {
        if(isRunning()){ //RECORD
            record();
        } else { //RESET
            reset();
        }
    }
    
    @FXML
    private void memoryButton(ActionEvent event) {
        if(isRunning()){
            memoryText.setText("Free Physical Memory: " + memory/1000000 + " MB");
        }
    }
    
    public double getCPUUsage() { 
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad") && Modifier.isPublic(method.getModifiers())) { 
                try {
                        value = (double) method.invoke(operatingSystemMXBean); 
                } catch (Exception e) {
                    value = 0; 
                }
                return value;
            }
        }
        return value;
    }
    
    public long getFreeMemory() { //docs.oracle.com/javase/7/docs/jre/api/management/extension/com/sun/management/OperatingSystemMXBean.html
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        long value = 0;
        
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            
            if (method.getName().startsWith("getFreePhysicalMemorySize") && Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (long) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
    
    public void updateHandAndDigital(){
        percentCPU.setText(df.format(cpu*100) + "%");
        handImage.setRotate(195 + (cpu*300));
    }
    
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    private void checkCPUMax(){
        if (cpu > cpuMax) {
            peakCPU.setText("Peak CPU usage: " + df.format(cpu*100) + "%");
            cpuMax = cpu;
        }
    }
    
    private void updateAverage(){
        cpuMean = cpuMean * (counter-1);
        cpuMean += (cpu);
        cpuMean = cpuMean/counter;
        meanCPU.setText("Mean CPU usage: " + df.format(cpuMean*100) + "%");
    }
    
    private void start(){
        timeline.play();
        recordResetButton.setText("Record");
        startStopButton.setText("Stop");
        startStopButton.setStyle("-fx-background-color: #ff0000");
    }
    
    private void stop(){
        timeline.pause();
        recordResetButton.setText("Reset");
        startStopButton.setText("Start");
        startStopButton.setStyle("-fx-background-color: #00ff00");
    }
    
    private void record(){
        recordCounter++;
        recordSeries.getData().add(new XYChart.Data(Integer.toString(recordCounter), cpu*100)); 
        
    }
    
    private void reset(){
        timeline.stop();
        
        cpu = 0;
        cpuMax = 0;
        cpuMean = 0;
        counter = 0;
        recordCounter = 0;
        
        handImage.setRotate(195);
        
        startStopButton.setText("Start");
        startStopButton.setStyle("-fx-background-color: #00ff00");
        recordResetButton.setText("Record");
        
        percentCPU.setText("0.00%");
        peakCPU.setText("Peak CPU usage: 0.00%");
        meanCPU.setText("Mean CPU usage: 0.00%");
        memoryText.setText("Free Physical Memory: 0 MB");
        
        recordSeries.getData().clear();
        recordChart.getData().clear(); //https://stackoverflow.com/questions/12093556/javafx-2-x-how-to-remove-xy-line-chart-once-plotted/13053403
        recordChart.getData().add(recordSeries); 
        
        meanSeries.getData().clear();
        meanChart.getData().clear(); //https://stackoverflow.com/questions/12093556/javafx-2-x-how-to-remove-xy-line-chart-once-plotted/13053403
        meanChart.getData().add(meanSeries); 
        
        initializeCharts();
    }
    
    public void updateAverageChart(){
        meanSeries.getData().add(new XYChart.Data(Integer.toString(counter), cpuMean*100)); 
    }
    
    public void initializeCharts(){
        recordChart.setAnimated(false);
        recordYAxis.setLowerBound(0);
        recordYAxis.setUpperBound(100);
        
        meanChart.setAnimated(false);
        meanYAxis.setAutoRanging(false);
        meanYAxis.setLowerBound(0); 
        meanYAxis.setUpperBound(100);
    }
}

