package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.CSS_CLASS_DIALOG_PANE;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import e.controller.ImageController;
import e.model.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

    Component componentToAdd;
    EPortfolioMakerView ui;
    ArrayList<TextField> slidesList = new ArrayList<TextField>();

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
    public SlideshowDialog(Stage primaryStage, EPortfolioMakerView initUI) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        ui = initUI;
        componentToAdd = new Component();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            SlideshowDialog.this.selection = sourceButton.getText();
            SlideshowDialog.this.hide();
        };

        ScrollPane scroll = new ScrollPane();
        BorderPane slideshowPane = new BorderPane();
        scroll.setContent(slideshowPane);
        VBox slideshowControls = new VBox();
        Button addButton = ui.initChildButton(slideshowControls, "Add.png", "add a slide", CSS_CLASS_DIALOG_BUTTON, false);

        slideshowPane.setLeft(slideshowControls);
        addButton.setOnAction(e -> {
            HBox slide = new HBox();
            Button removeButton = ui.initChildButton(slide, "Remove.png", "remove a slide", CSS_CLASS_DIALOG_BUTTON, false);
            Button upButton = ui.initChildButton(slide, "Up.png", "move up a slide", CSS_CLASS_DIALOG_BUTTON, false);
            Button downButton = ui.initChildButton(slide, "Down.png", "move down a slide", CSS_CLASS_DIALOG_BUTTON, false);
            VBox captionBox = new VBox();

            Label captionLabel = new Label("caption: ");
            TextField captionTextField = new TextField();
            updatePic(primaryStage);
            captionBox.getChildren().addAll(image, captionLabel, captionTextField);
            slide.getChildren().add(captionBox);
            slides.getChildren().add(slide);
            removeButton.setOnAction(ee -> {
                slides.getChildren().remove(slide);
            });
            upButton.setOnAction(ee -> {
                int currentPosition = slides.getChildren().indexOf(slide);
                if (currentPosition != 0) {
                    slides.getChildren().remove(slide);
                    slides.getChildren().add(currentPosition - 1, slide);
                }

            });
            downButton.setOnAction(ee -> {
                int currentPosition = slides.getChildren().indexOf(slide);
                if (currentPosition != slides.getChildren().size() - 1) {
                    slides.getChildren().remove(slide);
                    slides.getChildren().add(currentPosition + 1, slide);
                }
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
        yesButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        noButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        cancelButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        messagePane.getStyleClass().add(CSS_CLASS_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_DIALOG_PANE);

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

    public void updateslides() {

    }

    public Component getComponent() {
        return componentToAdd;
    }
}
