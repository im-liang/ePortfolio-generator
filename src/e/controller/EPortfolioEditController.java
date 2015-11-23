/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.LABEL_PAGE_TITLE;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;

/**
 *
 * @author jieliang
 */
public class EPortfolioEditController {

    private EPortfolioMakerView ui;

    public EPortfolioEditController(EPortfolioMakerView initUI) {
        ui = initUI;
    }

    public void handleAddPageRequest() {
        EPortfolio ePortfolio = ui.getEPortfolio();
        ePortfolio.addPage(LABEL_PAGE_TITLE);
        ui.updateFileToolbarControls(false);
        ui.reloadPagePane();
    }

    public void handleRemovePageRequest() {
        EPortfolio ePortfolio = ui.getEPortfolio();
        ePortfolio.removeSelectedPage();
        ui.updatePageEditToolbarControls();
    }

    public void handleBannerImageRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleLayoutTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleColorTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddTextRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddImageRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddVideoRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddSlideshowRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddHyperlinkRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
