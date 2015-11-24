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
import e.view.SlideshowDialog;
import e.view.VideoDialog;
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
        ImageDialog imageDialog = new ImageDialog(ui.getWindow());
        imageDialog.updatePic(ui.getWindow());
        imageDialog.show("Banner Image");
    }

    public void handleLayoutTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleColorTemplateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleAddTextRequest() {
        HeadingDialog head = new HeadingDialog(ui.getWindow());
        head.show("Text");
        String selection = head.getSelection();
        boolean addComponent = selection.equals(head.YES);
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
            Label image = new Label("Text");
            v.getChildren().add(button);
            v.getChildren().add(image);
            component.setContent(v);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
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
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                componentBox.getChildren().remove(component);
            });
        }
//        imageDialog.hide();
//        imageDialog.setPath();
//        imageDialog.updatePic(ui.getWindow());
//        imageDialog.show("Image");
    }

    public void handleAddVideoRequest() {
        VideoDialog imageDialog = new VideoDialog(ui.getWindow());
        imageDialog.show("Video");
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
            Label image = new Label("Video");
            v.getChildren().add(button);
            v.getChildren().add(image);
            component.setContent(v);
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
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
            componentBox.getChildren().add(component);

            button.setOnAction(e -> {
                componentBox.getChildren().remove(component);
            });
        }
    }
}
