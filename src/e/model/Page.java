/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import static e.StartUpConstants.DEFAULT_PAGETITLE;
import e.view.EPortfolioMakerView;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author jieliang
 */
public class Page {

    EPortfolioMakerView ui;
    String pageTitle;
    String font;
    String banner;
    Image bannerImage;
    String footer;
    ObservableList<Component> components;
    Component selectedComponent;

    public Page(EPortfolioMakerView initUI) {
        ui = initUI;
        components = FXCollections.observableArrayList();
        reset();
    }

    //access methods
    public boolean isComponentSelected() {
        return selectedComponent != null;
    }

    public boolean isSelectedComponent(Component theComponent) {
        return selectedComponent == theComponent;
    }

    public ObservableList<Component> getComponents() {
        return components;
    }

    public Component getSelectedComponent() {
        return selectedComponent;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getFooter() {
        return footer;
    }

    public String banner() {
        return banner;
    }

    public Image getBannerImage() {
        return bannerImage;
    }
    
    public String getFont() {
        return font;
    }

    // mutator methods
    public void setSelectedComponent(Component initSelectedComponent) {
        selectedComponent = initSelectedComponent;
    }

    public void setPageTitle(String initPageTitle) {
        pageTitle = initPageTitle;
    }

    public void setBanner(String initBanner) {
        banner = initBanner;
    }

    public void setBannerImage(Image initBannerImage) {
        bannerImage = initBannerImage;
    }

    public void setFooter(String initFooter) {
        footer = initFooter;
    }
    
    public void setFont(String initFont) {
        font = initFont;
    }

    /*
     * Reset the Page to have no components and a default title
     */
    public void reset() {
        components.clear();
        pageTitle = DEFAULT_PAGETITLE;
        selectedComponent = null;
    }

//    public void addComponent(String initComponentType, ArrayList<String> initComponentContent, String initComponentPath, String initComponentWidth, String initComponentHeight, String initComponentFont_Float, ArrayList<String> initComponentCaption) {
//        Component componentToAdd = new Component(initComponentType, initComponentContent, initComponentPath, initComponentWidth, initComponentHeight, initComponentFont_Float, initComponentCaption);
//        components.add(componentToAdd);
//        ui.reloadComponentPane(this);
//    }
    
    public void addComponent(Component initComponent) {
        components.add(initComponent);
        ui.reloadComponentPane(this);
    }

    public void removeSelectedComponent() {
        if (isComponentSelected()) {
            components.remove(selectedComponent);
            selectedComponent = null;
            ui.reloadComponentPane(this);
        }
    }
}
