/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_PANE;
import static e.StartUpConstants.ICON_EXIT;
import static e.StartUpConstants.ICON_EXPORT_EPORTFOLIO;
import static e.StartUpConstants.ICON_LOAD_EPORTFOLIO;
import static e.StartUpConstants.ICON_NEW_EPORTFOLIO;
import static e.StartUpConstants.ICON_PAGE_EDITOR;
import static e.StartUpConstants.ICON_PAGE_VIEWER;
import static e.StartUpConstants.ICON_SAVE_AS_EPORTFOLIO;
import static e.StartUpConstants.ICON_SAVE_EPORTFOLIO;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.TOOLTIP_EXIT;
import static e.ToolTip.TOOLTIP_EXPORT_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_LOAD_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_NEW_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_PAGE_EDITOR;
import static e.ToolTip.TOOLTIP_PAGE_VIEWER;
import static e.ToolTip.TOOLTIP_SAVE_AS_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_SAVE_EPORTFOLIO;
import e.controller.FileController;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.model.EPortfolio;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
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

    //Left side of the workspace
    ComponentMakerView componentMakerView;

    //Right side of the workspace 
    PageMakerView pageMakerView;

    //save and load eportfolio
    EPortfolioFileManager fileManager;

    EPortfolioSiteExporter siteExporter;

    // CURRENT WORKING EPORTFOLIO
    EPortfolio ePortfolio;

    FileController fileController;

    ErrorHandler errorHandler;

    public EPortfolioMakerView(EPortfolioFileManager initFileManager, EPortfolioSiteExporter initSiteExporter) {
        fileManager = initFileManager;
        siteExporter = initSiteExporter;
        errorHandler = new ErrorHandler(this);
        ePortfolio = new EPortfolio(this);
    }

    public static Button initChildButton(Pane toolbar, String iconFileName, String tooltip, String cssClass, boolean disabled) {
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

    private void initStyle() {
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        //TOP
        ePortfolioToolbarPane.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_PANE);
    }

    private void initFileToolbar() {
        ePortfolioToolbarPane = new BorderPane();
        fileToolbarPane = new FlowPane();
        modePane = new FlowPane();
        modePane.setAlignment(Pos.CENTER_RIGHT);
        ePortfolioToolbarPane.setLeft(fileToolbarPane);
        ePortfolioToolbarPane.setRight(modePane);

        newEPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO, TOOLTIP_NEW_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadEPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_EPORTFOLIO, TOOLTIP_LOAD_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveEPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_EPORTFOLIO, TOOLTIP_SAVE_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        saveAsEPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_EPORTFOLIO, TOOLTIP_SAVE_AS_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exportEPortfolioButton = initChildButton(fileToolbarPane, ICON_EXPORT_EPORTFOLIO, TOOLTIP_EXPORT_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

        pageEditorButton = initChildButton(modePane, ICON_PAGE_EDITOR, TOOLTIP_PAGE_EDITOR, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        pageViewerButton = initChildButton(modePane, ICON_PAGE_VIEWER, TOOLTIP_PAGE_VIEWER, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
    }

    private void initWorkspace() {
        workspace = new BorderPane();
        componentMakerView = new ComponentMakerView(this);
        pageMakerView = new PageMakerView(ePortfolio, this);
        workspace.setCenter(componentMakerView.getComponentPane());
        workspace.setRight(pageMakerView.getPageEditVBox());
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

    private void initEventHandlers() {
        fileController = new FileController(this, fileManager, siteExporter);
        newEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
            ePane.setCenter(workspace);
        });
        loadEPortfolioButton.setOnAction(e -> {
            fileController.handleLoadEPortfolioRequest();
            ePane.setCenter(workspace);
        });
        saveEPortfolioButton.setOnAction(e -> {
            fileController.handleSaveEPortfolioRequest(ePortfolio.getStudentName());
        });
        saveAsEPortfolioButton.setOnAction(e -> {
            fileController.handleSaveAsEPortfolioRequest();
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
            fileController.handleVieWPageRequest(ePane);
        });
    }

    public void startUI(Stage initPrimaryStage, String windowTitle) {
        initFileToolbar();
        initWorkspace();
        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    public void updateFileToolbarControls(boolean saved) {
        saveEPortfolioButton.setDisable(saved);
        saveAsEPortfolioButton.setDisable(saved);
        exportEPortfolioButton.setDisable(!saved);
        pageEditorButton.setDisable(true);
        pageViewerButton.setDisable(true);
    }

    public void updatePageModeControls(boolean edit) {
        pageEditorButton.setDisable(!edit);
        pageViewerButton.setDisable(!edit);
    }

    public EPortfolio getEPortfolio() {
        return ePortfolio;
    }

    public void reloadEPortfolioPane() {
        pageMakerView.reloadPagePane();
        if (ePortfolio.isPageSelected()) {
            componentMakerView.reloadComponentPane(ePortfolio.getSelectedPage());
            pageMakerView.updatePageEditToolbarControls(true);
            componentMakerView.updateComponentToolbarControls(true);
        } else {
            pageMakerView.updatePageEditToolbarControls(false);
            componentMakerView.updateComponentToolbarControls(false);
        }
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public Stage getWindow() {
        return primaryStage;
    }

    public VBox getPageVBox() {
        return pageMakerView.getPageTitlesVBox();
    }

    public VBox getCompoentnVBox() {
        return componentMakerView.getComponentDialogVBox();
    }
}
