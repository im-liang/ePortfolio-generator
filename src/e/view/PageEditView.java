/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TITLES_LABEL;
import e.model.Page;
import java.util.Optional;
import javafx.scene.control.Label;
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
        Label currentPageLabel = new Label(page.getPageTitle());

        // SIZE THE WINDOW
        this.setPrefWidth(285);

        getChildren().add(currentPageLabel);

        currentPageLabel.setOnMousePressed(e -> {
            if (!ui.getEPortfolio().isSelectedPage(page)) {
                ui.getEPortfolio().setSelectedPage(page);
                ui.reloadEPortfolioPane();
            }
            TextInputDialog dialog = new TextInputDialog(page.getPageTitle());
            dialog.setTitle("Title Input Dialog");
            dialog.setHeaderText("Please enter the page title:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> page.setPageTitle(result.get()));
            ui.reloadEPortfolioPane();
        });

        currentPageLabel.textProperty().addListener(e -> {
            page.setPageTitle(currentPageLabel.getText());
            ui.reloadEPortfolioPane();
        });
        currentPageLabel.getStyleClass().add(CSS_CLASS_PAGE_EDIT_TITLES_LABEL);
    }

}
