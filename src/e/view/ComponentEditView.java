/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.PATH_ICONS;
import static e.ToolTip.TOOLTIP_REMOVE_TEXT;
import e.controller.ImageController;
import e.model.Component;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
                    TextDialog textDialog = new TextDialog(ui.getWindow());
                    break;
            }
            ui.reloadComponentPane(ui.getEPortfolio().getSelectedPage());
        });
    }
}
