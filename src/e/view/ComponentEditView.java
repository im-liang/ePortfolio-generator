/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.ToolTip.TOOLTIP_REMOVE_TEXT;
import e.controller.ImageController;
import e.error.ErrorHandler;
import e.model.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class ComponentEditView extends HBox {

    EPortfolioMakerView ui;

    Component component;

    ImageView selectionView;

    VBox contentVBox;
    Label contentLabel;
    TextField contentTextField;

    ImageController imageController;
    /*
     * This constructor initializes the full UI for this component, using ininComponent data for initializing values.
     */

    public ComponentEditView(EPortfolioMakerView initUI, Component initComponent) {
        ui = initUI;
        component = initComponent;
        selectionView = new ImageView();
        updateComponentImage();

        Button removeButton = ui.initChildButton(this, ICON_REMOVE_PAGE, TOOLTIP_REMOVE_TEXT, CSS_CLASS_DIALOG_BUTTON, false);
        Label image = new Label(component.getComponentType());
        getChildren().add(image);
        removeButton.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().removeSelectedComponent();
            ui.reloadComponentPane(ui.getEPortfolio().getSelectedPage());
            ui.updateFileToolbarControls(false);
        });
        setOnMouseClicked(e -> {
            ui.getEPortfolio().getSelectedPage().setSelectedComponent(component);
            String componentType = initComponent.getComponentType();
            switch (componentType) {
                case "header":
                    HeaderDialog headerDialog = new HeaderDialog(ui.getWindow(), ui);
                    headerDialog.show(componentType);
                    break;
                case "paragraph":
                    ParagraphDialog pDialog = new ParagraphDialog(ui.getWindow(), ui);
                    pDialog.show(componentType);
                    break;
                case "list":
                    ListDialog listDialog = new ListDialog(ui.getWindow(), ui);
                    listDialog.show(componentType);
                    break;
                case "image":
                    ImageDialog imageDialog = new ImageDialog(ui.getWindow(),ui);
                    imageDialog.show(componentType);
                    break;
                case "video":
                    VideoDialog videoDialog = new VideoDialog(ui.getWindow(),ui);
                    videoDialog.show(componentType);
                    break;
                case "slideshow":
                    SlideshowDialog slideshowDialog = new SlideshowDialog(ui.getWindow(), ui);
                    slideshowDialog.show(componentType);
                    break;
            }
            ui.reloadComponentPane(ui.getEPortfolio().getSelectedPage());
        });
    }

    public void updateComponentImage() {
        for (int i = 0; i < component.getComponentContent().size(); i++) {
            String imagePath = component.getComponentPath() + "/" + component.getComponentContent().get(i);
            File file = new File(imagePath);
            try {
                URL fileURL = file.toURI().toURL();
                Image componentImage = new Image(fileURL.toExternalForm());
                ImageView imageView = new ImageView();
                imageView.setFitWidth(component.getComponentWidth());
                imageView.setFitHeight(component.getComponentHeight());
            } catch (MalformedURLException ex) {
                ErrorHandler eh = new ErrorHandler(ui);
                eh.processError();
            }
        }
    }
}
