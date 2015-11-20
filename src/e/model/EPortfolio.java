/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import static e.StartUpConstants.DEFAULT_STUDENTNAME;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jieliang
 */
public class EPortfolio {
    EPortfolioMakerView ui;
    String studentName;
    ObservableList<Page> pages;
    Page selectedPage;

    public EPortfolio() {
        pages = FXCollections.observableArrayList();
    }

    public boolean isPageSelected() {
        return selectedPage != null;
    }

    public boolean isSelectedPage(Page testPage) {
        return selectedPage == testPage;
    }

    public ObservableList<Page> getPages() {
        return pages;
    }

    public Page getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Page initSelectedPage) {
        selectedPage = initSelectedPage;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String initStudentName) {
        studentName = initStudentName;
    }

    /**
     * Resets the slide show to have no slides and a default title.
     */
    public void reset() {
        pages.clear();
        studentName = DEFAULT_STUDENTNAME;
        selectedPage = null;
    }

    public void addPage() {
        
    }
    
    public void removeSelectedPage() {
        if (isPageSelected()) {
            pages.remove(selectedPage);
            selectedPage = null;
            ui.reloadSlideShowPane();
        }
    }
    
    
}
