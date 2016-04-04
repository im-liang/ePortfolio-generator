/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.dialog;

import e.model.Component;
import e.model.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class HeaderDialog extends Dialog {

    BorderPane workspace = super.getDialogWorkspace();
    Component componentToAdd;
    
    Label headerLabel;
    TextField headerTextField;

    public HeaderDialog(Stage primaryStage, Component initComponentToAdd) {
        super(primaryStage);
        componentToAdd = initComponentToAdd;
    }

    public void initWorkspace() {
        headerLabel = new Label("Header: ");
    }

    private void addHeader() {

        componentToAdd.setComponentType("header");

        
        if (componentToAdd.getComponentContent().isEmpty()) {
            headerTextField = new TextField("Content");
        } else {
            headerTextField = new TextField(componentToAdd.getComponentContent().get(0));
        }
        selectTextChoiceVBox.getChildren().clear();
        selectTextChoiceVBox.getChildren().addAll(headerLabel, headerTextField);
        headerTextField.textProperty().addListener(e -> {
            componentToAdd.getComponentContent().removeAll(componentToAdd.getComponentContent());
            componentToAdd.getComponentContent().add(headerTextField.getText());
        });
    }
}
