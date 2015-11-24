package e.view;

import static e.StartUpConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static e.StartUpConstants.CSS_CLASS_LANG_OK_BUTTON;
import static e.StartUpConstants.CSS_CLASS_LANG_PROMPT;
import static e.StartUpConstants.STYLE_SHEET_UI;
import e.controller.ImageController;
import e.controller.VideoController;
import e.error.ErrorHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

/**
 * This class serves to present a dialog with three options to the user: Yes,
 * No, or Cancel and lets one access which was selected.
 *
 * @author Jie Liang
 */
public class VideoDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG

    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;

    HBox captionBox;
    Label caption;
    TextField captiont;
    HBox widthBox;
    Label width;
    TextField widtht;
    HBox heightBox;
    Label height;
    TextField heightt;

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
    public VideoDialog(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            VideoDialog.this.selection = sourceButton.getText();
            VideoDialog.this.hide();
        };

        // YES, NO, AND CANCEL BUTTONS
        yesButton = new Button(YES);
        noButton = new Button(NO);
        cancelButton = new Button(CANCEL);
        yesButton.setOnAction(yesNoCancelHandler);
        noButton.setOnAction(yesNoCancelHandler);
        cancelButton.setOnAction(yesNoCancelHandler);

        ImageView image = new ImageView();
        String imagePath = "./images/img/banner.jpg";
        File file = new File(imagePath);
        VideoController imageController = new VideoController(primaryStage);
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
            Logger.getLogger(ImageDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        captionBox = new HBox();
        caption = new Label("Caption: ");
        captiont = new TextField();
        captionBox.getChildren().addAll(caption, captiont);
        widthBox = new HBox();
        width = new Label("width: ");
        widtht = new TextField();
        widthBox.getChildren().addAll(width, widtht);
        heightBox = new HBox();
        height = new Label("Height: ");
        heightt = new TextField();
        widthBox.getChildren().addAll(height, heightt);

        // NOW ORGANIZE OUR BUTTONS
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(yesButton);
        buttonBox.getChildren().add(noButton);
        buttonBox.getChildren().add(cancelButton);

        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.TOP_CENTER);
        captionBox.setAlignment(Pos.TOP_CENTER);
        widthBox.setAlignment(Pos.TOP_CENTER);
        heightBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().add(image);
        messagePane.getChildren().add(captionBox);
        messagePane.getChildren().add(widthBox);
        messagePane.getChildren().add(heightBox);
        messagePane.getChildren().add(buttonBox);

        // CSS CLASSES
        yesButton.getStyleClass().add("dialog_button");
        noButton.getStyleClass().add("dialog_button");
        cancelButton.getStyleClass().add("dialog_button");
        messageLabel.getStyleClass().add(CSS_CLASS_LANG_PROMPT);
        messagePane.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);

        // MAKE IT LOOK NICE
        messagePane.setPadding(new Insets(10, 20, 20, 20));
        messagePane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(messagePane);
        messageScene.getStylesheets().add(STYLE_SHEET_UI);
        this.setScene(messageScene);
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
}
