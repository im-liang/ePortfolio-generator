/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.ICON_REMOVE_PAGE;
import e.model.Page;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
        Label currentPageLabel = new Label(page.getPageTitle());

        // SIZE THE WINDOW
        this.setPrefWidth(257);

        getChildren().add(currentPageLabel);

        currentPageLabel.setOnMousePressed(e -> {
            boolean remove = this.getChildren().remove(currentPageLabel);
            TextField currentPageTextField = new TextField(currentPageLabel.getText());
            getChildren().add(currentPageTextField);

            currentPageTextField.setOnAction(eh -> {
                page.setPageTitle(currentPageTextField.getText());
                this.getChildren().remove(currentPageTextField);
                this.getChildren().add(new Label(currentPageTextField.getText()));
                ui.updateFileToolbarControls(false);
            });
        });

        currentPageLabel.textProperty().addListener(e -> {
            page.setPageTitle(currentPageLabel.getText());
            ui.updateFileToolbarControls(false);
        });
        removeButton.setOnAction(eee -> {
            ui.getEPortfolio().removeSelectedPage();
            ui.updateFileToolbarControls(false);
        });
    }
}
