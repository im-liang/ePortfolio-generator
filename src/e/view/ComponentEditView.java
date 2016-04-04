/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_COMPONENT_LABEL;
import static e.StartUpConstants.CSS_CLASS_COMPONENT_TEXTFIELD;
import static e.StartUpConstants.DEFAULT_BANNER;
import static e.StartUpConstants.DEFAULT_FOOTER;
import static e.StartUpConstants.DEFAULT_STUDENTNAME;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.LABEL_BANNER;
import static e.StartUpConstants.LABEL_FOOTER;
import static e.StartUpConstants.LABEL_STUDENT_NAME;
import e.model.Component;
import static e.view.EPortfolioMakerView.initChildButton;
import java.awt.Scrollbar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class ComponentEditView extends HBox {

    EPortfolioMakerView ui;

    Component component;

    /*
     * This constructor initializes the full UI for this component, using ininComponent data for initializing values.
     */
    public ComponentEditView(EPortfolioMakerView initUI, Component initComponent) {
        ui = initUI;
        component = initComponent;

        Label image = new Label(component.getComponentType());
        getChildren().add(image);
    }

    private void initStyle() {
    }
}
