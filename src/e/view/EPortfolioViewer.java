/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import static e.file.EPortfolioSiteExporter.INDEX_FILE;
import e.model.EPortfolio;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author jieliang
 */
public class EPortfolioViewer extends BorderPane {

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

    public void startEPortfolio() throws MalformedURLException, URISyntaxException {
        // SETUP THE UI
        webView = new WebView();
        scrollPane = new ScrollPane(webView);

        // GET THE URL
        String indexPath = "sites/" + parentView.getEPortfolio().getStudentName() + "/" + INDEX_FILE;
        File indexFile = new File(indexPath);
        URL indexURL = indexFile.toURI().toURL();

        // SETUP THE WEB ENGINE AND LOAD THE URL
        webEngine = webView.getEngine();
        webEngine.load(indexURL.toString());
        webEngine.setJavaScriptEnabled(true);
    }
}
