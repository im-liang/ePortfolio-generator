/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import e.controller.FileController;
import e.controller.SlideShowController;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.model.EPortfolio;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newEPortfolioButton;
    Button loadEPortfolioButton;
    Button saveEPortfolioButton;
    Button saveAsEPortfolioButton;
    Button exportEPortfolioButton;
    Button exitButton;
    
        // WORKSPACE
    BorderPane workspace;
    
        // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane siteToolbarPane;
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;
    
    // FOR THE PAGE TITLE
    FlowPane titlePane;
    TextField titleTextField;

    ScrollPane pageEditorScrollPane;
    VBox pageEditorPane;

    EPortfolio ePortfolio;

    EPortfolioFileManager fileManager;

    EPortfolioSiteExporter siteExporter;

    private ErrorHandler errorHandler;

    private FileController fileController;

    private SlideShowController editController;
    
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
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    private void initWorkspace() {
        workspace = new BorderPane();
    }
}
