/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2audiovizs21;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles 
 * 
 * Music: 
 * http://www.bensound.com/royalty-free-music
 * http://www.audiocheck.net/testtones_sinesweep20-20k.php
 * 
 * 
 * References: 
 * http://stackoverflow.com/questions/11994366/how-to-reference-primarystage
 */
public class PlayerController implements Initializable {

    @FXML
    private AnchorPane vizPane;

    @FXML
    private MediaView mediaView;

    @FXML
    private Text filePathText;

    @FXML
    private Text lengthText;

    @FXML
    private Text currentText;

    @FXML
    private Text bandsText;

    @FXML
    private Text visualizerNameText;

    @FXML
    private Text errorText;

    @FXML
    private Menu visualizersMenu;

    @FXML
    private Menu bandsMenu;

    @FXML
    private Slider timeSlider;

    @FXML
    private Button playPause;
    
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Text volumeText;

    private Media media;
    private MediaPlayer mediaPlayer;

    private Integer numOfBands;
    private final Double updateInterval = 0.05;

    private ArrayList<Visualizer> visualizers;
    private Visualizer currentVisualizer;
    private final Integer[] bandsList = {1, 2, 4, 8, 16, 20, 40, 60, 80, 100, 120, 140, 180, 200};

    private int currentStatus = 0;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numOfBands = 40;
        
        bandsText.setText(Integer.toString(numOfBands));

        visualizers = new ArrayList<>();
        visualizers.add(new EllipseVisualizer1());
        visualizers.add(new EllipseVisualizer2());
        visualizers.add(new EllipseVisualizer3());
        
        //Newly added visualizer
        visualizers.add(new Rcw5k2DaBabyAndSheeshS21());
        visualizers.add(new Rcw5k2DaBabyAndSheeshRotateS21());
        
        for (Visualizer visualizer : visualizers) {
            MenuItem menuItem = new MenuItem(visualizer.getVizName());
            menuItem.setUserData(visualizer);
            menuItem.setOnAction((ActionEvent event) -> {
                selectVisualizer(event);
            });
            visualizersMenu.getItems().add(menuItem);
        }
        
        currentVisualizer = visualizers.get(0);
        visualizerNameText.setText(currentVisualizer.getVizName());

        for (Integer bands : bandsList) {
            MenuItem menuItem = new MenuItem(Integer.toString(bands));
            menuItem.setUserData(bands);
            menuItem.setOnAction((ActionEvent event) -> {
                selectBands(event);
            });
            bandsMenu.getItems().add(menuItem);
        }
    }

    private void selectVisualizer(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Visualizer visualizer = (Visualizer) menuItem.getUserData();
        changeVisualizer(visualizer);
    }

    private void selectBands(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        numOfBands = (Integer) menuItem.getUserData();
        if (currentVisualizer != null) {
            currentVisualizer.setup(numOfBands, vizPane);
        }
        if (mediaPlayer != null) {
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
        }
        bandsText.setText(Integer.toString(numOfBands));
    }

    private void changeVisualizer(Visualizer visualizer) {
        if (currentVisualizer != null) {
            currentVisualizer.destroy();
        }
        currentVisualizer = visualizer;
        currentVisualizer.setup(numOfBands, vizPane);
        visualizerNameText.setText(currentVisualizer.getVizName());
    }

    private void openMedia(File file) {
        filePathText.setText("");
        errorText.setText("");

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                handleReady();
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                handleEndOfMedia();
            });
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
            mediaPlayer.setAudioSpectrumInterval(updateInterval);
            mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
                handleVisualize(timestamp, duration, magnitudes, phases);
            });
            mediaPlayer.setAutoPlay(true);
            filePathText.setText(file.getPath());
            currentStatus = 1;
            mediaPlayer.play();
            playPause.setText("Pause");
        } catch (Exception ex) {
            errorText.setText(ex.toString());
        }
    }

    private void handleReady() {
        Duration duration = mediaPlayer.getTotalDuration();
        
        int minute = (int)duration.toMinutes();
        int second = ((int)duration.toSeconds())%60;

        //https://stackoverflow.com/questions/12421444/how-to-format-a-number-0-9-to-display-with-2-digits-its-not-a-date
        lengthText.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + " s");
                
        Duration ct = mediaPlayer.getCurrentTime();
        currentText.setText(ct.toString());
        currentVisualizer.setup(numOfBands, vizPane);
        timeSlider.setMin(0);
        timeSlider.setMax(duration.toMillis());
        
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(1);
        
        int volume = (int)(volumeSlider.getValue()*100);
        volumeText.setText(Integer.toString(volume) + "%");
    }

    private void handleEndOfMedia() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        timeSlider.setValue(0);
        
        volumeSlider.setValue(0);
    }

    private void handleVisualize(double timestamp, double duration, float[] magnitudes, float[] phases) {
        Duration ct = mediaPlayer.getCurrentTime();
        int minute = (int)ct.toMinutes();
        int second = ((int)ct.toSeconds())%60;
        currentText.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + " s");
        
        double ms = ct.toMillis();
        timeSlider.setValue(ms);

        currentVisualizer.visualize(timestamp, duration, magnitudes, phases);
    }

    @FXML
    private void handleOpen(Event event) {
        Stage primaryStage = (Stage) vizPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            openMedia(file);
        }
    }

    @FXML
    private void handlePlayPause(ActionEvent event) {
        if (mediaPlayer != null) {
            if (currentStatus == 0) {
                currentStatus = 1;
                mediaPlayer.play();
                volumeSlider.setValue(mediaPlayer.getVolume());
                playPause.setText("Pause");
            } else {
                currentStatus = 0;
                mediaPlayer.pause();
                playPause.setText("Play");
            }
        }
    }
    
    @FXML
    private void handleSliderMousePressed(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void handleSliderMouseReleased(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(new Duration(timeSlider.getValue()));
//            System.out.println(timeSlider.getValue()); //printing the new time to the user 
            currentVisualizer.setup(numOfBands, vizPane);
            mediaPlayer.play();
        }
    }

    @FXML
    private void handleVolumeSliderMouseReleased(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volumeSlider.getValue());
        }
    }

    @FXML
    private void handleSliderMouseDragged(MouseEvent event) {
        if (mediaPlayer != null) {
            int ct = (int)timeSlider.getValue();
            int minute = (ct/1000)/60;
            int second = (ct/1000)%60;
            currentText.setText(String.format("%02d", minute) + ":" + String.format("%02d", second) + " s");
        }
    }

    @FXML
    private void handleVolumeSliderMouseDragged(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volumeSlider.getValue());
            
            int volume = (int)(volumeSlider.getValue()*100);
            volumeText.setText(Integer.toString(volume) + "%");
        }
    }
}