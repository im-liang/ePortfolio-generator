/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.LABEL_SAVE_UNSAVED_WORK;
import static e.StartUpConstants.PATH_DATA;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import e.view.EPortfolioViewer;
import e.view.YesNoCancelDialog;
import java.io.File;
import java.io.IOException;
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

    //@todo
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
                ui.reloadPagePane();
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
     * This method will save the current slideshow to a file. Note that we
     * already know the name of the file, so we won't need to prompt the user.
     */
    public boolean handleSaveEPortfolioRequest() {
//        try {
            // GET THE SLIDE SHOW TO SAVE
            EPortfolio ePortfolioToSave = ui.getEPortfolio();

            // SAVE IT TO A FILE
            ePortfolioIO.saveEPortfolio(ePortfolioToSave);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateFileToolbarControls(saved);
            return true;
//        } catch (IOException ioe) {
//            ErrorHandler eH = ui.getErrorHandler();
//            eH.processError();
//            return false;
//        }
    }

    /**
     * This method shows the current slide show in a separate window.
     */
    public void handleViewEPortfolioRequest() {
        try {
            // FIRST EXPORT THE SITE
            EPortfolio ePortfolio = ui.getEPortfolio();
            siteExporter.exportSite(ePortfolio);

            // THEN VIEW THE SITE
            EPortfolioViewer viewer = new EPortfolioViewer(ui);
            viewer.startEPortfolio();
        } catch (Exception e) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError();
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
        YesNoCancelDialog yesNoCancelDialog = new YesNoCancelDialog(ui.getWindow());
        yesNoCancelDialog.show(LABEL_SAVE_UNSAVED_WORK);

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();
        boolean saveWork = selection.equals(YesNoCancelDialog.YES);

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            EPortfolio ePortfolio = ui.getEPortfolio();
            ePortfolioIO.saveEPortfolio(ePortfolio);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
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
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File(PATH_DATA));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                EPortfolio ePortfolioToLoad = ui.getEPortfolio();
                ePortfolioIO.loadEPortfolio(ePortfolioToLoad, selectedFile.getAbsolutePath());
                ui.reloadPageTitlePane();
                ui.reloadPagePane();
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
