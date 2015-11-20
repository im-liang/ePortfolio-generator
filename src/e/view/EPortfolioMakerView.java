/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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

    private FIleController fileController;

    private SlideShowEditController editController;
    
    public EPortfolio(EPortfolioFileManager initFileManager, EPortfolioSiteExporter initSiteExporter) {
        fileManager = initFileManager;
        siteExporter = initSiteExporter;
        ePortfolio = new EPortfolio(this);
        errorHandler = new ErrorHandler(this);
    }
    
    public 
}
