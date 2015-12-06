package e.view;

import static e.StartUpConstants.CSS_CLASS_DIALOG_BUTTON;
import static e.StartUpConstants.CSS_CLASS_DIALOG_LABEL;
import static e.StartUpConstants.CSS_CLASS_DIALOG_PANE;
import static e.StartUpConstants.DEFAULT_COMPONENT_IMAGE;
import static e.StartUpConstants.PATH_IMAGES;
import static e.StartUpConstants.STYLE_SHEET_UI;
import e.controller.ImageController;
import e.error.ErrorHandler;
import e.model.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
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
public class ImageDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;

    ImageView imageView;
    ImageController imageController;
    HBox captionHBox;
    Label captionLabel;
    TextField captionTextField;
    HBox widthHBox;
    Label widthLabel;
    DoubleTextField widthTextField;
    HBox heightHBox;
    Label heightLabel;
    DoubleTextField heightTextField;
    ComboBox<String> floatComboBox;
    Button leftButton;
    Button rightButton;
    Button neitherButton;

    EPortfolioMakerView ui;
    Component componentToAdd;

    // CONSTANT CHOICES
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";

    public ImageDialog(Stage primaryStage, EPortfolioMakerView initUI) {
        this(primaryStage, initUI, new Component());
    }

    /**
     * Initializes this dialog so that it can be used repeatedly for all kinds
     * of messages.
     *
     * @param primaryStage The owner of this modal dialog.
     */
    public ImageDialog(Stage primaryStage, EPortfolioMakerView initUI, Component initComponentToAdd) {
        ui = initUI;
        componentToAdd = initComponentToAdd;
        imageController = new ImageController(ui);

        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        messageLabel = new Label();

        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            ImageDialog.this.selection = sourceButton.getText();
            ImageDialog.this.hide();
        };

        // YES, NO, AND CANCEL BUTTONS
        yesButton = new Button(YES);
        noButton = new Button(NO);
        cancelButton = new Button(CANCEL);
        yesButton.setOnAction(yesNoCancelHandler);
        noButton.setOnAction(yesNoCancelHandler);
        cancelButton.setOnAction(yesNoCancelHandler);

        captionHBox = new HBox();
        captionLabel = new Label("Caption: ");
        captionTextField = new TextField();
        captionHBox.getChildren().addAll(captionLabel, captionTextField);

        widthHBox = new HBox();
        widthLabel = new Label("width: ");
        widthTextField = new DoubleTextField();
        widthHBox.getChildren().addAll(widthLabel, widthTextField);

        heightHBox = new HBox();
        heightLabel = new Label("Height: ");
        heightTextField = new DoubleTextField();
        heightHBox.getChildren().addAll(heightLabel, heightTextField);

        floatComboBox = new ComboBox();
        floatComboBox.getItems().addAll(
                "Neither",
                "Left",
                "Right"
        );
        floatComboBox.setValue("Neither");
        floatComboBox.setOnAction(eh -> {
            componentToAdd.setComponentFont_Float(floatComboBox.getValue());
        });
        captionTextField.textProperty().addListener(e -> {
            componentToAdd.getComponentCaption().removeAll(componentToAdd.getComponentCaption());
            componentToAdd.getComponentCaption().add(captionTextField.getText());
        });
        widthTextField.textProperty().addListener(e -> {
            componentToAdd.setComponentWidth(Integer.parseInt(widthTextField.getText()));
        });
        heightTextField.textProperty().addListener(e -> {
            componentToAdd.setComponentHeight(Integer.parseInt(heightTextField.getText()));
        });

        // NOW ORGANIZE OUR BUTTONS
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(yesButton);
        buttonBox.getChildren().add(noButton);
        buttonBox.getChildren().add(cancelButton);

        //SET UP DATA
        imageView = new ImageView();
        imageView.setOnMouseClicked(e -> {
            
        });
        updateComponentContent();

        // WE'LL PUT EVERYTHING HERE
        messagePane = new VBox();
        messagePane.setAlignment(Pos.TOP_CENTER);
        captionHBox.setAlignment(Pos.TOP_CENTER);
        widthHBox.setAlignment(Pos.TOP_CENTER);
        heightHBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        messagePane.getChildren().add(messageLabel);
        messagePane.getChildren().add(imageView);
        messagePane.getChildren().add(captionHBox);
        messagePane.getChildren().add(widthHBox);
        messagePane.getChildren().add(heightHBox);
        messagePane.getChildren().add(floatComboBox);
        messagePane.getChildren().add(buttonBox);

        // CSS CLASSES
        widthLabel.getStyleClass().add(CSS_CLASS_DIALOG_LABEL);
        heightLabel.getStyleClass().add(CSS_CLASS_DIALOG_LABEL);
        captionLabel.getStyleClass().add(CSS_CLASS_DIALOG_LABEL);
        yesButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        noButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        cancelButton.getStyleClass().add(CSS_CLASS_DIALOG_BUTTON);
        messagePane.getStyleClass().add(CSS_CLASS_DIALOG_PANE);
        buttonBox.getStyleClass().add(CSS_CLASS_DIALOG_PANE);

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

    public Component getComponent() {
        return componentToAdd;
    }

    public void updateComponentContent() {
        if (componentToAdd.getComponentType().isEmpty()) {
            componentToAdd.setComponentType("image");
            String imagePath = PATH_IMAGES + "/img/" + DEFAULT_COMPONENT_IMAGE;
            File file = new File(imagePath);
            try {
                URL fileURL = file.toURI().toURL();
                Image componentImage = new Image(fileURL.toExternalForm());

                imageView.setImage(componentImage);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                componentToAdd.getComponentContent().add(DEFAULT_COMPONENT_IMAGE);
                componentToAdd.setComponentPath("./images/img/");
                imageView.setOnMousePressed(e -> {
                    componentToAdd.getComponentContent().remove(0);
                    imageController.processSelectImage(componentToAdd);
                    updateComponentContent();
                });
            } catch (MalformedURLException ex) {
                ErrorHandler eh = new ErrorHandler(ui);
                eh.processError();
            }
        } else {
            widthTextField.setText(Integer.toString(componentToAdd.getComponentWidth()));
            heightTextField.setText(Integer.toString(componentToAdd.getComponentHeight()));
            if (!componentToAdd.getComponentCaption().isEmpty()) {
                captionTextField.setText(componentToAdd.getComponentCaption().get(0));
            }
            floatComboBox.setValue(componentToAdd.getComponentFont_Float());

            String imagePath = componentToAdd.getComponentPath() + "/" + componentToAdd.getComponentContent().get(0);

            File file = new File(imagePath);
            try {
                URL fileURL = file.toURI().toURL();
                Image componentImage = new Image(fileURL.toExternalForm());
                imageView.setImage(componentImage);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setOnMousePressed(e -> {
                    componentToAdd.getComponentContent().remove(0);
                    imageController.processSelectImage(componentToAdd);
                });
            } catch (MalformedURLException ex) {
                ErrorHandler eh = new ErrorHandler(ui);
                eh.processError();
            }
        }
    }
}
