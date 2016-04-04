/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.CONFIRMATION_DIALOG_CONTENT;
import static e.StartUpConstants.CONFIRMATION_DIALOG_HEADER;
import static e.StartUpConstants.CONFIRMATION_DIALOG_TITLE;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON;
import static e.StartUpConstants.PATH_PIC;
import e.error.ErrorHandler;
import e.view.EPortfolioMakerView;
import static e.StartUpConstants.DEFAULT_LAYOUT_TEMPLATE;
import static e.StartUpConstants.DEFAULT_COLOR_TEMPLATE;
import static e.StartUpConstants.DEFAULT_PAGETITLE;
import static e.StartUpConstants.ICON_FONT;
import static e.ToolTip.TOOLTIP_FONT;
import e.model.EPortfolio;
import static e.view.EPortfolioMakerView.initChildButton;
import e.view.PageMakerView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author jieliang
 */
public class EditPageController {

    private EPortfolioMakerView ui;
    private EPortfolio ePortfolio;

    public EditPageController(EPortfolio initEPortfolio, EPortfolioMakerView initUI) {
        ePortfolio = initEPortfolio;
        ui = initUI;
    }

    public void handleAddPageRequest() {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle(CONFIRMATION_DIALOG_TITLE);
        alert.setHeaderText(CONFIRMATION_DIALOG_HEADER);
        alert.setContentText(CONFIRMATION_DIALOG_CONTENT);
        Optional<String> result = alert.showAndWait();
        if (result.isPresent()) {
            if ("".equals(result.get())) {
                ePortfolio.addPage(DEFAULT_PAGETITLE);
            } else {
                ePortfolio.addPage(result.get());
            }
        } else {
            ui.reloadEPortfolioPane();
        }
    }

    public void handleChooseBannerImageRequest() {
        try {
            MediaController mediaController = new MediaController(ui);
            File file = mediaController.processSelectMedia(PATH_PIC);

            if (file != null) {
                String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
                String fileName = file.getName();
                ImageView image = new ImageView();
                URL fileURL = file.toURI().toURL();
                Image i = new Image(fileURL.toExternalForm());
                image.setImage(i);
                image.setFitWidth(200);
                image.setFitHeight(200);

                ePortfolio.getSelectedPage().setBannerImageName(fileName);
                ePortfolio.getSelectedPage().setBannerImagePath(path);
                ui.getPagePane.getChildren().add(image);
                ui.updateFileToolbarControls(false);
            }
        } catch (MalformedURLException ex) {
            ErrorHandler error = new ErrorHandler(ui);
            error.processError();
        }
    }

    public void handleChooseLayoutTemplateRequest() {
        pageUI.getPageTitlesVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        pageUI.getPageTitlesVBox().getChildren().add(layoutHBox);
        //@todo
        Button topLeftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topCenterButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topRightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button leftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button rightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
    }

    public void handleChooseColorTemplateRequest() {
        pageUI.getPageTitlesVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        pageUI.getPageTitlesVBox().getChildren().add(layoutHBox);
        //@todo
        Button topLeftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topCenterButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topRightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button leftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button rightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
    }

    public void handleChooseFontRequest() {
        pageUI.getPageTitlesVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        pageUI.getPageTitlesVBox().getChildren().add(layoutHBox);
        //@todo
        Button topLeftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topCenterButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topRightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button leftButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button rightButton = initChildButton(layoutHBox, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
    }

}
