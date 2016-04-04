/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import e.model.Component;
import e.view.EPortfolioMakerView;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author jieliang
 */
public class MediaController {
//    EPortfolioMakerView ui;

    EPortfolioMakerView ui;

    String path;

    /**
     * Default contstructor doesn't need to initialize anything
     *
     * @param initUi
     */
    public MediaController(EPortfolioMakerView initUi) {
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
    public File processSelectMedia(String option) {
        FileChooser imageFileChooser = new FileChooser();

        if (null != option) switch (option) {
            case "image":
                // SET THE STARTING DIRECTORY
                imageFileChooser.setInitialDirectory(new File("./images/img/"));
                // LET'S ONLY SEE IMAGE FILES
                FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
                imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
                break;
            case "video":
                // SET THE STARTING DIRECTORY
                imageFileChooser.setInitialDirectory(new File("./video/"));
                // LET'S ONLY SEE IMAGE FILES
                FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
                imageFileChooser.getExtensionFilters().addAll(mp4Filter);
                break;
        }
        // LET'S OPEN THE FILE CHOOSER
        File file = imageFileChooser.showOpenDialog(null);
        return file;
    }

    public void addMedia(File file, Component componentToEdit) {
        String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
        String fileName = file.getName();
        componentToEdit.getComponentContent().add(fileName);
        componentToEdit.setComponentPath(path);
        ui.updateFileToolbarControls(false);
    }
}
