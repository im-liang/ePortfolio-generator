/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.ToolTip.TOOLTIP_REMOVE_PAGE;
import e.controller.EPortfolioEditController;
import e.model.Page;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author jieliang
 */
public class PageEditView extends HBox {
    EPortfolioMakerView ui;
    Page page;
    Button removePageButton;
    Label pageTitleLabel;
    EPortfolioEditController editController;
    
    public PageEditView(EPortfolioMakerView initUI, Page initPage) {
        ui = initUI;
        page = initPage;
        // FIRST SELECT THE CSS STYLE CLASS FOR THIS CONTAINER
	this.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
        
        removePageButton = ui.initChildButton(this, ICON_REMOVE_PAGE, TOOLTIP_REMOVE_PAGE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        pageTitleLabel = new Label(page.getPageTitle());
        
        getChildren().add(removePageButton);
        getChildren().add(pageTitleLabel);
        
        removePageButton.setOnAction(e -> {
            ui.getEPortfolio().setSelectedPage(page);
            ui.getEPortfolio().removeSelectedPage();
            ui.reloadPagePane();
        });
        pageTitleLabel.setOnMouseClicked(e -> {
            ui.getEPortfolio().setSelectedPage(page);
            ui.reloadComponentPane();
        });
    }
}
