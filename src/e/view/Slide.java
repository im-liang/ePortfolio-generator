package e.view;

/**
 * This class represents a single slide in a slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class Slide {
    String imageFileName;
    String imagePath;
    String caption;
     
    /**
     * Constructor, it initializes all slide data.
     * @param initImageFileName File name of the image.
     * 
     * @param initImagePath File path for the image.
     * 
     * @param initCaption Textual caption to appear under the image.
     */
    public Slide(String initImageFileName, String initImagePath, String initCaption) {
	imageFileName = initImageFileName;
	imagePath = initImagePath;
	caption = initCaption;
    }
    
    // ACCESSOR METHODS
    public String getImageFileName() { return imageFileName; }
    public String getImagePath() { return imagePath; }
    public String getCaption() { return caption; }
    
    // MUTATOR METHODS
    public void setImageFileName(String initImageFileName) {
	imageFileName = initImageFileName;
    }
    
    public void setImagePath(String initImagePath) {
	imagePath = initImagePath;
    }
    
    public void setCaption(String initCaption) {
	caption = initCaption;
    }
    
    public void setImage(String initPath, String initFileName) {
	imagePath = initPath;
	imageFileName = initFileName;
    }
}
