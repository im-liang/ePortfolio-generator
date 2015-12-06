/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.controller;

import static e.StartUpConstants.DEFAULT_PAGETITLE;
import e.error.ErrorHandler;
import e.model.Component;
import e.model.EPortfolio;
import e.model.Page;
import e.view.EPortfolioMakerView;
import e.view.HeaderDialog;
import e.view.ImageDialog;
import e.view.ListDialog;
import e.view.ParagraphDialog;
import e.view.SlideshowDialog;
import e.view.VideoDialog;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author jieliang
 */
public class EPortfolioEditController {

    private EPortfolioMakerView ui;
    public static String LAYOUTTEMPLATE = "layout_1.css";
    public static String COLORTEMPLATE = "color_1.css";

    public EPortfolioEditController(EPortfolioMakerView initUI) {
        ui = initUI;
    }

    public void handleAddPageRequest(String pageTitle, String pageFont, String pageFooter,String banner, String bannerFileName, String bannerFilePath, ArrayList<String> components) {
        ui.getEPortfolio().addPage(pageTitle, pageFont, pageFooter,banner, bannerFileName, bannerFilePath, components);
    }

    public void handleRemovePageRequest() {
        EPortfolio ePortfolio = ui.getEPortfolio();
        ePortfolio.removeSelectedPage();
    }

    public void handleBannerImageRequest(Page page) {
        try {
            FileChooser imageFileChooser = new FileChooser();

            // SET THE STARTING DIRECTORY
            imageFileChooser.setInitialDirectory(new File("./images/img/"));

            // LET'S ONLY SEE IMAGE FILES
            FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
            imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);

            // LET'S OPEN THE FILE CHOOSER
            File file = imageFileChooser.showOpenDialog(null);
            if (file != null) {
                String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
                String fileName = file.getName();
                ImageView image = new ImageView();
                URL fileURL = file.toURI().toURL();
                Image i = new Image(fileURL.toExternalForm());
                image.setImage(i);
                image.setFitWidth(200);
                image.setFitHeight(200);
                
                page.setBannerImageName(fileName);
                page.setBannerImagePath(path);
                ui.getPagePane().getChildren().add(image);
                ui.updateFileToolbarControls(false);
            }
        } catch (MalformedURLException ex) {
            ErrorHandler error = new ErrorHandler(ui);
            error.processError();
        }
    }

    public void handleLayoutTemplateRequest() {
        // DIFFERENT CSS
        ComboBox layoutTemplateComboBox = new ComboBox();
        layoutTemplateComboBox.getItems().addAll(
                "Top_left",
                "Top_center",
                "Left",
                "Right",
                "Top_Right"
        );
        layoutTemplateComboBox.setValue("Top_left");
        String chosedLayout = layoutTemplateComboBox.getValue().toString();
        layoutTemplateComboBox.setOnAction(eh -> {
            switch (chosedLayout) {
                case "Top_left":
                    LAYOUTTEMPLATE = "layout_1.css";
                    break;
                case "Top_center":
                    LAYOUTTEMPLATE = "layout_2.css";
                    break;
                case "Left":
                    LAYOUTTEMPLATE = "layout_3.css";
                    break;
                case "Right":
                    LAYOUTTEMPLATE = "layout_4.css";
                    break;
                case "Top_Right":
                    LAYOUTTEMPLATE = "layout_5.css";
                    break;
            }
        });
        ui.getPagePane().getChildren().add(layoutTemplateComboBox);
    }

    public void handleColorTemplateRequest() {
        ComboBox colorTemplateComboBox = new ComboBox();
        colorTemplateComboBox.getItems().addAll(
                "Red",
                "Blue",
                "Light Blue",
                "Dark Blue",
                "Yellow"
        );
        colorTemplateComboBox.setValue("Red");
        String chosedColor = colorTemplateComboBox.getValue().toString();
        colorTemplateComboBox.setOnAction(eh -> {
            switch (chosedColor) {
                case "Teal":
                    COLORTEMPLATE = "color_1.css";
                    break;
                case "Red":
                    COLORTEMPLATE = "color_5.css";
                    break;
                case "Light Blue":
                    COLORTEMPLATE = "color_3.css";
                    break;
                case "Dark Blue":
                    COLORTEMPLATE = "color_2.css";
                    break;
                case "Yellow":
                    COLORTEMPLATE = "color_4.css";
                    break;
            }
        });
        ui.getPagePane().getChildren().add(colorTemplateComboBox);
    }

    public void handleAddImageRequest() {
        ImageDialog imageDialog = new ImageDialog(ui.getWindow(), ui);
        imageDialog.show("Image");
        String selection = imageDialog.getSelection();
        boolean addComponent = selection.equals(imageDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(imageDialog.getComponent());
        }

    }

    public void handleAddVideoRequest() {
        VideoDialog videoDialog = new VideoDialog(ui.getWindow(), ui);
        videoDialog.show("Video");
        String selection = videoDialog.getSelection();
        boolean addComponent = selection.equals(videoDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(videoDialog.getComponent());
        }
    }

    public void handleAddSlideshowRequest() {
        SlideshowDialog slideshowDialog = new SlideshowDialog(ui.getWindow(), ui);
        slideshowDialog.show("Slideshow");
        String selection = slideshowDialog.getSelection();
        boolean addComponent = selection.equals(slideshowDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(slideshowDialog.getComponent());
        }
    }

    public void handleAddFontRequest() {
        HBox fontHBox = new HBox();
        ComboBox fontComboBox = new ComboBox();
        fontComboBox.getItems().addAll(
                "'Montserrat', sans-serif;",
                "'Poiret One', cursive;",
                "'Indie Flower', cursive;",
                "'Lobster', cursive;",
                "'Nunito', sans-serif;"
        );
        fontComboBox.setValue("'Montserrat', sans-serif;");
        ui.getEPortfolio().getSelectedPage().setFont(fontComboBox.getValue().toString());
        fontHBox.getChildren().addAll(fontComboBox);
        ui.getPagePane().getChildren().add(fontHBox);
        fontComboBox.setOnAction(e -> {
            ui.getEPortfolio().getSelectedPage().setFont(fontComboBox.getValue().toString());
            ui.updateFileToolbarControls(false);
        });
    }

    public void handleAddHeaderRequest() {
        HeaderDialog headerDialog = new HeaderDialog(ui.getWindow(), ui);
        headerDialog.show("Header");
        String selection = headerDialog.getSelection();
        boolean addComponent = selection.equals(headerDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(headerDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }

    public void handleAddParagraphRequest() {
        ParagraphDialog paragraphDialog = new ParagraphDialog(ui.getWindow(), ui);
        paragraphDialog.show("Paragraph");
        String selection = paragraphDialog.getSelection();
        boolean addComponent = selection.equals(paragraphDialog.YES);
        if (addComponent) {
            ui.getEPortfolio().getSelectedPage().addComponent(paragraphDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }

    public void handleAddListRequest() {
        ListDialog listDialog = new ListDialog(ui.getWindow(), ui);
        listDialog.show("List");
        String selection = listDialog.getSelection();
        boolean addComponent = selection.equals(listDialog.YES);
        if (addComponent) {
            listDialog.updateList();
            ui.getEPortfolio().getSelectedPage().addComponent(listDialog.getComponent());
            ui.updateFileToolbarControls(!addComponent);
        }
    }
}
