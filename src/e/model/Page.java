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

/**
 *
 * @author jieliang
 */
public class Page {

    EPortfolioMakerView ui;
    String pageTitle;
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

    // mutator methods
    public void setSelectedComponent(Component initSelectedComponent) {
        selectedComponent = initSelectedComponent;
    }

    public void setPageTitle(String initPageTitle) {
        pageTitle = initPageTitle;
    }

    /*
    * Reset the Page to have no components and a default title
    */
    public void reset() {
        components.clear();
        pageTitle = DEFAULT_PAGETITLE;
        selectedComponent = null;
    }
    
    public void addComponent(String initComponentFileName, ArrayList<String> initComponentPath, String initComponentType, String initComponentFont, ArrayList<String> initComponentDesiredText) {
        Component componentToAdd = new Component(initComponentFileName, initComponentPath, initComponentType, initComponentFont, initComponentDesiredText);
        components.add(componentToAdd);
        ui.reloadComponentPane();
    }
    
    public void removeSelectedComponent() {
        if(isComponentSelected()) {
            components.remove(selectedComponent);
            selectedComponent = null;
            ui.reloadComponentPane();
        }
    }
}
