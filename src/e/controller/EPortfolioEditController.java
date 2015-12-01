/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.DEFAULT_PAGETITLE;
import static e.StartUpConstants.LABEL_PAGE_TITLE;
import e.error.ErrorHandler;
import e.model.EPortfolio;
import e.view.EPortfolioMakerView;
import e.view.TextDialog;
import e.view.ImageDialog;
import e.view.SlideshowDialog;
import e.view.VideoDialog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
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

    public void handleAddPageRequest() {
        ui.getEPortfolio().addPage(DEFAULT_PAGETITLE);
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

    public void handleAddTextRequest() {
        TextDialog textDialog = new TextDialog(ui.getWindow());
        textDialog.show("Text");
        String selection = textDialog.getSelection();
        boolean addComponent = selection.equals(textDialog.YES);
        if (addComponent) {
            VBox componentBox = ui.getComponentPane();
            ScrollPane component = new ScrollPane();

            HBox textHBox = new HBox();
            Button remove = new Button();
            String iconPath = "file:./images/icons/Remove.png";
            Image buttonImage = new Image(iconPath);
            Button button = new Button();
            button.setGraphic(new ImageView(buttonImage));
            Tooltip buttonTooltip = new Tooltip("Remove the Component");
            button.setTooltip(buttonTooltip);
            Label image = new Label(textDialog.getChoice());
            textHBox.getChildren().add(button);
            textHBox.getChildren().add(image);
            component.setContent(textHBox);

            ui.getEPortfolio().getSelectedPage().addComponent(textDialog.getChoice(), null, iconPath, iconPath, null);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                ui.getEPortfolio().getSelectedPage().removeSelectedComponent();
                componentBox.getChildren().remove(component);
            });
        }
    }

    public void handleAddImageRequest() {
        ImageDialog imageDialog = new ImageDialog(ui.getWindow());
        imageDialog.updatePic(ui.getWindow());
        imageDialog.show("Image");
        String selection = imageDialog.getSelection();
        boolean addComponent = selection.equals(imageDialog.YES);
        if (addComponent) {
            VBox componentBox = ui.getComponentPane();
            ScrollPane component = new ScrollPane();

            HBox v = new HBox();
            Button remove = new Button();
            String iconPath = "file:./images/icons/Remove.png";
            Image buttonImage = new Image(iconPath);
            Button button = new Button();
            button.setGraphic(new ImageView(buttonImage));
            Tooltip buttonTooltip = new Tooltip("Remove the Component");
            button.setTooltip(buttonTooltip);
            Label image = new Label("image");
            v.getChildren().add(button);
            v.getChildren().add(image);
            component.setContent(v);

            ui.getEPortfolio().getSelectedPage().addComponent("image", null, iconPath, iconPath, null);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                ui.getEPortfolio().getSelectedPage().removeSelectedComponent();
                componentBox.getChildren().remove(component);
            });
        }
//        imageDialog.hide();
//        imageDialog.setPath();
//        imageDialog.updatePic(ui.getWindow());
//        imageDialog.show("Image");
    }

    public void handleAddVideoRequest() {
        VideoDialog videoDialog = new VideoDialog(ui.getWindow());
        videoDialog.show("Video");
        String selection = videoDialog.getSelection();
        boolean addComponent = selection.equals(videoDialog.YES);
        if (addComponent) {
            VBox componentBox = ui.getComponentPane();
            ScrollPane component = new ScrollPane();

            HBox v = new HBox();
            Button remove = new Button();
            String iconPath = "file:./images/icons/Remove.png";
            Image buttonImage = new Image(iconPath);
            Button button = new Button();
            button.setGraphic(new ImageView(buttonImage));
            Tooltip buttonTooltip = new Tooltip("Remove the Component");
            button.setTooltip(buttonTooltip);
            Label image = new Label("Video");
            v.getChildren().add(button);
            v.getChildren().add(image);
            component.setContent(v);

            ui.getEPortfolio().getSelectedPage().addComponent("video", null, iconPath, iconPath, null);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                ui.getEPortfolio().getSelectedPage().removeSelectedComponent();
                componentBox.getChildren().remove(component);
            });
        }
    }

    public void handleAddSlideshowRequest() {
        SlideshowDialog slideshowDialog = new SlideshowDialog(ui.getWindow());
        slideshowDialog.show("Slideshow");
        String selection = slideshowDialog.getSelection();
        boolean addComponent = selection.equals(slideshowDialog.YES);
        if (addComponent) {
            VBox componentBox = ui.getComponentPane();
            ScrollPane component = new ScrollPane();

            HBox v = new HBox();
            Button remove = new Button();
            String iconPath = "file:./images/icons/Remove.png";
            Image buttonImage = new Image(iconPath);
            Button button = new Button();
            button.setGraphic(new ImageView(buttonImage));
            Tooltip buttonTooltip = new Tooltip("Remove the Component");
            button.setTooltip(buttonTooltip);
            Label image = new Label("Slideshow");
            v.getChildren().add(button);
            v.getChildren().add(image);
            component.setContent(v);

            ui.getEPortfolio().getSelectedPage().addComponent("slideshow", null, iconPath, iconPath, null);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                ui.getEPortfolio().getSelectedPage().removeSelectedComponent();
                componentBox.getChildren().remove(component);
            });
        }
    }
}
