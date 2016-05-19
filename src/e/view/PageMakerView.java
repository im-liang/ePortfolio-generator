/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.StartUpConstants.CSS_CLASS_PAGE;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_PANE;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_REMOVE_BUTTON;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_SCROLLPANE;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TITLES_VBOX;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON;
import static e.StartUpConstants.CSS_CLASS_PAGE_EDIT_TOOLBAR_PANE;
import static e.StartUpConstants.CSS_CLASS_SELECTED_PAGE;
import static e.StartUpConstants.ICON_ADD_PAGE;
import static e.StartUpConstants.ICON_BANNER_IMAGE;
import static e.StartUpConstants.ICON_COLOR_TEMPLATE;
import static e.StartUpConstants.ICON_FONT;
import static e.StartUpConstants.ICON_LAYOUT_TEMPLATE;
import static e.ToolTip.TOOLTIP_ADD_PAGE;
import static e.ToolTip.TOOLTIP_BANNER_IMAGE;
import static e.ToolTip.TOOLTIP_COLOR_TEMPLATE;
import static e.ToolTip.TOOLTIP_FONT;
import static e.ToolTip.TOOLTIP_LAYOUT_TEMPLATE;
import e.controller.EditPageController;
import e.model.EPortfolio;
import e.model.Page;
import static e.view.EPortfolioMakerView.initChildButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class PageMakerView {

    VBox pageMainVBox;
    TabPane pageFeatureTabPane;
    Tab pageTitleTab;
    Tab pageSettingTab;
    ScrollPane pageTitlesScrollPane;
    VBox pageTitlesVBox;
    HBox pageEditToolbarFlowPane;
    Button addPageButton;
    Button removePageButton;
    Button bannerImageButton;
    Button layoutTemplateButton;
    Button colorTemplateButton;
    Button fontButton;

    EditPageController editPageController;

    EPortfolio ePortfolio;

    EPortfolioMakerView ui;

    public PageMakerView(EPortfolio initEPortfolio, EPortfolioMakerView initUI) {
        ePortfolio = initEPortfolio;
        ui = initUI;
        initPageSpace();
        initStyle();
        initEventHandler();
    }

    private void initPageSpace() {
        pageEditToolbarFlowPane = new HBox(5);
        pageTitlesVBox = new VBox(5);
        
        Label x = new Label("s");
        pageTitlesVBox.getChildren().add(x);
        
        addPageButton = initChildButton(pageTitlesVBox, ICON_ADD_PAGE, TOOLTIP_ADD_PAGE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, false);
        bannerImageButton = initChildButton(pageEditToolbarFlowPane, ICON_BANNER_IMAGE, TOOLTIP_BANNER_IMAGE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        layoutTemplateButton = initChildButton(pageEditToolbarFlowPane, ICON_LAYOUT_TEMPLATE, TOOLTIP_LAYOUT_TEMPLATE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        colorTemplateButton = initChildButton(pageEditToolbarFlowPane, ICON_COLOR_TEMPLATE, TOOLTIP_COLOR_TEMPLATE, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);
        fontButton = initChildButton(pageEditToolbarFlowPane, ICON_FONT, TOOLTIP_FONT, CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON, true);

        pageTitlesScrollPane = new ScrollPane(pageTitlesVBox);
        
        pageFeatureTabPane = new TabPane();
        pageTitleTab = new Tab();
        pageTitleTab.setText("Title");
        pageSettingTab = new Tab();
        pageSettingTab.setText("Setting");
        pageFeatureTabPane.getTabs().addAll(pageTitleTab, pageSettingTab);
        
        pageMainVBox = new VBox();
        
        pageTitleTab.setContent(pageTitlesScrollPane);
        pageSettingTab.setContent(pageEditToolbarFlowPane);
        pageTitleTab.setClosable(false);
        pageSettingTab.setClosable(false);
//        pageMainVBox.getChildren().addAll(pageEditToolbarFlowPane, pageTitlesScrollPane);
        pageMainVBox.getChildren().add(pageFeatureTabPane);
    }

    private void initStyle() {
        pageEditToolbarFlowPane.setPrefWidth(260);
        pageMainVBox.getStyleClass().add(CSS_CLASS_PAGE_EDIT_PANE);
        pageEditToolbarFlowPane.getStyleClass().add(CSS_CLASS_PAGE_EDIT_TOOLBAR_PANE);
        pageTitlesScrollPane.getStyleClass().add(CSS_CLASS_PAGE_EDIT_SCROLLPANE);
        pageTitlesVBox.getStyleClass().add(CSS_CLASS_PAGE_EDIT_TITLES_VBOX);
        
    }

    private void initEventHandler() {
        editPageController = new EditPageController(ePortfolio, ui);
        addPageButton.setOnAction(e -> {
            editPageController.handleAddPageRequest();
        });
        bannerImageButton.setOnAction(e -> {
            editPageController.handleChooseBannerImageRequest();
        });
        layoutTemplateButton.setOnAction(e -> {
            editPageController.handleChooseLayoutTemplateRequest();
        });
        colorTemplateButton.setOnAction(e -> {
            editPageController.handleChooseColorTemplateRequest();
        });
        fontButton.setOnAction(e -> {
            editPageController.handleChooseFontRequest();
        });
    }

    public VBox getPageMainVBox() {
        return pageMainVBox;
    }

    public VBox getPageTitlesVBox() {
        return pageTitlesVBox;
    }

    public void reloadPagePane() {
        if (!ui.getEPortfolio().isPageSelected()) {
            ui.componentMakerView.getComponentPane().setVisible(false);
        } else {
            ui.componentMakerView.getComponentPane().setVisible(true);
        }
        pageTitlesVBox.getChildren().clear();
        for (Page page : ePortfolio.getPages()) {
            PageEditView pageEditView = new PageEditView(ui, page);
            if (ePortfolio.isSelectedPage(page)) {
                pageEditView.getStyleClass().add(CSS_CLASS_SELECTED_PAGE);
                Button removeButton = new Button("X");
                pageEditView.getChildren().add(0, removeButton);
                removeButton.setOnAction(e -> {
                    ePortfolio.removeSelectedPage();
                    reloadPagePane();
                });
                removeButton.getStyleClass().add(CSS_CLASS_PAGE_EDIT_REMOVE_BUTTON);
            } else {
                pageEditView.getStyleClass().add(CSS_CLASS_PAGE);
            }
            ui.componentMakerView.updateHeader();
            pageTitlesVBox.getChildren().add(pageEditView);
            pageEditView.setOnMouseClicked(e -> {
                ePortfolio.setSelectedPage(page);
                this.reloadPagePane();
            });
        }
    }

    public void updatePageEditToolbarControls(boolean edited) {
        bannerImageButton.setDisable(!edited);
        colorTemplateButton.setDisable(!edited);
        layoutTemplateButton.setDisable(!edited);
        fontButton.setDisable(!edited);
    }
}
