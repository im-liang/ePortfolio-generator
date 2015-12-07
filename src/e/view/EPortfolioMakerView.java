/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_BANNER_TEXT;
import static e.StartUpConstants.CSS_CLASS_COMPONENT;
import static e.StartUpConstants.CSS_CLASS_COMPONENT_PANE;
import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_PAGE;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_PAGE_PANE;
import static e.StartUpConstants.CSS_CLASS_SELECTED_COMPONENT;
import static e.StartUpConstants.CSS_CLASS_SELECTED_PAGE;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_WORKSPACE;
import static e.StartUpConstants.ICON_ADD_HEADING;
import static e.StartUpConstants.ICON_ADD_IMAGE;
import static e.StartUpConstants.ICON_ADD_LIST;
import static e.StartUpConstants.ICON_ADD_P;
import static e.StartUpConstants.ICON_ADD_PAGE;
import static e.StartUpConstants.ICON_ADD_SLIDESHOW;
import static e.StartUpConstants.ICON_ADD_TEXT;
import static e.StartUpConstants.ICON_ADD_VIDEO;
import static e.StartUpConstants.ICON_BANNER_IMAGE;
import static e.StartUpConstants.ICON_COLOR_TEMPLATE;
import static e.StartUpConstants.ICON_EXIT;
import static e.StartUpConstants.ICON_EXPORT_EPORTFOLIO;
import static e.StartUpConstants.ICON_LAYOUT_TEMPLATE;
import static e.StartUpConstants.ICON_LOAD_EPORTFOLIO;
import static e.StartUpConstants.ICON_NEW_EPORTFOLIO;
import static e.StartUpConstants.ICON_PAGE_EDITOR;
import static e.StartUpConstants.ICON_PAGE_VIEWER;
import static e.StartUpConstants.ICON_REMOVE_PAGE;
import static e.StartUpConstants.ICON_SAVE_AS_EPORTFOLIO;
import static e.StartUpConstants.ICON_SAVE_EPORTFOLIO;
import static e.StartUpConstants.LABEL_BANNER;
import static e.StartUpConstants.LABEL_FOOTER;
import static e.StartUpConstants.LABEL_PAGE_TITLE;
import static e.StartUpConstants.LABEL_STUDENT_NAME;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.DEFAULT_FOOTER;
import static e.ToolTip.DEFAULT_PAGE_TITLE;
import static e.ToolTip.DEFAULT_STUDENT_NAME;
import static e.ToolTip.TOOLTIP_ADD_HEADING;
import static e.ToolTip.TOOLTIP_ADD_IMAGE;
import static e.ToolTip.TOOLTIP_ADD_LIST;
import static e.ToolTip.TOOLTIP_ADD_P;
import static e.ToolTip.TOOLTIP_ADD_PAGE;
import static e.ToolTip.TOOLTIP_ADD_SLIDESHOW;
import static e.ToolTip.TOOLTIP_ADD_TEXT;
import static e.ToolTip.TOOLTIP_ADD_VIDEO;
import static e.ToolTip.TOOLTIP_BANNER_IMAGE;
import static e.ToolTip.TOOLTIP_COLOR_TEMPLATE;
import static e.ToolTip.TOOLTIP_EXIT;
import static e.ToolTip.TOOLTIP_EXPORT_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_LAYOUT_TEMPLATE;
import static e.ToolTip.TOOLTIP_LOAD_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_NEW_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_PAGE_EDITOR;
import static e.ToolTip.TOOLTIP_PAGE_VIEWER;
import static e.ToolTip.TOOLTIP_SAVE_AS_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_SAVE_EPORTFOLIO;
import e.controller.EPortfolioEditController;
import e.controller.FileController;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import static e.file.EPortfolioSiteExporter.INDEX_FILE;
import e.model.Component;
import e.model.EPortfolio;
import e.model.Page;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * user interface
 *
 * @author jieliang
 */
public class EPortfolioMakerView {

    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    Stage primaryStage;
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane ePane;

    //THIS IS THE TOP TOOLBAR
    BorderPane ePortfolioToolbarPane;

