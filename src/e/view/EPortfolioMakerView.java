/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_SLIDES_EDITOR_PANE;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_WORKSPACE;
import static e.StartUpConstants.ICON_ADD_IMAGE;
import static e.StartUpConstants.ICON_ADD_LINK;
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
import static e.StartUpConstants.ICON_SAVE_AS_EPORTFOLIO;
import static e.StartUpConstants.ICON_SAVE_EPORTFOLIO;
import static e.StartUpConstants.LABEL_FOOTER;
import static e.StartUpConstants.LABEL_PAGE_TITLE;
import static e.StartUpConstants.LABEL_STUDENT_NAME;
import static e.StartUpConstants.PATH_ICONS;
import static e.StartUpConstants.STYLE_SHEET_UI;
import static e.ToolTip.DEFAULT_FOOTER;
import static e.ToolTip.DEFAULT_PAGE_TITLE;
import static e.ToolTip.DEFAULT_STUDENT_NAME;
import static e.ToolTip.TOOLTIP_ADD_IMAGE;
import static e.ToolTip.TOOLTIP_ADD_PAGE;
import static e.ToolTip.TOOLTIP_ADD_SLIDESHOW;
import static e.ToolTip.TOOLTIP_ADD_TEXT;
import static e.ToolTip.TOOLTIP_ADD_TEXT_HYPERLINK;
import static e.ToolTip.TOOLTIP_ADD_VIDEO;
import static e.ToolTip.TOOLTIP_BANNER_IMAGE;
import static e.ToolTip.TOOLTIP_COLOR_TEMPLATE;
import static e.ToolTip.TOOLTIP_EXIT;
import static e.ToolTip.TOOLTIP_EXPORT_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_LAYOUT_TEMPLATE;
import static e.ToolTip.TOOLTIP_LOAD_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_NEW_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_SAVE_AS_EPORTFOLIO;
import static e.ToolTip.TOOLTIP_SAVE_EPORTFOLIO;
import e.controller.FileController;
import e.controller.SlideShowController;
import e.error.ErrorHandler;
import e.file.EPortfolioFileManager;
import e.file.EPortfolioSiteExporter;
import e.model.EPortfolio;
import e.model.Page;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    // THIS IS THE TOP LEFT TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newEPortfolioButton;
    Button loadEPortfolioButton;
    Button saveEPortfolioButton;
    Button saveAsEPortfolioButton;
    Button exportEPortfolioButton;
    Button exitButton;

    // WORKSPACE
    BorderPane workspace;

    //right
    TabPane tabPane;
    Tab pagesEditorTab;
    Tab pagesViewerTab;
    Button addPageButton;
    Button removePageButton;
    Button bannerImageButton;
    Button layoutTemplateButton;
    Button colorTemplateButton;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane pageEditToolbar;
    Button addTextButton;
    Button addImageButton;
    Button addVideoButton;
    Button addSlideshowButton;
    Button addHyperlinkButton;

    // FOR THE PAGE TITLE
    FlowPane bannerPane;
    Label titleLabel;
    TextField titleTextField;
    Label studentNameLabel;
    TextField studentNameTextField;
    Label footerLabel;
    TextField footerTextField;

    //Center
    ScrollPane pageEditorScrollPane;
    VBox pageEditorPane;

    //current working eportfolio
    EPortfolio ePortfolio;

    //save and load eportfolio
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
        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    private void initWorkspace() {
        workspace = new BorderPane();
        //left side of the screen
        pageEditToolbar = new FlowPane();
        addTextButton = this.initChildButton(pageEditToolbar, ICON_ADD_TEXT, TOOLTIP_ADD_TEXT, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addImageButton = this.initChildButton(pageEditToolbar, ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addSlideshowButton = this.initChildButton(pageEditToolbar, ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SLIDESHOW, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addVideoButton = this.initChildButton(pageEditToolbar, ICON_ADD_VIDEO, TOOLTIP_ADD_VIDEO, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addHyperlinkButton = this.initChildButton(pageEditToolbar, ICON_ADD_LINK, TOOLTIP_ADD_TEXT_HYPERLINK, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        //center
        pageEditorPane = new VBox();
        pageEditorScrollPane = new ScrollPane(pageEditorPane);
        pageEditorScrollPane.setFitToWidth(true);
        pageEditorScrollPane.setFitToHeight(true);
        initBannerControls();

        //right
        initTabControls();

        workspace.setLeft(pageEditToolbar);
        workspace.setCenter(pageEditorScrollPane);
        workspace.setRight(tabPane);

        // SETUP ALL THE STYLE CLASSES
        workspace.getStyleClass().add(CSS_CLASS_WORKSPACE);
        pageEditToolbar.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);
        pageEditorPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);
        pageEditorScrollPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);

    }

    private void initEventHandlers() {
        fileController = new FileController(this, fileManager, siteExporter);
        newEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        loadEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        saveEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        saveAsEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        exportEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        exitButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
    }

    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();
        fileToolbarPane.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_PANE);
        newEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO, TOOLTIP_NEW_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_LOAD_EPORTFOLIO, TOOLTIP_LOAD_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_SAVE_EPORTFOLIO, TOOLTIP_SAVE_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveAsEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_SAVE_AS_EPORTFOLIO, TOOLTIP_SAVE_AS_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exportEPortfolioButton = this.initChildButton(fileToolbarPane, ICON_EXPORT_EPORTFOLIO, TOOLTIP_EXPORT_EPORTFOLIO, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exitButton = this.initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
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
        ePane.getStyleClass().add(CSS_CLASS_WORKSPACE);
        ePane.setTop(fileToolbarPane);
        ePane.setCenter(workspace);
        primaryScene = new Scene(ePane);

        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
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
        updateEPortfolioEditToolbarControls();
    }
    public void updateEPortfolioEditToolbarControls() {
        addPageButton.setDisable(false);
        boolean pageSelected = ePortfolio.isPageSelected();
        removePageButton.setDisable(!pageSelected);
    }

    public void reloadEPortfolioPane() {
        pageEditorPane.getChildren().clear();
        reloadBannerControls();
        for(Page page : ePortfolio.getPages()) {
            PageEditView pageEditor = new PageEditView(this, page);
            if(ePortfolio.isSelectedPage(page))
                
        }
    }
    private void initBannerControls() {
        bannerPane = new FlowPane();
        titleLabel = new Label(LABEL_PAGE_TITLE);
        titleTextField = new TextField();
        titleTextField.setText(DEFAULT_PAGE_TITLE);

        studentNameLabel = new Label(LABEL_STUDENT_NAME);
        studentNameTextField = new TextField();
        studentNameTextField.setText(DEFAULT_STUDENT_NAME);

        footerLabel = new Label(LABEL_FOOTER);
        footerTextField = new TextField();
        footerTextField.setText(DEFAULT_FOOTER);

        bannerPane.getChildren().addAll(titleLabel, titleTextField, studentNameLabel, studentNameTextField, footerLabel, footerTextField);

        titleTextField.textProperty().addListener(e -> {
            //ePortfolio.;
            updateFileToolbarControls(false);
        });
        studentNameTextField.textProperty().addListener(e -> {
            ePortfolio.setStudentName(studentNameTextField.getText());
            updateFileToolbarControls(false);
        });
        titleTextField.textProperty().addListener(e -> {
            //ePortfolio.getSelectedPage();
            updateFileToolbarControls(false);
        });
    }

    public void reloadBannerControls() {
        //titleTextField.setText(ePortfolio.getSelectedPage().);
        studentNameTextField.setText(ePortfolio.getStudentName());
        //footerTextField.setText(ePortfolio.getSelectedPage().);
    }

    private void initTabControls() {
        tabPane = new TabPane();
        pagesEditorTab = new Tab();
        pagesEditorTab.setText("Editor");
        pagesEditorTab.setClosable(false);
        pagesViewerTab = new Tab();
        pagesViewerTab.setText("Viewer");
        pagesViewerTab.setClosable(false);

        BorderPane contentPane = new BorderPane();
        pagesEditorTab.setContent(contentPane);
        HBox hbox = new HBox();
        addPageButton = this.initChildButton(hbox, ICON_ADD_PAGE, TOOLTIP_ADD_PAGE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        bannerImageButton = this.initChildButton(hbox, ICON_BANNER_IMAGE, TOOLTIP_BANNER_IMAGE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        layoutTemplateButton = this.initChildButton(hbox, ICON_LAYOUT_TEMPLATE, TOOLTIP_LAYOUT_TEMPLATE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        colorTemplateButton = this.initChildButton(hbox, ICON_COLOR_TEMPLATE, TOOLTIP_COLOR_TEMPLATE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        
        VBox pageTitlePane = new VBox();
        ScrollPane pageTitlesScrollPane = new ScrollPane(pageTitlePane);
        
        contentPane.setTop(hbox);
        contentPane.setCenter(pageTitlesScrollPane);
        tabPane.getTabs().addAll(pagesEditorTab, pagesViewerTab);
    }
}
