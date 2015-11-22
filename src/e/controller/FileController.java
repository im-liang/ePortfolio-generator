/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.LABEL_SAVE_UNSAVED_WORK;
import static e.ToolTip.ERROR_UNEXPECTED;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import e.view.YesNoCancelDialog;
import java.io.IOException;

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
		ui.reloadBannerControls();	
		//ui.reloadSlideShowPane();
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
}
