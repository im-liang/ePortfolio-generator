/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import static e.StartUpConstants.CSS_CLASS_DIALOG_OPTION_BUTTON;
import static e.StartUpConstants.CSS_CLASS_DIALOG_PANE;
import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON_PANE;
import static e.StartUpConstants.STYLE_SHEET_UI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class Dialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    BorderPane dialogBorderPane;
    Scene dialogScene;
    Label dialogLabel;
    BorderPane dialogWorkspaceBorderPane;
    
    HBox buttonBox;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    
    String selection = CANCEL;
    
    // CONSTANT CHOICES
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";

    
    /**
     * Initializes this dialog so that it can be used repeatedly for all kinds
     * of messages.
     *
     * @param primaryStage The owner of this modal dialog.
     */
    public Dialog(Stage primaryStage) {

        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        dialogLabel = new Label();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            Dialog.this.selection = sourceButton.getText();
            Dialog.this.hide();
        };

        // YES, NO, AND CANCEL BUTTONS
        yesButton = new Button(YES);
        noButton = new Button(NO);
        cancelButton = new Button(CANCEL);
        yesButton.setOnAction(yesNoCancelHandler);
        noButton.setOnAction(yesNoCancelHandler);
        cancelButton.setOnAction(yesNoCancelHandler);

        // NOW ORGANIZE THE BUTTONS
        buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(yesButton,noButton,cancelButton);

        dialogBorderPane = new BorderPane();
        dialogWorkspaceBorderPane = new BorderPane();
        
        dialogBorderPane.setCenter(dialogWorkspaceBorderPane);
        dialogBorderPane.setBottom(buttonBox);

        initStyle();
        
        // AND PUT IT IN THE WINDOW
        dialogScene = new Scene(dialogBorderPane);
        dialogScene.getStylesheets().add(STYLE_SHEET_UI);
        this.setScene(dialogScene);
        this.setHeight(500);
        this.setWidth(500);
    }

    private void initStyle() {
        // CSS CLASS
        yesButton.getStyleClass().add(CSS_CLASS_DIALOG_OPTION_BUTTON);
        noButton.getStyleClass().add(CSS_CLASS_DIALOG_OPTION_BUTTON);
        cancelButton.getStyleClass().add(CSS_CLASS_DIALOG_OPTION_BUTTON);
        dialogBorderPane.getStyleClass().add(CSS_CLASS_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON_PANE);

        // MAKE IT LOOK NICE
        dialogBorderPane.setPadding(new Insets(10, 10, 10, 10));        
    }
    /**
     * Accessor method for getting the selection the user made.
     *
     * @return Either YES, NO, or CANCEL, depending on which button the user
     * selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
    
    public BorderPane getDialogWorkspace() {
        return dialogWorkspaceBorderPane;
    }

    /**
     * This method loads a custom message into the label and then pops open the
     * dialog.
     *
     * @param message type of the dialog.
     */
    public void show(String message) {
        this.setTitle(message);
        this.showAndWait();
    }    
}
