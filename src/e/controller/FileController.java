/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.CANCEL;
import static e.StartUpConstants.LABEL_SAVE_UNSAVED_WORK;
import static e.StartUpConstants.PATH_DATA;
import static e.StartUpConstants.YES;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import static e.file.EPortfolioSiteExporter.INDEX_FILE;
import e.model.Dialog;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 *
 * @author jieliang
 */
public class FileController {

    //check if saved or not
    private boolean saved;
    //UI
    private EPortfolioMakerView ui;
    //read and write data
    private EPortfolioFileManager ePortfolioIO;
    //export site
    private EPortfolioSiteExporter siteExporter;

    public FileController(EPortfolioMakerView initUI, EPortfolioFileManager initEPortfolioIO, EPortfolioSiteExporter initSiteExporter) {
        //initial (nothing to save)
        saved = true;
        ui = initUI;
        ePortfolioIO = initEPortfolioIO;
        siteExporter = initSiteExporter;
    }

    public void markAsEdited() {
        saved = false;
        ui.updateFileToolbarControls(saved);
    }

    /**
     * This method starts the process of editing a new slide show. If a pose is
     * already being edited, it will prompt the user to save it first.
     */
    public void handleNewEPortfolioRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                EPortfolio ePortfolio = ui.getEPortfolio();
                ePortfolio.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                ui.updateFileToolbarControls(saved);

                // MAKE SURE THE TITLE CONTROLS ARE ENABLED
                ui.reloadEPortfolioPane();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError();
        }
    }

    /**
     * This method lets the user open a slideshow saved to a file. It will also
     * make sure data for the current slideshow is not lost.
     */
    public void handleLoadEPortfolioRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError();
        }
    }

    /**
     * This method will save the current ePortfolio to a file.
     */
    public boolean handleSaveEPortfolioRequest(String studentName) {
        try {
            // GET THE SLIDE SHOW TO SAVE
            EPortfolio ePortfolioToSave = ui.getEPortfolio();

            // SAVE IT TO A FILE
            ePortfolioIO.saveEPortfolio(ePortfolioToSave, studentName);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateFileToolbarControls(saved);
            return true;
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError();
            return false;
        }
    }

    /**
     * This method will save the current ePortfolio to a file.
     */
    public void handleSaveAsEPortfolioRequest() {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Conformation Dialog");
        alert.setHeaderText("Do you want to save the ePortfolio?");
        Optional<String> result = alert.showAndWait();
        if (!result.get().isEmpty()) {
            result.ifPresent(fileName -> this.handleSaveEPortfolioRequest(fileName));

        } else {
            result.ifPresent(fileName -> this.handleSaveEPortfolioRequest("UNTITLED"));
        }
    }

    /**
     * This method shows the current slide show in a separate window.
     */
    public void handleExportEPortfolioRequest() {
        try {
            // FIRST EXPORT THE SITE
            EPortfolio ePortfolio = ui.getEPortfolio();
            if (!saved) {
                Alert saveAlert = new Alert(AlertType.WARNING);
                saveAlert.setTitle("Warning Dialog");
                saveAlert.setHeaderText("You have to save it First!");
                saveAlert.showAndWait();
            } else {
                siteExporter.exportSite(ePortfolio);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     */
    public void handleExitRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave();
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError();
        }
    }

    public void handleVieWPageRequest(BorderPane ePane) {
        try {
            BorderPane dumb = new BorderPane();
            // SETUP THE UI
            WebView webView = new WebView();
            ScrollPane scrollPane = new ScrollPane(webView);

            // GET THE URL
            String indexPath = "sites/" + ui.getEPortfolio().getStudentName() + "/" + INDEX_FILE;
            File indexFile = new File(indexPath);
            URL indexURL = indexFile.toURI().toURL();

            // SETUP THE WEB ENGINE AND LOAD THE URL
            webView.getEngine().load(indexURL.toString());
            webView.getEngine().setJavaScriptEnabled(true);
            dumb.setCenter(scrollPane);
            ePane.setCenter(dumb);

            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
        } catch (MalformedURLException ex) {
            Logger.getLogger(EPortfolioMakerView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        Dialog yesNoCancelDialog = new Dialog(ui.getWindow());
        yesNoCancelDialog.getScene().getWindow().sizeToScene();
        yesNoCancelDialog.show(LABEL_SAVE_UNSAVED_WORK);

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();
        boolean saveWork = selection.equals(YES);
        boolean cancel = selection.equals(CANCEL);

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            EPortfolio ePortfolio = ui.getEPortfolio();
            ePortfolioIO.saveEPortfolio(ePortfolio, ePortfolio.getStudentName());
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (cancel) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser ePortfolioFileChooser = new FileChooser();
        ePortfolioFileChooser.setInitialDirectory(new File(PATH_DATA));
        File selectedFile = ePortfolioFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                EPortfolio ePortfolioToLoad = ui.getEPortfolio();
                ePortfolioIO.loadEPortfolio(ePortfolioToLoad, selectedFile.getAbsolutePath());
                ui.reloadEPortfolioPane();
                saved = true;
                ui.updateFileToolbarControls(saved);
            } catch (Exception e) {
                ErrorHandler eH = ui.getErrorHandler();
                eH.processError();
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the pose is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current pose has been saved
     * since it was last editing. If the current file matches the pose data,
     * we'll return true, otherwise false.
     *
     * @return true if the current pose is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }
}
