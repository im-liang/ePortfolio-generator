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
import static e.StartUpConstants.ICON_PAGE_DARK_BLUE;
import static e.StartUpConstants.ICON_PAGE_INDIE_FLOWER;
import static e.StartUpConstants.ICON_PAGE_LEFT;
import static e.StartUpConstants.ICON_PAGE_LIGHT_BLUE;
import static e.StartUpConstants.ICON_PAGE_LOBSTER;
import static e.StartUpConstants.ICON_PAGE_MONTSERRAT;
import static e.StartUpConstants.ICON_PAGE_NUNITO;
import static e.StartUpConstants.ICON_PAGE_POIRET_ONE;
import static e.StartUpConstants.ICON_PAGE_RED;
import static e.StartUpConstants.ICON_PAGE_RIGHT;
import static e.StartUpConstants.ICON_PAGE_TEAL;
import static e.StartUpConstants.ICON_PAGE_TOP_CENTER;
import static e.StartUpConstants.ICON_PAGE_TOP_LEFT;
import static e.StartUpConstants.ICON_PAGE_TOP_RIGHT;
import static e.StartUpConstants.ICON_PAGE_YELLOW;
import static e.ToolTip.TOOLTIP_PAGE_DARK_BLUE;
import static e.ToolTip.TOOLTIP_PAGE_INDIE_FLOWER;
import static e.ToolTip.TOOLTIP_PAGE_LEFT;
import static e.ToolTip.TOOLTIP_PAGE_LIGHT_BLUE;
import static e.ToolTip.TOOLTIP_PAGE_LOBSTER;
import static e.ToolTip.TOOLTIP_PAGE_MONTSERRAT;
import static e.ToolTip.TOOLTIP_PAGE_NUNITO;
import static e.ToolTip.TOOLTIP_PAGE_POIRET_ONE;
import static e.ToolTip.TOOLTIP_PAGE_RED;
import static e.ToolTip.TOOLTIP_PAGE_RIGHT;
import static e.ToolTip.TOOLTIP_PAGE_TEAL;
import static e.ToolTip.TOOLTIP_PAGE_TOP_CENTER;
import static e.ToolTip.TOOLTIP_PAGE_TOP_LEFT;
import static e.ToolTip.TOOLTIP_PAGE_TOP_RIGHT;
import static e.ToolTip.TOOLTIP_PAGE_YELLOW;
import e.model.EPortfolio;
import static e.view.EPortfolioMakerView.initChildButton;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import javafx.scene.control.Button;
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
                
                image.setFitWidth(ui.getPageVBox().getWidth());
                image.setFitHeight(ui.getPageVBox().getWidth());

                ePortfolio.getSelectedPage().setBannerImageName(fileName);
                ePortfolio.getSelectedPage().setBannerImagePath(path);
                ui.getPageVBox().getChildren().clear();
                ui.getPageVBox().getChildren().add(image);
                ui.updateFileToolbarControls(false);
            }
        } catch (MalformedURLException ex) {
            ErrorHandler error = new ErrorHandler(ui);
            error.processError();
        }
    }

    public void handleChooseLayoutTemplateRequest() {
        ui.getPageVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        ui.getPageVBox().getChildren().add(layoutHBox);
        
        Button topLeftButton = initChildButton(layoutHBox, ICON_PAGE_TOP_LEFT, TOOLTIP_PAGE_TOP_LEFT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topCenterButton = initChildButton(layoutHBox, ICON_PAGE_TOP_CENTER, TOOLTIP_PAGE_TOP_CENTER, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button topRightButton = initChildButton(layoutHBox, ICON_PAGE_TOP_RIGHT, TOOLTIP_PAGE_TOP_RIGHT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button leftButton = initChildButton(layoutHBox, ICON_PAGE_LEFT, TOOLTIP_PAGE_LEFT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button rightButton = initChildButton(layoutHBox, ICON_PAGE_RIGHT, TOOLTIP_PAGE_RIGHT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        topLeftButton.setOnAction(e -> {
            DEFAULT_LAYOUT_TEMPLATE = "layout_1.css";
            ui.updateFileToolbarControls(false);
        });
        topCenterButton.setOnAction(e -> {
            DEFAULT_LAYOUT_TEMPLATE = "layout_2.css";
            ui.updateFileToolbarControls(false);
        });
        topRightButton.setOnAction(e -> {
            DEFAULT_LAYOUT_TEMPLATE = "layout_5.css";
            ui.updateFileToolbarControls(false);
        });
        leftButton.setOnAction(e -> {
            DEFAULT_LAYOUT_TEMPLATE = "layout_3.css";
            ui.updateFileToolbarControls(false);
        });
        rightButton.setOnAction(e -> {
            DEFAULT_LAYOUT_TEMPLATE = "layout_4.css";
            ui.updateFileToolbarControls(false);
        });
    }

    public void handleChooseColorTemplateRequest() {
        ui.getPageVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        ui.getPageVBox().getChildren().add(layoutHBox);
        
        Button redButton = initChildButton(layoutHBox, ICON_PAGE_RED, TOOLTIP_PAGE_RED, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button yellowButton = initChildButton(layoutHBox, ICON_PAGE_YELLOW, TOOLTIP_PAGE_YELLOW, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button tealButton = initChildButton(layoutHBox, ICON_PAGE_TEAL, TOOLTIP_PAGE_TEAL, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button lightBlueButton = initChildButton(layoutHBox, ICON_PAGE_LIGHT_BLUE, TOOLTIP_PAGE_LIGHT_BLUE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button darkBlueButton = initChildButton(layoutHBox, ICON_PAGE_DARK_BLUE, TOOLTIP_PAGE_DARK_BLUE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        redButton.setOnAction(e -> {
            DEFAULT_COLOR_TEMPLATE = "color_5.css";
            ui.updateFileToolbarControls(false);
        });
        yellowButton.setOnAction(e -> {
            DEFAULT_COLOR_TEMPLATE = "color_4.css";
            ui.updateFileToolbarControls(false);
        });
        tealButton.setOnAction(e -> {
            DEFAULT_COLOR_TEMPLATE = "color_1.css";
            ui.updateFileToolbarControls(false);
        });
        lightBlueButton.setOnAction(e -> {
            DEFAULT_COLOR_TEMPLATE = "color_3.css";
            ui.updateFileToolbarControls(false);
        });
        darkBlueButton.setOnAction(e -> {
            DEFAULT_COLOR_TEMPLATE = "color_2.css";
            ui.updateFileToolbarControls(false);
        });        
    }

    public void handleChooseFontRequest() {
        ui.getPageVBox().getChildren().clear();
        HBox layoutHBox = new HBox(5);
        ui.getPageVBox().getChildren().add(layoutHBox);
        //@todo
        Button lobsterFontButton = initChildButton(layoutHBox, ICON_PAGE_LOBSTER, TOOLTIP_PAGE_LOBSTER, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button nunitoFontButton = initChildButton(layoutHBox, ICON_PAGE_NUNITO, TOOLTIP_PAGE_NUNITO, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button montserratFontButton = initChildButton(layoutHBox, ICON_PAGE_MONTSERRAT, TOOLTIP_PAGE_MONTSERRAT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button poiretOneFontButton = initChildButton(layoutHBox, ICON_PAGE_POIRET_ONE, TOOLTIP_PAGE_POIRET_ONE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        Button indieFlowerFontButton = initChildButton(layoutHBox, ICON_PAGE_INDIE_FLOWER, TOOLTIP_PAGE_INDIE_FLOWER, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        lobsterFontButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont("'Lobster', cursive;");
            ui.updateFileToolbarControls(false);
        });
        nunitoFontButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont("'Nunito', sans-serif;");
            ui.updateFileToolbarControls(false);
        });
        montserratFontButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont("'Montserrat', sans-serif;");
            ui.updateFileToolbarControls(false);
        });
        poiretOneFontButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont("'Poiret One', cursive;");
            ui.updateFileToolbarControls(false);            
        });
        indieFlowerFontButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont("'Indie Flower', cursive;");
            ui.updateFileToolbarControls(false);            
        });        
    }

}
