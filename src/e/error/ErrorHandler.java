package e.error;

import e.view.EPortfolioMakerView;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");

        Exception ex = new Exception();
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