    // THIS IS THE TOP LEFT TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newEPortfolioButton;
    Button loadEPortfolioButton;
    Button saveEPortfolioButton;
    Button saveAsEPortfolioButton;
    Button exportEPortfolioButton;
    Button exitButton;

    //THIS IS THE TOP RIGHT TOOLBAR AND ITS CONTROLS
    FlowPane modePane;
    Button pageEditorButton;
    Button pageViewerButton;

    // WORKSPACE
    BorderPane workspace;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane componentEditToolbarPane;
    Button addHeaderButton;
    Button addParagraphButton;
    Button addListButton;
    Button addImageButton;
    Button addVideoButton;
    Button addSlideshowButton;

    // FOR THE PAGE TITLE
    VBox bannerPane;
    Label bannerLabel;
    TextField bannerTextField;
    Label studentNameLabel;
    TextField studentNameTextField;

    Label footerLabel;
    TextField footerTextField;

    //Center
    ScrollPane pageEditorScrollPane;
    VBox pageEditorPane;
    VBox componentEditorPane;

    //Right SIDE OF THE WORKSPACE
    BorderPane pageUIPane;
    ScrollPane pageTitlesScrollPane;
    VBox pageTitlePane;
    FlowPane pageEditToolbarPane;
    Button addPageButton;
    Button removePageButton;
    Button bannerImageButton;
    Button layoutTemplateButton;
    Button colorTemplateButton;
    Button fontButton;

    //current working eportfolio
    EPortfolio ePortfolio;

    //save and load eportfolio
    EPortfolioFileManager fileManager;

    EPortfolioSiteExporter siteExporter;

    private ErrorHandler errorHandler;

    private FileController fileController;

    private EPortfolioEditController editController;

    public EPortfolioMakerView(EPortfolioFileManager initFileManager, EPortfolioSiteExporter initSiteExporter) {
        fileManager = initFileManager;
        siteExporter = initSiteExporter;
        ePortfolio = new EPortfolio(this);
        errorHandler = new ErrorHandler(this);
    }

    public EPortfolio getEPortfolio() {
        return ePortfolio;
    }

