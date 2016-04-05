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
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class ParagraphDialog extends Dialog {
    
    BorderPane workspace = super.getDialogWorkspace();
    HBox headerHBox;
    Component componentToAdd;

    Label headerLabel;
    TextField headerTextField;
    
    public ParagraphDialog(Stage primaryStage) {
        super(primaryStage);
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
        workspace.setCenter(headerHBox);
        addHeader();
    }

    private void addHeader() {

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
