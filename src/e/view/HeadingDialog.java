package e.view;

import static e.StartUpConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static e.StartUpConstants.CSS_CLASS_LANG_OK_BUTTON;
import static e.StartUpConstants.CSS_CLASS_LANG_PROMPT;
import static e.StartUpConstants.ICON_ADD_HEADING;
import static e.StartUpConstants.ICON_ADD_LINK;
import static e.StartUpConstants.ICON_ADD_LIST;
import static e.StartUpConstants.ICON_ADD_P;
import static e.StartUpConstants.ICON_ADD_PAGE;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.TOOLTIP_ADD_HEADING;
import static e.ToolTip.TOOLTIP_ADD_LIST;
import static e.ToolTip.TOOLTIP_ADD_P;
import static e.ToolTip.TOOLTIP_ADD_TEXT_HYPERLINK;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

/**
 * This class serves to present a dialog with three options to
 * the user: Yes, No, or Cancel and lets one access which was
 * selected.
 * 
 * @author Jie Liang
 */
public class HeadingDialog extends Stage {
    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    TextField headingTextField;
    HBox choice;
    ScrollPane scrollPane;
    VBox nextChoice;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;
    
    // CONSTANT CHOICES
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";
    
    /**
     * Initializes this dialog so that it can be used repeatedly
     * for all kinds of messages.
     * 
     * @param primaryStage The owner of this modal dialog.
     */
    public HeadingDialog(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();   
        headingTextField = new TextField();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            HeadingDialog.this.selection = sourceButton.getText();
            HeadingDialog.this.hide();
        };
        
        choice = new HBox();
        Button heading = initChildButton(choice,ICON_ADD_HEADING, TOOLTIP_ADD_HEADING, "dialog_button");
        Button p = initChildButton(choice,ICON_ADD_P, TOOLTIP_ADD_P, "dialog_button");
        Button list = initChildButton(choice,ICON_ADD_LIST, TOOLTIP_ADD_LIST, "dialog_button");
        heading.setOnAction(e -> {
            Label header = new Label("Header: ");
            TextField headert = new TextField();
            nextChoice.getChildren().clear();
            nextChoice.getChildren().addAll(header,headert);
        });
        p.setOnAction(e -> {
            Label header = new Label("Paragraph: ");
            TextField headert = new TextField();
            Label font = new Label("Font: ");
            TextField fontt = new TextField();
            Label link = new Label("HyperLink: ");
            TextField linkt = new TextField();
            nextChoice.getChildren().clear();
            nextChoice.getChildren().addAll(header,headert,font,fontt, link,linkt);
        });
        list.setOnAction(e -> {
            nextChoice.getChildren().clear();
            Label header = new Label("List: ");
            Button addButton = initChildButton(nextChoice,ICON_ADD_PAGE, TOOLTIP_ADD_LIST, "dialog_button");
            addButton.setOnAction(ee -> {
                HBox item = new HBox();
                Button removeButton = initChildButton(item,ICON_REMOVE_PAGE, "Remove the list item", "dialog_button");
                TextField i = new TextField();
                item.getChildren().add(i);
                nextChoice.getChildren().add(item);
                removeButton.setOnAction(eee -> {
                    nextChoice.getChildren().remove(item);
                });
            });
            
            nextChoice.getChildren().addAll(header,addButton);
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
	       
        // WE'LL PUT EVERYTHING HERE
        //scrollPane = new ScrollPane();
        nextChoice = new VBox();
        //scrollPane.setContent(nextChoice);
        messagePane = new VBox();
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().add(choice);
        messagePane.getChildren().add(nextChoice);
        messagePane.getChildren().add(buttonBox);

	// CSS CLASSES
	yesButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
	noButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
	cancelButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
	messageLabel.getStyleClass().add(CSS_CLASS_LANG_PROMPT);
	messagePane.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
	buttonBox.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
	
        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(100, 100, 100, 100));
        messagePane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
	messageScene.getStylesheets().add(STYLE_SHEET_UI);
        this.setScene(messageScene);
    }

    /**
     * Accessor method for getting the selection the user made.
     * 
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
    
    public String getContent() {
        return headingTextField.getText();
    }
 
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
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
}