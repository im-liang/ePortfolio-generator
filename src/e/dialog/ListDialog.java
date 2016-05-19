/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.dialog;

import e.model.Component;
import e.model.Dialog;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class ListDialog extends Dialog {

    Component componentToAdd;
    public ListDialog(Stage primaryStage) {
        super(primaryStage);
        componentToAdd = new Component();
    }

    public Component getComponent() {
        return componentToAdd;
    }
}
