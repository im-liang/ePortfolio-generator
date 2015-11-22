/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import static e.StartUpConstants.DEFAULT_PAGETITLE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jieliang
 */
public class Page {

    String pageTitle;
    ObservableList<Component> components;
    Component selectedComponent;

    public Page() {
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
    
    public void addComponent() {
        Component componentToAdd = new Component();
        components.add(componentToAdd);
    }
    
    public void removeSelectedComponent() {
        if(isComponentSelected()) {
            components.remove(selectedComponent);
            selectedComponent = null;
            ui.reloadComponentPane();
        }
    }
}
