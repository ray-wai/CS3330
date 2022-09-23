/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2audiovizs21;

import static java.lang.Integer.min;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author raymondwaidmann
 */
public class Rcw5k2DaBabyAndSheeshS21 implements Visualizer {
    
    private final String name;
    
    private Integer numOfBands;
    private AnchorPane vizPane;
    
    private Double width;
    private Double height;
    
    private final Double bandHeightPercentage;
    private final Double minImageViewHeight;
    
    private Double bandWidth;
    private Double bandHeight;
    
    private ImageView[] daBabyImageView;
    private Image daBabyImage;
    private final String daBabyImageName = "Rcw5k2DaBabyS21.png";
    
    private ImageView[] sheeshImageView;
    private Image sheeshImage;
    private final String sheeshImageName = "Rcw5k2SheeshS21.png";
    
    public Rcw5k2DaBabyAndSheeshS21(){
        name = "Rcw5k2 DaBaby and Sheesh S21";
        bandHeightPercentage = 1.3;
        minImageViewHeight = 10.0;
        width = 0.0;
        height = 0.0;
        bandWidth = 0.0;
        bandHeight = 0.0;
    }
    
    @Override
    public String getVizName() {
        return name;
    }
    
    @Override
    public void setup(Integer numBands, AnchorPane vizPane) {
        destroy();
        
        this.numOfBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        Rectangle clip = new Rectangle(width, height);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        vizPane.setClip(clip);
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        daBabyImageView = new ImageView[numBands];
        daBabyImage = new Image(getClass().getResourceAsStream(daBabyImageName));
        
        sheeshImageView = new ImageView[numBands];
        sheeshImage = new Image(getClass().getResourceAsStream(sheeshImageName));
        
        for (int i = 0; i < numBands; i++) {
            ImageView imageView = new ImageView();
            imageView.setX(bandWidth * i);
            imageView.setY(0);
            imageView.setFitWidth(bandWidth);
            imageView.setFitHeight(minImageViewHeight);
            imageView.setImage(daBabyImage);
            vizPane.getChildren().add(imageView);
            daBabyImageView[i] = imageView;
            
            ImageView imageView2 = new ImageView();
            imageView2.setX(bandWidth * i);
            imageView2.setY(height - 50);
            imageView2.setFitWidth(bandWidth);
            imageView2.setFitHeight(minImageViewHeight);
            imageView2.setImage(sheeshImage);
            vizPane.getChildren().add(imageView2);
            sheeshImageView[i] = imageView2;
        }
        
    }
    
    @Override
    public void destroy() {
        if (daBabyImageView != null && sheeshImageView != null) {
            for (ImageView imageView : daBabyImageView) {
                vizPane.getChildren().remove(imageView);
            }
            daBabyImageView = null;
            
            for (ImageView imageView : sheeshImageView) {
                vizPane.getChildren().remove(imageView);
            }
            sheeshImageView = null;
        } 
    }
    
    @Override
    public void visualize(double timestamp, double lenght, float[] magnitudes, float[] phases) {
        if (daBabyImageView == null && sheeshImageView == null) {
            return;
        }
        
        Integer num = min(daBabyImageView.length, magnitudes.length);
        
        for (int i = 0; i < num/2; i++) {
            daBabyImageView[i].setFitHeight(((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight);
            daBabyImageView[i].setFitWidth(((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight);
            
            sheeshImageView[(num-1)-i].setFitHeight(((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight);
            sheeshImageView[(num-1)-i].setFitWidth(((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight);
            sheeshImageView[(num-1)-i].setX((width-(bandWidth * i))-(((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight));
            sheeshImageView[(num-1)-i].setY((height-50-((60.0 + magnitudes[i])/60.0) * bandHeight/2 + minImageViewHeight));
        }
        
        //modification so that more of the bands are utilized. 
        for (int i = num/2; i < num; i++) {
            daBabyImageView[i].setFitHeight(((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight);
            daBabyImageView[i].setFitWidth(((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight);
            
            sheeshImageView[(num-1)-i].setFitHeight(((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight);
            sheeshImageView[(num-1)-i].setFitWidth(((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight);
            sheeshImageView[(num-1)-i].setX((width-(bandWidth * i))-(((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight));
            sheeshImageView[(num-1)-i].setY((height-50-((60.0 + magnitudes[i])/60.0) * bandHeight * 2.5 + minImageViewHeight));
        }
    }
    
}
