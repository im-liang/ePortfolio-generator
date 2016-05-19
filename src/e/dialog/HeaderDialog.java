/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.dialog;

import static e.StartUpConstants.HEADER_DIALOG_LABEL;
import e.model.Component;
import e.model.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class HeaderDialog extends Dialog {

    BorderPane workspace = super.getDialogWorkspace();
    HBox headerHBox;
    Component componentToAdd;

    Label headerLabel;
    TextField headerTextField;

    public HeaderDialog(Stage primaryStage) {
        super(primaryStage);
        componentToAdd = new Component();
        initWorkspace();
    }
    
    public HeaderDialog(Stage primaryStage, Component initComponentToEdit) {
        super(primaryStage);
        componentToAdd = initComponentToEdit;
        initWorkspace();
    }

    public void initWorkspace() {
        headerHBox = new HBox(5);
        headerLabel = new Label(HEADER_DIALOG_LABEL);
        if (componentToAdd.getComponentContent().isEmpty()) {
            headerTextField = new TextField("Content");
        } else {
            headerTextField = new TextField(componentToAdd.getComponentContent().get(0));
        }
        headerHBox.getChildren().addAll(headerLabel, headerTextField);
//        headerHBox.setHgrow(workspace, Priority.ALWAYS);
        workspace.setCenter(headerHBox);
        addHeaderComponent();
    }

    private void addHeaderComponent() {

        componentToAdd.setComponentType("header");

        headerTextField.textProperty().addListener(e -> {
            componentToAdd.getComponentContent().removeAll(componentToAdd.getComponentContent());
            componentToAdd.getComponentContent().add(headerTextField.getText());
        });
    }

    public Component getComponent() {
        return componentToAdd;
    }
}
