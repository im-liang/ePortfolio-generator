/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.LABEL_PAGE_TITLE;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import e.view.HeadingDialog;
import e.view.ImageDialog;
import e.view.VideoDialog;

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
        HeadingDialog headingDialog = new HeadingDialog(ui.getWindow());
        headingDialog.show(LABEL_PAGE_TITLE);

        // AND NOW GET THE USER'S SELECTION
        String selection = headingDialog.getSelection();
        boolean saveWork = selection.equals(headingDialog.YES);
        if (saveWork) {
            EPortfolio ePortfolio = ui.getEPortfolio();
            ePortfolio.addPage(headingDialog.getContent());
            ui.updateFileToolbarControls(false);
            ui.reloadPagePane();
        }

    }

    public void handleRemovePageRequest() {
        EPortfolio ePortfolio = ui.getEPortfolio();
        ePortfolio.removeSelectedPage();
        ui.updatePageEditToolbarControls();
    }

    public void handleBannerImageRequest() {
        ImageController image = new ImageController(ui.getWindow());
    }

    public void handleLayoutTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleColorTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddTextRequest() {
        HeadingDialog head = new HeadingDialog(ui.getWindow());
    }

    public void handleAddImageRequest() {
        ImageDialog imageDialog = new ImageDialog(ui.getWindow());
        imageDialog.updatePic(ui.getWindow());
        imageDialog.show("Image");
        imageDialog.hide();
        imageDialog.setPath();
        imageDialog.updatePic(ui.getWindow());
        imageDialog.show("Image");
    }

    public void handleAddVideoRequest() {
        VideoDialog imageDialog = new VideoDialog(ui.getWindow());
        imageDialog.show("Video");
    }

    public void handleAddSlideshowRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddHyperlinkRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
