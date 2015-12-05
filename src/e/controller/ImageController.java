package e.controller;

import e.model.Component;
import e.model.Page;
import e.view.ComponentEditView;
import e.view.EPortfolioMakerView;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This controller provides a controller for when the user chooses to select an
 * image for the slide show.
 *
 * @author McKilla Gorilla & _____________
 */
public class ImageController {
//    EPortfolioMakerView ui;

    EPortfolioMakerView ui;

    String path;
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageController(EPortfolioMakerView initUi) {
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
    public void processSelectImage(Component componentToEdit) {
        FileChooser imageFileChooser = new FileChooser();

        // SET THE STARTING DIRECTORY
        imageFileChooser.setInitialDirectory(new File("./images/img/"));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = imageFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();
            componentToEdit.getComponentContent().add(fileName);
            componentToEdit.setComponentPath(path);
            ui.updateFileToolbarControls(false);
        }
    }

}
