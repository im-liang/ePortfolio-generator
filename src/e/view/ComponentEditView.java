/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_COMPONENT_LABEL;
import e.model.Component;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author jieliang
 */
public class ComponentEditView extends HBox {

    EPortfolioMakerView ui;

    Component component;
    
    Label componentLabel;

    /*
     * This constructor initializes the full UI for this component, using ininComponent data for initializing values.
     */
    public ComponentEditView(EPortfolioMakerView initUI, Component initComponent) {
        ui = initUI;
        component = initComponent;

        componentLabel = new Label(component.getComponentType());
        getChildren().add(componentLabel);
        initStyle();
    }

    private void initStyle() {
        componentLabel.getStyleClass().add(CSS_CLASS_COMPONENT_LABEL);
    }
}
