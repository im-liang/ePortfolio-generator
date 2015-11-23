package e.controller;

import e.model.Page;
import e.view.EPortfolioMakerView;
import e.view.PageEditView;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class VideoController {
//    EPortfolioMakerView ui;
    Stage ui;
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public VideoController(Stage initUi) {   
	ui = initUi;
    }
    
    /**
     * This function provides the response to the user's request to
     * select an image.
     * 
     * @param slideToEdit - Slide for which the user is selecting an image.
     * 
     * @param view The user interface control group where the image
     * will appear after selection.
     */
    public void processSelectImage() {
	FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File("./video/"));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
	    //slideToEdit.setImage(path, fileName);
	    //view.updateSlideImage();
	    //ui.updateFileToolbarControls(false);
	}
    }
}
