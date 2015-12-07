/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.CSS_CLASS_DIALOG_PANE;
import static e.StartUpConstants.STYLE_SHEET_UI;
import e.model.Component;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class ParagraphDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    ScrollPane scrollPane;
    VBox paragraphVBox;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;
    Component componentToAdd;
    ArrayList<TextField> itemsList = new ArrayList<TextField>();

    EPortfolioMakerView ui;
    // CONSTANT CHOICES
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";

    public ParagraphDialog(Stage primaryStage, EPortfolioMakerView initUI) {
        this(primaryStage, initUI, new Component());
    }

    /**
     * Initializes this dialog so that it can be used repeatedly for all kinds
     * of messages.
     *
     * @param primaryStage The owner of this modal dialog.
     */
    public ParagraphDialog(Stage primaryStage, EPortfolioMakerView initUI, Component initComponentToAdd) {
        ui = initUI;
        componentToAdd = initComponentToAdd;
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();
        paragraphVBox = new VBox();
        addParagraph();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            ParagraphDialog.this.selection = sourceButton.getText();
            ParagraphDialog.this.hide();
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
        scrollPane = new ScrollPane(paragraphVBox);
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

    public void addParagraph() {
        componentToAdd.setComponentType("paragraph");
        Label paragraphLabel = new Label("Paragraph: ");
        TextArea paragraphTextArea = new TextArea();
        Label fontLabel = new Label("Font: ");
        ComboBox fontComboBox = new ComboBox();
        //FONT
        fontComboBox.getItems().addAll(
                "'Montserrat', sans-serif;",
                "'Poiret One', cursive;",
                "'Indie Flower', cursive;",
                "'Lobster', cursive;",
                "'Nunito', sans-serif;"
        );
        if (componentToAdd.getComponentFont_Float() == null) {
            fontComboBox.setValue("'Montserrat', sans-serif;");
            componentToAdd.setComponentFont_Float(fontComboBox.getValue().toString());
        } else {
            fontComboBox.setValue(componentToAdd.getComponentFont_Float());
        }
        fontComboBox.setOnAction(eh -> {
            componentToAdd.setComponentFont_Float(fontComboBox.getValue().toString());
        });

        if (!componentToAdd.getComponentContent().isEmpty()) {
            paragraphTextArea.setText(componentToAdd.getComponentContent().get(0));
        }

        paragraphVBox.getChildren().clear();
        paragraphVBox.getChildren().add(paragraphLabel);
        paragraphVBox.getChildren().add(paragraphTextArea);
        Button hyperlinkButton = ui.initChildButton(paragraphVBox, "Link.png", "Add Hyperlink", "dialog_button", false);
        paragraphVBox.getChildren().add(fontLabel);
        paragraphVBox.getChildren().add(fontComboBox);

        hyperlinkButton.setOnAction(e -> {
            if (!paragraphTextArea.getSelectedText().isEmpty()) {
                addHyperlink(paragraphTextArea, hyperlinkButton);
            }
        });
        paragraphTextArea.textProperty().addListener(ee -> {
            componentToAdd.getComponentContent().removeAll(componentToAdd.getComponentContent());
            componentToAdd.getComponentContent().add(paragraphTextArea.getText());
            if (!paragraphTextArea.getText().isEmpty()) {
                hyperlinkButton.setDisable(false);
            } else {
                hyperlinkButton.setDisable(true);
            }
        });
    }

    private void addHyperlink(TextArea textArea, Button hyperlinkButton) {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Conformation Dialog");
        alert.setHeaderText("Do you want to add a Hyperlink?");
        Optional<String> result = alert.showAndWait();

        if (result.isPresent()) {
            textArea.replaceSelection("<a href=" + result.get() + ">" + textArea.getSelectedText() + "</a>");
        }
    }
}