    public Stage getWindow() {
        return primaryStage;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void startUI(Stage initPrimaryStage, String windowTitle) {
        initFileToolbar();

        initWorkspace();

        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    private void initWorkspace() {
        workspace = new BorderPane();
        //left side of the screen
        componentEditToolbarPane = new FlowPane();
        addHeaderButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_HEADING, TOOLTIP_ADD_HEADING, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        addParagraphButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_P, TOOLTIP_ADD_P, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        addListButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_LIST, TOOLTIP_ADD_LIST, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        addImageButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        addSlideshowButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SLIDESHOW, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
        addVideoButton = this.initChildButton(componentEditToolbarPane, ICON_ADD_VIDEO, TOOLTIP_ADD_VIDEO, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);

        //center
        pageEditorPane = new VBox();
        componentEditorPane = new VBox();

        pageEditorScrollPane = new ScrollPane(pageEditorPane);
        pageEditorScrollPane.setFitToWidth(true);
        pageEditorScrollPane.setFitToHeight(true);

        //right
        pageUIPane = new BorderPane();
        pageEditToolbarPane = new FlowPane();

        addPageButton = this.initChildButton(pageEditToolbarPane, ICON_ADD_PAGE, TOOLTIP_ADD_PAGE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        bannerImageButton = this.initChildButton(pageEditToolbarPane, ICON_BANNER_IMAGE, TOOLTIP_BANNER_IMAGE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        layoutTemplateButton = this.initChildButton(pageEditToolbarPane, ICON_LAYOUT_TEMPLATE, TOOLTIP_LAYOUT_TEMPLATE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        colorTemplateButton = this.initChildButton(pageEditToolbarPane, ICON_COLOR_TEMPLATE, TOOLTIP_COLOR_TEMPLATE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        fontButton = this.initChildButton(pageEditToolbarPane, "page.png", "Select Font for the Page", CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);

        pageTitlePane = new VBox();
        pageTitlesScrollPane = new ScrollPane(pageTitlePane);
        pageUIPane.setTop(pageEditToolbarPane);
        pageUIPane.setCenter(pageTitlesScrollPane);

        workspace.setLeft(componentEditToolbarPane);
        workspace.setCenter(pageEditorScrollPane);
        workspace.setRight(pageUIPane);
    }

    private void initEventHandlers() {
        fileController = new FileController(this, fileManager, siteExporter);
        newEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        loadEPortfolioButton.setOnAction(e -> {
            fileController.handleLoadEPortfolioRequest();
        });
        saveEPortfolioButton.setOnAction(e -> {
            fileController.handleSaveEPortfolioRequest(ePortfolio.getStudentName());
        });
        saveAsEPortfolioButton.setOnAction(e -> {
            TextInputDialog alert = new TextInputDialog();
            alert.setTitle("Conformation Dialog");
            alert.setHeaderText("Do you want to save the ePortfolio?");
            Optional<String> result = alert.showAndWait();
            if (result.isPresent()) {
                fileController.handleSaveEPortfolioRequest(result.get());
            }
        });
        exportEPortfolioButton.setOnAction(e -> {
            fileController.handleExportEPortfolioRequest();
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });
        pageEditorButton.setOnAction(e -> {
            ePane.setCenter(workspace);
        });
        pageViewerButton.setOnAction(e -> {

            //            EPortfolioViewer viewer = new EPortfolioViewer(this);
//            try {
//                viewer.startEPortfolio();
//            } catch (MalformedURLException ex) {
//                ErrorHandler eh = new ErrorHandler(this);
//                eh.processError();
//            } catch (URISyntaxException ex) {
//                ErrorHandler eh = new ErrorHandler(this);
//                eh.processError();
//            }
//            ePane.setCenter(viewer);
//            BorderPane dumb = new BorderPane();
//            WebView web = new WebView();
//            web.getEngine().load("https://www.google.com");
//            dumb.setCenter(web);
//            ePane.setCenter(dumb);
            try {
                BorderPane dumb = new BorderPane();
                // SETUP THE UI
                WebView webView = new WebView();
                ScrollPane scrollPane = new ScrollPane(webView);

                // GET THE URL
                String indexPath = "sites/" + ePortfolio.getStudentName() + "/" + INDEX_FILE;
                File indexFile = new File(indexPath);
                URL indexURL = indexFile.toURI().toURL();

                // SETUP THE WEB ENGINE AND LOAD THE URL
                webView.getEngine().load(indexURL.toString());
                webView.getEngine().setJavaScriptEnabled(true);
                dumb.setCenter(scrollPane);
                ePane.setCenter(dumb);
                
                scrollPane.setFitToHeight(true);
                scrollPane.setFitToWidth(true);
            } catch (MalformedURLException ex) {
                Logger.getLogger(EPortfolioMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        editController = new EPortfolioEditController(this);
        addPageButton.setOnAction(e -> {
            TextInputDialog alert = new TextInputDialog("Title");
            alert.setTitle("Conformation Dialog");
            alert.setHeaderText("Do you want to add a Page?");
            alert.setContentText("OK for adding page, cancel for displaying pages.");
            Optional<String> result = alert.showAndWait();
            if (result.isPresent()) {
                editController.handleAddPageRequest(result.get(), "'Montserrat', sans-serif;", "Footer", "Banner", "", "");
                reloadBannerControls();
            } else {
                reloadPagePane();
            }
        });
        bannerImageButton.setOnAction(e -> {
            pageTitlePane.getChildren().clear();
            editController.handleBannerImageRequest(ePortfolio.getSelectedPage());
        });
        layoutTemplateButton.setOnAction(e -> {
            pageTitlePane.getChildren().clear();
            editController.handleLayoutTemplateRequest();
        });
        colorTemplateButton.setOnAction(e -> {
            pageTitlePane.getChildren().clear();
            editController.handleColorTemplateRequest();
        });
        fontButton.setOnAction(e -> {
            pageTitlePane.getChildren().clear();
            editController.handleAddFontRequest();
        });

        addHeaderButton.setOnAction(e -> {
            editController.handleAddHeaderRequest();
        });
        addParagraphButton.setOnAction(e -> {
            editController.handleAddParagraphRequest();
        });
        addListButton.setOnAction(e -> {
            editController.handleAddListRequest();
        });
        addImageButton.setOnAction(e -> {
            editController.handleAddImageRequest();
        });
        addVideoButton.setOnAction(e -> {
            editController.handleAddVideoRequest();
        });
        addSlideshowButton.setOnAction(e -> {
            editController.handleAddSlideshowRequest();
        });

    }

    private void initFileToolbar() {
        ePortfolioToolbarPane = new BorderPane();
        fileToolbarPane = new FlowPane();
        modePane = new FlowPane();
        modePane.setAlignment(Pos.CENTER_RIGHT);
        ePortfolioToolbarPane.setLeft(fileToolbarPane);
        ePortfolioToolbarPane.setRight(modePane);

        newEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO, TOOLTIP_NEW_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_LOAD_EPORTFOLIO, TOOLTIP_LOAD_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_SAVE_EPORTFOLIO, TOOLTIP_SAVE_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        saveAsEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_SAVE_AS_EPORTFOLIO, TOOLTIP_SAVE_AS_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exportEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_EXPORT_EPORTFOLIO, TOOLTIP_EXPORT_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exitButton = this.initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

        pageEditorButton = this.initChildButton(modePane, ICON_PAGE_EDITOR, TOOLTIP_PAGE_EDITOR, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        pageViewerButton = this.initChildButton(modePane, ICON_PAGE_VIEWER, TOOLTIP_PAGE_VIEWER, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
    }

    private void initWindow(String windowTitle) {
        //set the title
        primaryStage.setTitle(windowTitle);

        //get the size of the screen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        //set up basic ui
        ePane = new BorderPane();

        ePane.setTop(ePortfolioToolbarPane);
        primaryScene = new Scene(ePane);
        primaryStage.setScene(primaryScene);
        initStyle();
        primaryStage.show();
    }

    private void initStyle() {
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        ePane.getStyleClass().add(CSS_CLASS_WORKSPACE);
        workspace.getStyleClass().add(CSS_CLASS_WORKSPACE);
        //TOP
        ePortfolioToolbarPane.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_PANE);
        //TOP LEFT
        componentEditToolbarPane.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);

        //CENTER
        pageEditorPane.getStyleClass().add(CSS_CLASS_COMPONENT_PANE);
        pageTitlePane.getStyleClass().add(CSS_CLASS_PAGE_PANE);
//        studentNameLabel.getStyleClass().add(CSS_CLASS_BANNER_TEXT);
//        studentNameTextField.getStyleClass().add(CSS_CLASS_BANNER_TEXT);
//        footerLabel.getStyleClass().add(CSS_CLASS_BANNER_TEXT);
//        footerTextField.getStyleClass().add(CSS_CLASS_BANNER_TEXT);

        //RIGHT TOP
        pageEditToolbarPane.getStyleClass().add(CSS_CLASS_PAGE_EDIT_TOOLBAR_PANE);
    }

    public Button initChildButton(Pane toolbar, String iconFileName, String tooltip, String cssClass, boolean disabled) {
        String iconPath = "file:" + PATH_ICONS + iconFileName;
        Image buttonImage = new Image(iconPath);
        Button button = new Button();
        button.getStyleClass().add(cssClass);
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(tooltip);
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    public void updateFileToolbarControls(boolean saved) {
        ePane.setCenter(workspace);
        saveEPortfolioButton.setDisable(saved);
        saveAsEPortfolioButton.setDisable(saved);
    }

    public void updatePageEditToolbarControls() {
        boolean pageSelected = ePortfolio.isPageSelected();

        exportEPortfolioButton.setDisable(!pageSelected);

        pageEditorButton.setDisable(!pageSelected);
        pageViewerButton.setDisable(!pageSelected);

        addHeaderButton.setDisable(!pageSelected);
        addParagraphButton.setDisable(!pageSelected);
        addListButton.setDisable(!pageSelected);
        addImageButton.setDisable(!pageSelected);
        addVideoButton.setDisable(!pageSelected);
        addSlideshowButton.setDisable(!pageSelected);

        bannerImageButton.setDisable(!pageSelected);
        layoutTemplateButton.setDisable(!pageSelected);
        colorTemplateButton.setDisable(!pageSelected);
        fontButton.setDisable(!pageSelected);

    }

    public void reloadComponentPane(Page page) {
        componentEditorPane.getChildren().clear();
        if (ePortfolio.getSelectedPage() != null) {
            reloadBannerControls();
            for (Component component : ePortfolio.getSelectedPage().getComponents()) {
                ComponentEditView componentEditor = new ComponentEditView(this, component);
                if (ePortfolio.getSelectedPage().isSelectedComponent(component)) {
                    componentEditor.getStyleClass().add(CSS_CLASS_SELECTED_COMPONENT);
                } else {
                    componentEditor.getStyleClass().add(CSS_CLASS_COMPONENT);
                }
                componentEditorPane.getChildren().add(componentEditor);
            }
        }
    }

    public void reloadPagePane() {
        pageTitlePane.getChildren().clear();
        for (Page page : ePortfolio.getPages()) {
            PageEditView pageEditView = new PageEditView(this, page);
            if (ePortfolio.isSelectedPage(page)) {
                pageEditView.getStyleClass().add(CSS_CLASS_SELECTED_PAGE);
            } else {
                pageEditView.getStyleClass().add(CSS_CLASS_PAGE);
            }
            pageTitlePane.getChildren().add(pageEditView);
            pageEditView.setOnMouseClicked(e -> {
                ePortfolio.setSelectedPage(page);
                this.reloadPagePane();
                reloadComponentPane(page);
            });
        }
        updatePageEditToolbarControls();
    }

    private void initBannerControls() {
        bannerPane = new VBox();

        studentNameLabel = new Label(LABEL_STUDENT_NAME);
        studentNameTextField = new TextField();
        studentNameTextField.setText(ePortfolio.getStudentName());

        bannerLabel = new Label(LABEL_BANNER);
        bannerTextField = new TextField("Banner");
        ePortfolio.getSelectedPage().setBanner(bannerTextField.getText());

        footerLabel = new Label(LABEL_FOOTER);
        footerTextField = new TextField(DEFAULT_FOOTER);
        ePortfolio.getSelectedPage().setFooter(footerTextField.getText());

        bannerPane.getChildren().addAll(studentNameLabel, studentNameTextField, bannerLabel, bannerTextField, footerLabel, footerTextField);

        studentNameTextField.textProperty().addListener(e -> {
            ePortfolio.setStudentName(studentNameTextField.getText());
            updateFileToolbarControls(false);
        });
        bannerTextField.textProperty().addListener(e -> {
            ePortfolio.getSelectedPage().setBanner(bannerTextField.getText());
            //bannerTextField.setText(ePortfolio.getSelectedPage().getBanner());
            updateFileToolbarControls(false);
        });
        footerTextField.textProperty().addListener(e -> {
            ePortfolio.getSelectedPage().setFooter(footerTextField.getText());
            //footerTextField.setText(ePortfolio.getSelectedPage().getFooter());
            updateFileToolbarControls(false);
        });
        pageEditorPane.getChildren().add(bannerPane);
        pageEditorPane.getChildren().add(componentEditorPane);
    }

    public void reloadBannerControls() {
        if (pageEditorPane.getChildren().size() == 0) {
            initBannerControls();
        } else {
            bannerTextField.setText(ePortfolio.getSelectedPage().getBanner());
            studentNameTextField.setText(ePortfolio.getStudentName());
            footerTextField.setText(ePortfolio.getSelectedPage().getFooter());
        }
    }

    public VBox getComponentPane() {
        return componentEditorPane;
    }

    public VBox getPagePane() {
        return pageTitlePane;
    }

}
