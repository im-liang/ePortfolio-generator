/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import e.controller.ImageController;
import e.model.Component;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jieliang
 */
public class ComponentEditView extends HBox {
    EPortfolioMakerView ui;
    
    Component component;
    
    ImageView selectionView;
    
    VBox contentVBox;
    Label contentLabel;
    TextField contentTextField;
    
    ImageController imageController;
    /*
    * This constructor initializes the full UI for this component, using ininComponent data for initializing values.
    */
    public ComponentEditView(EPortfolioMakerView initUI, Component initComponent) {
        ui = initUI;
        component = initComponent;
        selectionView = new ImageView();
        updateComponentImage();
        
        contentVBox = new VBox();
        
    }
    
    public void updateComponentImage() {
        
    }
}
