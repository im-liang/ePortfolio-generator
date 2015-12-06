package e.controller;

import e.model.Component;
import e.model.Page;
import e.view.EPortfolioMakerView;
import e.view.PageEditView;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This controller provides a controller for when the user chooses to select an
 * image for the slide show.
 *
 * @author McKilla Gorilla & _____________
 */
public class VideoController {
//    EPortfolioMakerView ui;

    EPortfolioMakerView ui;

    /**
     * Default contstructor doesn't need to initialize anything
     */
    public VideoController(EPortfolioMakerView initUi) {
        ui = initUi;
    }

    /**
     * This function provides the response to the user's request to select an
     * image.
     *
     * @param slideToEdit - Slide for which the user is selecting an image.
     *
     * @param view The user interface control group where the image will appear
     * after selection.
     */
    public File processSelectVideo() {
        FileChooser imageFileChooser = new FileChooser();

        // SET THE STARTING DIRECTORY
        imageFileChooser.setInitialDirectory(new File("./video/"));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
        imageFileChooser.getExtensionFilters().addAll(jpgFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = imageFileChooser.showOpenDialog(null);
        return file;
    }

    public void addVideo(File file,Component componentToEdit) {
        String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
        String fileName = file.getName();
        componentToEdit.getComponentContent().add(fileName);
        componentToEdit.setComponentPath(path);
        ui.updateFileToolbarControls(false);
    }
}
