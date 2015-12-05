/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.DEFAULT_PAGETITLE;
import e.error.ErrorHandler;
import e.model.Component;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import e.view.HeaderDialog;
import e.view.ImageDialog;
import e.view.ListDialog;
import e.view.ParagraphDialog;
import e.view.SlideshowDialog;
import e.view.VideoDialog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class EPortfolioEditController {

    private EPortfolioMakerView ui;
    public static String LAYOUTTEMPLATE = "layout_1.css";
    public static String COLORTEMPLATE = "color_1.css";

    public EPortfolioEditController(EPortfolioMakerView initUI) {
        ui = initUI;
    }

    public void handleAddPageRequest(String pageTitle,String pageFont,String banner,String bannerFileName,String bannerFilePath, String components) {
        ui.getEPortfolio().addPage(pageTitle,pageFont,banner,bannerFileName,bannerFilePath,components);
    }

    public void handleRemovePageRequest() {
        EPortfolio ePortfolio = ui.getEPortfolio();
        ePortfolio.removeSelectedPage();
    }

    public void handleBannerImageRequest() {
        try {
            ImageController imageController = new ImageController(ui.getWindow());
            imageController.processSelectImage();
            ImageView image = new ImageView();
            File file = new File(imageController.getPath());
            URL fileURL = file.toURI().toURL();
            Image i = new Image(fileURL.toExternalForm());
            image.setImage(i);
            image.setFitWidth(200);
            image.setFitHeight(200);
            ui.getPagePane().getChildren().add(image);
            ui.getEPortfolio().getSelectedPage().setBannerImage(i);
        } catch (MalformedURLException ex) {
            ErrorHandler error = new ErrorHandler(ui);
            error.processError();
        }
    }

    public void handleLayoutTemplateRequest() {
        // DIFFERENT CSS
        ComboBox layoutTemplateComboBox = new ComboBox();
        layoutTemplateComboBox.getItems().addAll(
                "Top_left",
                "Top_center",
                "Left",
                "Dark Blue",
                "Yellow"
        );
        layoutTemplateComboBox.setValue("Top_left");
        String chosedLayout = layoutTemplateComboBox.getValue().toString();
        layoutTemplateComboBox.setOnAction(eh -> {
            switch (chosedLayout) {
                case "Top_left":
                    LAYOUTTEMPLATE = "layout_1.css";
                    break;
                case "Top_center":
                    LAYOUTTEMPLATE = "layout_2.css";
                    break;
                case "Left":
                    LAYOUTTEMPLATE = "layout_3.css";
                    break;
                case "???":
                    LAYOUTTEMPLATE = "layout_4.css";
                    break;
                case "??":
                    LAYOUTTEMPLATE = "layout_5.css";
                    break;
            }
        });
        ui.getPagePane().getChildren().add(layoutTemplateComboBox);
    }

    public void handleColorTemplateRequest() {
        ComboBox colorTemplateComboBox = new ComboBox();
        colorTemplateComboBox.getItems().addAll(
                "Red",
                "Blue",
                "Light Blue",
                "Dark Blue",
                "Yellow"
        );
        colorTemplateComboBox.setValue("Red");
        String chosedColor = colorTemplateComboBox.getValue().toString();
        colorTemplateComboBox.setOnAction(eh -> {
            switch (chosedColor) {
                case "Red":
                    COLORTEMPLATE = "color_1.css";
                    break;
                case "Blue":
                    COLORTEMPLATE = "color_2.css";
                    break;
                case "Light Blue":
                    COLORTEMPLATE = "color_3.css";
                    break;
                case "Dark Blue":
                    COLORTEMPLATE = "color_4.css";
                    break;
                case "Yellow":
                    COLORTEMPLATE = "color_5.css";
                    break;
            }
        });
        ui.getPagePane().getChildren().add(colorTemplateComboBox);
    }

    public void handleAddImageRequest() {
        ImageDialog imageDialog = new ImageDialog(ui.getWindow(), ui);
        imageDialog.show("Image");
        String selection = imageDialog.getSelection();
        boolean addComponent = selection.equals(imageDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(imageDialog.getComponent());
        }

    }

    public void handleAddVideoRequest() {
        VideoDialog videoDialog = new VideoDialog(ui.getWindow(), ui);
        videoDialog.show("Video");
        String selection = videoDialog.getSelection();
        boolean addComponent = selection.equals(videoDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(videoDialog.getComponent());
        }
    }

    public void handleAddSlideshowRequest() {
        SlideshowDialog slideshowDialog = new SlideshowDialog(ui.getWindow(), ui);
        slideshowDialog.show("Slideshow");
        String selection = slideshowDialog.getSelection();
        boolean addComponent = selection.equals(slideshowDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(slideshowDialog.getComponent());
        }
    }

    public void handleAddFontRequest() {
        HBox fontHBox = new HBox();
        ComboBox fontComboBox = new ComboBox();
        fontComboBox.getItems().addAll(
                "'Montserrat', sans-serif;",
                "'Poiret One', cursive;",
                "'Indie Flower', cursive;",
                "'Lobster', cursive;",
                "'Nunito', sans-serif;"
        );
        fontComboBox.setValue("'Montserrat', sans-serif;");
        fontHBox.getChildren().addAll(fontComboBox);
        ui.getPagePane().getChildren().add(fontHBox);
        fontComboBox.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont(fontComboBox.getValue().toString());
            ui.updateFileToolbarControls(false);
        });
    }

    public void handleAddHeaderRequest() {
        HeaderDialog headerDialog = new HeaderDialog(ui.getWindow(), ui);
        headerDialog.show("Header");
        String selection = headerDialog.getSelection();
        boolean addComponent = selection.equals(headerDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(headerDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }

    public void handleAddParagraphRequest() {
        ParagraphDialog paragraphDialog = new ParagraphDialog(ui.getWindow(), ui);
        paragraphDialog.show("Paragraph");
        String selection = paragraphDialog.getSelection();
        boolean addComponent = selection.equals(paragraphDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(paragraphDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }

    public void handleAddListRequest() {
        ListDialog listDialog = new ListDialog(ui.getWindow(), ui);
        listDialog.show("List");
        String selection = listDialog.getSelection();
        boolean addComponent = selection.equals(listDialog.YES);
        if (addComponent) {
            listDialog.updateList();
            ui.getEPortfolio().getSelectedPage().addComponent(listDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }
}
