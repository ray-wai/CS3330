/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package about;

import rcw5k2rummys21.AbstractModel; //step zero; import the abstract model from the rcw5k2rummyS21 package (copied directly from oracle docs)

/**
 *
 * @author raymondwaidmann
 */
public class AboutModel extends AbstractModel { //step one; extends AbstractModel
    private String imageName;
    private String textBoxString;
    
    public AboutModel(){
        imageName =(System.getProperty("user.dir") + "/src/about/about.png");
        textBoxString = "CLICK IMAGE TO SHOW A SELF PORTRAIT";
    }
    
    public void updateImage(){ //step four; when the value is updated, let the controller know by calling firePropertyChange
        if(imageName.endsWith("about.png")){
            imageName =(System.getProperty("user.dir") + "/src/about/portrait.png");
            textBoxString = "CLICK IMAGE TO SHOW ABOUT INFORMATION";
        } else {
            imageName =(System.getProperty("user.dir") + "/src/about/about.png");
            textBoxString = "CLICK IMAGE TO SHOW A SELF PORTRAIT";
        }
        firePropertyChange("UpdateAboutImageView", textBoxString, imageName);
    }
}
