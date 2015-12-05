/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.CSS_CLASS_DIALOG_PANE;
import static e.StartUpConstants.ICON_ADD_PAGE;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.TOOLTIP_ADD_LIST_ITEM;
import static e.ToolTip.TOOLTIP_REMOVE_LIST_ITEM;
import e.model.Component;
import static e.view.ParagraphDialog.CANCEL;
import static e.view.ParagraphDialog.NO;
import static e.view.ParagraphDialog.YES;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class ListDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    ScrollPane scrollPane;
    VBox listVBox;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;
    Component componentToAdd = new Component();
    ArrayList<TextField> itemsList = new ArrayList<TextField>();

    EPortfolioMakerView ui;
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
    public ListDialog(Stage primaryStage, EPortfolioMakerView initUI) {
        ui = initUI;
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();
        listVBox = new VBox();
        addList();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            ListDialog.this.selection = sourceButton.getText();
            ListDialog.this.hide();
        };

        // YES, NO, AND CANCEL BUTTONS
        yesButton = new Button(YES);
        noButton = new Button(NO);
        cancelButton = new Button(CANCEL);
        yesButton.setOnAction(yesNoCancelHandler);
        noButton.setOnAction(yesNoCancelHandler);
        cancelButton.setOnAction(yesNoCancelHandler);

        // NOW ORGANIZE OUR BUTTONS
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(yesButton);
        buttonBox.getChildren().add(noButton);
        buttonBox.getChildren().add(cancelButton);

        messagePane = new VBox();
        messagePane.setAlignment(Pos.TOP_CENTER);
        messagePane.getChildren().add(messageLabel);
        scrollPane = new ScrollPane(listVBox);
        messagePane.getChildren().add(scrollPane);
        messagePane.getChildren().add(buttonBox);

        // CSS CLASSE
        yesButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        noButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        cancelButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        messagePane.getStyleClass().add(CSS_CLASS_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_DIALOG_PANE);

        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(10, 10, 10, 10));
        messagePane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
        messageScene.getStylesheets().add(STYLE_SHEET_UI);
        this.setScene(messageScene);
        this.setHeight(800);
        this.setWidth(800);
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

    /**
     * This method loads a custom message into the label and then pops open the
     * dialog.
     *
     * @param message Message to appear inside the dialog.
     */
    public void show(String message) {
        messageLabel.setText(message);
        this.showAndWait();
    }

    public Component getComponent() {
        return componentToAdd;
    }

    public void updateList() {
        for (int i = 0; i < itemsList.size(); i++) {
            componentToAdd.getComponentContent().add(itemsList.get(i).getText());
        }
    }

    public void addList() {
        listVBox.getChildren().clear();

        Label listLabel = new Label("List: ");
        listVBox.getChildren().add(listLabel);
        Button addButton = ui.initChildButton(listVBox, ICON_ADD_PAGE, TOOLTIP_ADD_LIST_ITEM, "dialog_button", false);

        componentToAdd.setComponentType("list");
        componentToAdd.getComponentContent().clear();

        addButton.setOnAction(ee -> {
            HBox itemHBox = new HBox();
            Button removeButton = ui.initChildButton(itemHBox, ICON_REMOVE_PAGE, TOOLTIP_REMOVE_LIST_ITEM, "dialog_button", false);
            TextField itemTextField = new TextField();
            itemsList.add(itemTextField);
            itemHBox.getChildren().add(itemTextField);
            listVBox.getChildren().add(itemHBox);
            componentToAdd.getComponentContent().add(itemTextField.getText());
            removeButton.setOnAction(eee -> {
                listVBox.getChildren().remove(itemHBox);
                componentToAdd.getComponentContent().remove(itemTextField.getText());
            });
        });
    }
}
