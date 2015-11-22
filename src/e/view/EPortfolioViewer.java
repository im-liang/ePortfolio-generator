/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import e.model.EPortfolio;
import java.net.MalformedURLException;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author jieliang
 */
public class EPortfolioViewer extends Stage {
    // main ui
    EPortfolioMakerView parentView;
    //data for eportfolio
    EPortfolio pages;
    //controls
    ScrollPane scrollPane;
    WebView webView;
    WebEngine webEngine;
    /*
    * initializes pages
    */
    public EPortfolioViewer(EPortfolioMakerView initParentView) {
        parentView = initParentView;
        pages = parentView.getEPortfolio();
    }
    /*
    * initializes the UI and open the window with the first page displayed
    */
    public void startEPortfolio() throws MalformedURLException {
        //set up the UI
        webView = new WebView();
        scrollPane = new ScrollPane(webView);
        
        //get the url
        String indexPath = 
    }
}
