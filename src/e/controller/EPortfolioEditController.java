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

    public EPortfolioEditController(EPortfolioMakerView initUI) {
        ui = initUI;
    }

    public void handleAddPageRequest(String pageTitle) {
        ui.getEPortfolio().addPage(pageTitle);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleColorTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        });
    }

    public void handleAddHeaderRequest() {
        HeaderDialog headerDialog = new HeaderDialog(ui.getWindow(), ui);
        headerDialog.show("Header");
        String selection = headerDialog.getSelection();
        boolean addComponent = selection.equals(headerDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(headerDialog.getComponent());
        }
    }

    public void handleAddParagraphRequest() {
        ParagraphDialog paragraphDialog = new ParagraphDialog(ui.getWindow(), ui);
        paragraphDialog.show("Paragraph");
        String selection = paragraphDialog.getSelection();
        boolean addComponent = selection.equals(paragraphDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(paragraphDialog.getComponent());
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
        }
    }
}
