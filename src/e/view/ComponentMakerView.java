/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_COMPONENT;
import static e.StartUpConstants.CSS_CLASS_COMPONENT_LABEL;
import static e.StartUpConstants.CSS_CLASS_COMPONENT_TEXTFIELD;
import static e.StartUpConstants.CSS_CLASS_SELECTED_COMPONENT;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_VERTICAL_TOOLBAR_PANE;
import static e.StartUpConstants.DEFAULT_BANNER;
import static e.StartUpConstants.DEFAULT_FOOTER;
import static e.StartUpConstants.DEFAULT_STUDENTNAME;
import static e.StartUpConstants.ICON_ADD_HEADING;
import static e.StartUpConstants.ICON_ADD_IMAGE;
import static e.StartUpConstants.ICON_ADD_LIST;
import static e.StartUpConstants.ICON_ADD_P;
import static e.StartUpConstants.ICON_ADD_SLIDESHOW;
import static e.StartUpConstants.ICON_ADD_VIDEO;
import static e.StartUpConstants.LABEL_BANNER;
import static e.StartUpConstants.LABEL_FOOTER;
import static e.StartUpConstants.LABEL_STUDENT_NAME;
import static e.ToolTip.TOOLTIP_ADD_HEADING;
import static e.ToolTip.TOOLTIP_ADD_IMAGE;
import static e.ToolTip.TOOLTIP_ADD_LIST;
import static e.ToolTip.TOOLTIP_ADD_P;
import static e.ToolTip.TOOLTIP_ADD_SLIDESHOW;
import static e.ToolTip.TOOLTIP_ADD_VIDEO;
import e.controller.EditComponentController;
import e.dialog.HeaderDialog;
import e.dialog.ImageDialog;
import e.dialog.ListDialog;
import e.dialog.ParagraphDialog;
import e.dialog.SlideshowDialog;
import e.dialog.VideoDialog;
import e.model.Component;
import e.model.Page;
import static e.view.EPortfolioMakerView.initChildButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class ComponentMakerView {

    BorderPane componentBorderPane;
    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane componentEditToolbarPane;
    Button addHeaderButton;
    Button addParagraphButton;
    Button addListButton;
    Button addImageButton;
    Button addVideoButton;
    Button addSlideshowButton;

    //top center
    VBox currentEditingPageVBox;
    HBox nameHBox, bannerHBox, footerHBox;
    Label nameLabel;
    TextField nameTextField;
    Label bannerLabel;
    TextField bannerTextField;
    Label footerLabel;
    TextField footerTextField;
    //Center
    ScrollPane componentEditorScrollPane;
    VBox componentEditorVBox;

    EditComponentController editComponentController;

    EPortfolioMakerView ui;

    public ComponentMakerView(EPortfolioMakerView initEPortfolioMakerView) {
        ui = initEPortfolioMakerView;
        initComponentSpace();
        initStyle();
        initEventHandler();
    }

    private void initComponentSpace() {
        //left side of the screen
        componentEditToolbarPane = new FlowPane();
        addHeaderButton = initChildButton(componentEditToolbarPane, ICON_ADD_HEADING, TOOLTIP_ADD_HEADING, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addParagraphButton = initChildButton(componentEditToolbarPane, ICON_ADD_P, TOOLTIP_ADD_P, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addListButton = initChildButton(componentEditToolbarPane, ICON_ADD_LIST, TOOLTIP_ADD_LIST, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addImageButton = initChildButton(componentEditToolbarPane, ICON_ADD_IMAGE, TOOLTIP_ADD_IMAGE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addSlideshowButton = initChildButton(componentEditToolbarPane, ICON_ADD_SLIDESHOW, TOOLTIP_ADD_SLIDESHOW, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        addVideoButton = initChildButton(componentEditToolbarPane, ICON_ADD_VIDEO, TOOLTIP_ADD_VIDEO, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);

        //top center
        currentEditingPageVBox = new VBox();

        nameHBox = new HBox();
        nameLabel = new Label(LABEL_STUDENT_NAME);
        nameTextField = new TextField(DEFAULT_STUDENTNAME);
        nameHBox.setHgrow(nameTextField, Priority.ALWAYS);
        nameHBox.getChildren().addAll(nameLabel, nameTextField);

        bannerHBox = new HBox();
        bannerLabel = new Label(LABEL_BANNER);
        bannerTextField = new TextField(DEFAULT_BANNER);
        bannerHBox.setHgrow(bannerTextField, Priority.ALWAYS);
        bannerHBox.getChildren().addAll(bannerLabel, bannerTextField);

        footerHBox = new HBox();
        footerLabel = new Label(LABEL_FOOTER);
        footerTextField = new TextField(DEFAULT_FOOTER);
        footerHBox.setHgrow(footerTextField, Priority.ALWAYS);
        footerHBox.getChildren().addAll(footerLabel, footerTextField);

        //center
        componentEditorVBox = new VBox();
        componentEditorScrollPane = new ScrollPane(componentEditorVBox);

        currentEditingPageVBox.getChildren().addAll(nameHBox, bannerHBox, footerHBox, componentEditorScrollPane);

        componentBorderPane = new BorderPane();
        componentBorderPane.setLeft(componentEditToolbarPane);
        componentBorderPane.setCenter(currentEditingPageVBox);

        nameTextField.textProperty().addListener(e-> {
            String studentName = nameTextField.getText();
            ui.getEPortfolio().setStudentName(studentName);
            ui.updateFileToolbarControls(false);
        });
        bannerTextField.textProperty().addListener(e -> {
            String banner = bannerTextField.getText();
            ui.getEPortfolio().getSelectedPage().setBanner(banner);
            ui.updateFileToolbarControls(false);
        });
        footerTextField.textProperty().addListener(e -> {
            String footer = footerTextField.getText();
            ui.getEPortfolio().getSelectedPage().setFooter(footer);
            ui.updateFileToolbarControls(false);
        });
    }

    private void initStyle() {
        componentEditToolbarPane.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);
        nameLabel.getStyleClass().add(CSS_CLASS_COMPONENT_LABEL);
        bannerLabel.getStyleClass().add(CSS_CLASS_COMPONENT_LABEL);
        footerLabel.getStyleClass().add(CSS_CLASS_COMPONENT_LABEL);
        nameTextField.getStyleClass().add(CSS_CLASS_COMPONENT_TEXTFIELD);
        bannerTextField.getStyleClass().add(CSS_CLASS_COMPONENT_TEXTFIELD);
        footerTextField.getStyleClass().add(CSS_CLASS_COMPONENT_TEXTFIELD);

//        componentEditorScrollPane.getStyleClass().add("component_editor_pane");
    }

    private void initEventHandler() {
        editComponentController = new EditComponentController(ui);
        addHeaderButton.setOnAction(e -> {
            editComponentController.handleAddHeaderRequest();
        });
        addImageButton.setOnAction(e -> {
            editComponentController.handleAddImageRequest();
        });
        addListButton.setOnAction(e -> {
            editComponentController.handleAddListRequest();
        });
        addParagraphButton.setOnAction(e -> {
            editComponentController.handleAddParagraphRequest();
        });
        addSlideshowButton.setOnAction(e -> {
            editComponentController.handleAddSlideshowRequest();
        });
        addVideoButton.setOnAction(e -> {
            editComponentController.handleAddVideoRequest();
        });
    }

    public BorderPane getComponentPane() {
        return componentBorderPane;
    }

    public VBox getCurrentEditingPageVBox() {
        return currentEditingPageVBox;
    }
    public VBox getComponentDialogVBox() {
        return componentEditorVBox;
    }

    public void reloadComponentPane(Page page) {
        componentEditorVBox.getChildren().clear();
        for (Component component : page.getComponents()) {
            ComponentEditView componentEditView = new ComponentEditView(ui, component);
            if (page.isSelectedComponent(component)) {
                componentEditView.getStyleClass().add(CSS_CLASS_SELECTED_COMPONENT);
            } else {
                componentEditView.getStyleClass().add(CSS_CLASS_COMPONENT);
            }
            componentEditorVBox.getChildren().add(componentEditView);
            componentEditView.setOnMouseClicked(e -> {
                page.setSelectedComponent(component);
                String componentType = component.getComponentType();
                if (e.getClickCount() == 2) {
                    switch (componentType) {
                        case "header":
                            HeaderDialog headerDialog = new HeaderDialog(ui.getWindow(), component);
                            headerDialog.show(componentType);
                            break;
                        case "paragraph":
                            ParagraphDialog pDialog = new ParagraphDialog(ui.getWindow());
                            pDialog.show(componentType);
                            break;
                        case "list":
                            ListDialog listDialog = new ListDialog(ui.getWindow());
                            listDialog.show(componentType);
                            break;
                        case "image":
                            ImageDialog imageDialog = new ImageDialog(ui.getWindow());
                            imageDialog.show(componentType);
                            break;
                        case "video":
                            VideoDialog videoDialog = new VideoDialog(ui.getWindow());
                            videoDialog.show(componentType);
                            break;
                        case "slideshow":
                            SlideshowDialog slideshowDialog = new SlideshowDialog(ui.getWindow());
                            slideshowDialog.show(componentType);
                            break;
                    }
                }
                this.updateComponentToolbarControls(true);
                this.reloadComponentPane(page);
            });
        }
    }

    public void updateComponentToolbarControls(boolean edited) {
        addHeaderButton.setDisable(!edited);
        addImageButton.setDisable(!edited);
        addListButton.setDisable(!edited);
        addParagraphButton.setDisable(!edited);
        addSlideshowButton.setDisable(!edited);
        addVideoButton.setDisable(!edited);
    }
    
    public void updateHeader() {
        bannerTextField.setText(ui.getEPortfolio().getSelectedPage().getBanner());
        footerTextField.setText(ui.getEPortfolio().getSelectedPage().getFooter());
    }
}
