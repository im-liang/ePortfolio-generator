/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.ICON_REMOVE_PAGE;
import e.model.Page;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author jieliang
 */
public class PageEditView extends HBox {

    EPortfolioMakerView ui;
    Page page;

    public PageEditView(EPortfolioMakerView initUI, Page initPage) {
        ui = initUI;
        page = initPage;
        Button removeButton = ui.initChildButton(this, ICON_REMOVE_PAGE, "Remove the Page", "dialog_button", false);
        TextField currentPageTextField = new TextField(page.getPageTitle());

        getChildren().add(currentPageTextField);

        currentPageTextField.textProperty().addListener(e -> {
            page.setPageTitle(currentPageTextField.getText());
            ui.updateFileToolbarControls(false);
        });
        removeButton.setOnAction(eee -> {
            ui.getEPortfolio().removeSelectedPage();
            ui.updateFileToolbarControls(false);
        });
    }
}
