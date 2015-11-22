/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import static e.StartUpConstants.ICON_WINDOW_LOGO;
import static e.StartUpConstants.PATH_IMAGES;
import static e.ToolTip.TITLE_WINDOW;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.view.EPortfolioMakerView;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author jieliang
 */
public class EportfolioMaker extends Application {

    // THIS WILL PERFORM SLIDESHOW READING AND WRITING
    EPortfolioFileManager fileManager = new EPortfolioFileManager();

    // THIS WILL EXPORT THE WEB SITES
    EPortfolioSiteExporter siteExporter = new EPortfolioSiteExporter();

    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    EPortfolioMakerView ui = new EPortfolioMakerView(fileManager, siteExporter);

    @Override
    public void start(Stage primaryStage) throws Exception {

        // SET THE WINDOW ICON
        String imagePath = PATH_IMAGES + ICON_WINDOW_LOGO;
        File file = new File(imagePath);

        // GET AND SET THE IMAGE
        URL fileURL = file.toURI().toURL();
        Image windowIcon = new Image(fileURL.toExternalForm());
        primaryStage.getIcons().add(windowIcon);

        // NOW START THE UI IN EVENT HANDLING MODE
        ui.startUI(primaryStage, TITLE_WINDOW);
    }

    /**
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
