/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import static e.StartUpConstants.DEFAULT_BANNER;
import static e.StartUpConstants.DEFAULT_BANNERIMAGE_NAME;
import static e.StartUpConstants.DEFAULT_FONT;
import static e.StartUpConstants.DEFAULT_FOOTER;
import static e.StartUpConstants.DEFAULT_STUDENTNAME;
import static e.StartUpConstants.PATH_PIC;
import e.view.EPortfolioMakerView;
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

    public EPortfolio(EPortfolioMakerView initUI) {
        ui = initUI;
        pages = FXCollections.observableArrayList();
        reset();
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
    public Page getPage(int i) {
        return pages.get(i);
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

    public void addPage(String pageTitle) {
        addPage(pageTitle, DEFAULT_FONT, DEFAULT_FOOTER, DEFAULT_BANNER, DEFAULT_BANNERIMAGE_NAME, PATH_PIC);
    }

    public void addPage(String pageTitle,String pageFont, String pageFooter,String banner, String bannerFileName, String bannerFilePath) {
        Page pageToAdd = new Page(ui);
        pageToAdd.setPageTitle(pageTitle);
        pageToAdd.setFont(pageFont);
        pageToAdd.setFooter(pageFooter);
        pageToAdd.setBanner(banner);
        pageToAdd.setBannerImageName(bannerFileName);
        pageToAdd.setBannerImagePath(bannerFilePath);
        pages.add(pageToAdd);
        ui.getEPortfolio().setSelectedPage(pageToAdd);
        ui.reloadEPortfolioPane();
    }

    public void removeSelectedPage() {
        if (isPageSelected()) {
            pages.remove(selectedPage);
            selectedPage = null;
            ui.reloadEPortfolioPane();
        }
    }    
}
