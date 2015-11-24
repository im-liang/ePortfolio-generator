package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static e.StartUpConstants.CSS_CLASS_LANG_OK_BUTTON;
import static e.StartUpConstants.CSS_CLASS_LANG_PROMPT;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import e.controller.ImageController;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 * This class serves to present a dialog with three options to the user: Yes,
 * No, or Cancel and lets one access which was selected.
 *
 * @author Jie Liang
 */
public class SlideshowDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;

    VBox slides;
    ImageView image;
    String path = "./images/img/banner.jpg";
    ImageController imageController;

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
    public SlideshowDialog(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            SlideshowDialog.this.selection = sourceButton.getText();
            SlideshowDialog.this.hide();
        };

        ScrollPane scroll = new ScrollPane();
        BorderPane slideshowPane = new BorderPane();
        scroll.setContent(slideshowPane);
        VBox slideshowControls = new VBox();
        Button addButton = initChildButton(slideshowControls, "Add.png", "add a slide", CSS_CLASS_DIALOG_BUTTON);

        slideshowPane.setLeft(slideshowControls);
        addButton.setOnAction(e -> {
            HBox slide = new HBox();
            Button removeButton = initChildButton(slide, "Remove.png", "remove a slide", CSS_CLASS_DIALOG_BUTTON);
            Button upButton = initChildButton(slide, "Up.png", "move up a slide", CSS_CLASS_DIALOG_BUTTON);
            Button downButton = initChildButton(slide, "Down.png", "move down a slide", CSS_CLASS_DIALOG_BUTTON);
            VBox captionBox = new VBox();

            Label captionLabel = new Label("caption: ");
            TextField captionTextField = new TextField();
            updatePic(primaryStage);
            captionBox.getChildren().addAll(image,captionLabel, captionTextField);
            slide.getChildren().add(captionBox);
            slides.getChildren().add(slide);
            removeButton.setOnAction(ee -> {
                slides.getChildren().remove(slide);
            });
            upButton.setOnAction(ee -> {
                int currentPosition = slides.getChildren().indexOf(slide);
                
                
            });
            downButton.setOnAction(ee -> {
                
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

        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.TOP_CENTER);
        messagePane.getChildren().add(messageLabel);
        slides = new VBox();
        slideshowPane.setRight(slides);
        messagePane.getChildren().add(scroll);
        messagePane.getChildren().add(buttonBox);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        // CSS CLASSES
        yesButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
        noButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
        cancelButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);
        messageLabel.getStyleClass().add(CSS_CLASS_LANG_PROMPT);
        messagePane.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);

        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(20, 20, 20, 20));
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
        public void updatePic(Stage primaryStage) {
        image = new ImageView();
        File file = new File(path);
        imageController = new ImageController(primaryStage);
        image.setOnMousePressed(e -> {
            imageController.processSelectImage();
        });
        try {
            URL fileURL = file.toURI().toURL();
            Image i = new Image(fileURL.toExternalForm());
            image.setImage(i);
            image.setFitWidth(200);
            image.setFitHeight(200);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SlideshowDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
