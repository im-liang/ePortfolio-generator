package e.error;

import e.view.EPortfolioMakerView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class provides error messages to the user when the occur. Note that
 * error messages should be retrieved from language-dependent XML files and
 * should have custom messages that are different depending on the type of error
 * so as to be informative concerning what went wrong.
 *
 * @author Jie Liang
 */
public class ErrorHandler {

    // APP UI

    private EPortfolioMakerView ui;

    // KEEP THE APP UI FOR LATER
    public ErrorHandler(EPortfolioMakerView initUI) {
        ui = initUI;
    }

    /**
     * This method provides all error feedback. It gets the feedback text, which
     * changes depending on the type of error, and presents it to the user in a
     * dialog box.
     *
     */
    public void processError() {
        // GET THE FEEDBACK TEXT
        String errorFeedbackText = "An Unexpected Error has Occured";

        // POP OPEN A DIALOG TO DISPLAY TO THE USER
        Alert alertDialog = new Alert(AlertType.WARNING, errorFeedbackText);
        alertDialog.showAndWait();
    }

    public void inValidNumber() {
        // GET THE FEEDBACK TEXT
        String errorFeedbackText = "Please enter a number!";

        // POP OPEN A DIALOG TO DISPLAY TO THE USER
        Alert alertDialog = new Alert(AlertType.WARNING, errorFeedbackText);
        alertDialog.showAndWait();
    }
}
