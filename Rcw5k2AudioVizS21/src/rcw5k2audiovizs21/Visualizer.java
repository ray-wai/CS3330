/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcw5k2audiovizs21;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Professor Wergeles 
 */
public interface Visualizer {
    public void setup(Integer numBands, AnchorPane vizPane);
    public void destroy();
    public String getVizName();
    public void visualize(double timestamp, double lenght, float[] magnitudes, float[] phases);
    
/*
    setup():
        allows visualizer to prepare objects and visual interface 
        create visual elements used in the visualizer
        (setup can be called multiple times before destroy is called; handle memory appropriately)
            - numBands changes, provides magnitudes and phases in the visualize() method
    
    destroy():
        allows visualizer to clean up objects created in setup() once no longer needed (AudioViz switched from my visualizer to another visualizer)
        any elements added to vizPane are removed and changes are to be undone (reset to default)
        
    getVisName():
        returns a human readable name for my visualizer as a string
    
    visualize():
        repeatedly gives info to the visualizer about the audio or video that is playing in magnitude and phase arrays. 
        Mmake sure you do not access elements outside the bounds of the array
        
*/
}