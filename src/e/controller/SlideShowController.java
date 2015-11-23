/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import e.model.EPortfolio;
import e.view.EPortfolioMakerView;

/**
 *
 * @author jieliang
 */
public class SlideShowController {
    private EPortfolioMakerView ui;
    /**
     * This constructor keeps the UI for later.
     */
    public SlideShowController(EPortfolioMakerView initUI) {
	ui = initUI;
    }
    /**
     * Provides a response for when the user wishes to add a new
     * slide to the slide show.
     */
    public void processAddSlideRequest() {
	EPortfolio ePortfolio = ui.getEPortfolio();
	slideShow.addSlide(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, "Caption");
	ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wishes to remove it from the slide show.
     */
    public void processRemoveSlideRequest() {
	EPortfolio ePortfolio = ui.getEPortfolio();
	slideShow.removeSelectedSlide();
	ui.reloadSlideShowPane();
	ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wishes to move it up in the slide show.
     */
    public void processMoveSlideUpRequest() {
	EPortfolio ePortfolio = ui.getEPortfolio();
	slideShow.moveSelectedSlideUp();	
	ui.updateFileToolbarControls(false);	
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wises to move it down in the slide show.
     */
    public void processMoveSlideDownRequest() {
	SlideShowModel slideShow = ui.getSlideShow();
	slideShow.moveSelectedSlideDown();	
	ui.updateFileToolbarControls(false);
    }
}
