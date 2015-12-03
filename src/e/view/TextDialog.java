package e.view;

import static e.StartUpConstants.ICON_ADD_HEADING;
import static e.StartUpConstants.ICON_ADD_LIST;
import static e.StartUpConstants.ICON_ADD_P;
import static e.StartUpConstants.ICON_ADD_PAGE;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.TOOLTIP_ADD_HEADING;
import static e.ToolTip.TOOLTIP_ADD_LIST;
import static e.ToolTip.TOOLTIP_ADD_P;
import e.model.Component;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

/**
 * This class serves to present a dialog with three options to the user: Yes,
 * No, or Cancel and lets one access which was selected.
 *
 * @author Jie Liang
 */
public class TextDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    HBox textChoiceHBox;
    ScrollPane scrollPane;
    VBox selectTextChoiceVBox;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;
    Component initComponent = new Component();
    String userChoice;

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
    public TextDialog(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();
        selectTextChoiceVBox = new VBox();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            TextDialog.this.selection = sourceButton.getText();
            TextDialog.this.hide();
        };

        textChoiceHBox = new HBox();
        Button heading = initChildButton(textChoiceHBox, ICON_ADD_HEADING, TOOLTIP_ADD_HEADING, "dialog_button");
        Button paragraph = initChildButton(textChoiceHBox, ICON_ADD_P, TOOLTIP_ADD_P, "dialog_button");
        Button list = initChildButton(textChoiceHBox, ICON_ADD_LIST, TOOLTIP_ADD_LIST, "dialog_button");

        userChoice = "header";
        initComponent.setComponentType(userChoice);
        Label headerLabel = new Label("Header: ");
        TextField headerTextField = new TextField();
        selectTextChoiceVBox.getChildren().clear();
        selectTextChoiceVBox.getChildren().addAll(headerLabel, headerTextField);
        headerTextField.textProperty().addListener(e -> {
            initComponent.getComponentContent().removeAll(initComponent.getComponentContent());
            initComponent.getComponentContent().add(headerTextField.getText());
        });

        heading.setOnAction(e -> {
            userChoice = "header";
            selectTextChoiceVBox.getChildren().clear();
            selectTextChoiceVBox.getChildren().addAll(headerLabel, headerTextField);
            initComponent.setComponentType(userChoice);
            initComponent.getComponentContent().removeAll(initComponent.getComponentContent());
            initComponent.getComponentContent().add(headerTextField.getText());
        });
        paragraph.setOnAction(e -> {
            userChoice = "paragraph";
            initComponent.setComponentType(userChoice);
            Label paragraphLabel = new Label("Paragraph: ");
            TextArea paragraphTextArea = new TextArea();
            Label fontLabel = new Label("Font: ");
            TextField fontTextField = new TextField();
            paragraphTextArea.textProperty().addListener(ee -> {
                initComponent.getComponentContent().removeAll(initComponent.getComponentContent());
                initComponent.getComponentContent().add(paragraphTextArea.getText());
            });
            fontTextField.textProperty().addListener(ee -> {
                initComponent.setComponentFont_Float(fontTextField.getText());
            });

            selectTextChoiceVBox.getChildren().clear();
            selectTextChoiceVBox.getChildren().addAll(paragraphLabel, paragraphTextArea, fontLabel, fontTextField);
        });
        list.setOnAction(e -> {
            userChoice = "list";
            selectTextChoiceVBox.getChildren().clear();
            Label listLabel = new Label("List: ");
            selectTextChoiceVBox.getChildren().add(listLabel);
            Button addButton = initChildButton(selectTextChoiceVBox, ICON_ADD_PAGE, TOOLTIP_ADD_LIST, "dialog_button");
            
            initComponent.setComponentType(userChoice);
            initComponent.getComponentContent().removeAll(initComponent.getComponentContent());
            
            addButton.setOnAction(ee -> {
                HBox item = new HBox();
                Button removeButton = initChildButton(item, ICON_REMOVE_PAGE, "Remove the list item", "dialog_button");
                TextField itemTextField = new TextField();
                item.getChildren().add(itemTextField);
                selectTextChoiceVBox.getChildren().add(item);
                initComponent.getComponentContent().add(itemTextField.getText());
                removeButton.setOnAction(eee -> {
                    selectTextChoiceVBox.getChildren().remove(item);
                    initComponent.getComponentContent().remove(itemTextField.getText());
                });
                itemTextField.textProperty().addListener(eee -> {
                    initComponent.getComponentContent()
                });
            });
        });

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
        messagePane.getChildren().add(textChoiceHBox);
        ScrollPane scroll = new ScrollPane(selectTextChoiceVBox);
        messagePane.getChildren().add(scroll);
        messagePane.getChildren().add(buttonBox);

        // CSS CLASSES
//        yesButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
//        noButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
//        cancelButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
//        messageLabel.getStyleClass().add(CSS_CLASS_LANG_PROMPT);
//        messagePane.getStyleClass().add(CSS_CLASS_DIALOG_PANE);
//        buttonBox.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
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

    public String getChoice() {
        return userChoice;
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

    public Button initChildButton(Pane toolbar, String iconFileName, String tooltip, String cssClass) {
        String iconPath = "file:" + PATH_ICONS + iconFileName;
        Image buttonImage = new Image(iconPath);
        Button button = new Button();
        button.getStyleClass().add(cssClass);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(tooltip);
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    public Component getComponent() {
        return initComponent;
    }
}
