/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.view;

import e.model.Page;

/**
 *
 * @author jieliang
 */
public class PageEditView {
    EPortfolioMakerView ui;
    Page page;
    
    public PageEditView(EPortfolioMakerView initUI, Page initPage) {
        ui = initUI;
        page = initPage;
    }
}
