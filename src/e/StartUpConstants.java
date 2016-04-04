/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

/**
 *
 * @author jieliang
 */
public class StartUpConstants {
    
    // Confirmation dialog
    public static String CONFIRMATION_DIALOG_TITLE = "Conformation Dialog";
    public static String CONFIRMATION_DIALOG_HEADER = "Do you want to add a Page?";
    public static String CONFIRMATION_DIALOG_CONTENT = "OK for adding page, cancel for displaying pages.";

    public static String DEFAULT_STUDENTNAME = "John Doe";
    public static String DEFAULT_PAGETITLE = "title";
    public static String DEFAULT_BANNER = "banner";    
    public static String DEFAULT_FONT = "'Montserrat', sans-serif;";
    public static String DEFAULT_FOOTER = "footer";
    public static String DEFAULT_BANNERIMAGE_NAME = "banner.jpg";
    
    public static String DEFAULT_LAYOUT_TEMPLATE = "layout_1.css";
    public static String DEFAULT_COLOR_TEMPLATE = "color_1.css";

    //selection
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";

    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS
    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String PATH_DATA = "./data/";
    public static String PATH_EPORTFOLIO = PATH_DATA + "ePortfolio/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_PIC = PATH_IMAGES + "img/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    public static String PATH_CSS = "/e/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "EPortfolioMakerStyle.css";

    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    public static String ICON_WINDOW_LOGO = "Logo.png";
    public static String ICON_NEW_EPORTFOLIO = "New.png";
    public static String ICON_LOAD_EPORTFOLIO = "Load.png";
    public static String ICON_SAVE_EPORTFOLIO = "Save.png";
    public static String ICON_SAVE_AS_EPORTFOLIO = "SaveAs.png";
    public static String ICON_EXPORT_EPORTFOLIO = "Export.png";
    public static String ICON_EXIT = "Exit.png";
    public static String ICON_PAGE_EDITOR = "Editor.png";
    public static String ICON_PAGE_VIEWER = "Viewer.png";

    public static String ICON_ADD_PAGE = "Add.png";
    public static String ICON_REMOVE_PAGE = "Remove.png";
    public static String ICON_BANNER_IMAGE = "banner_image.png";
    public static String ICON_LAYOUT_TEMPLATE = "Layout.png";
    public static String ICON_COLOR_TEMPLATE = "Color.png";
    public static String ICON_FONT = "font.png";

    public static String ICON_ADD_TEXT = "Text.png";
    public static String ICON_ADD_IMAGE = "pic.png";
    public static String ICON_ADD_VIDEO = "Video.png";
    public static String ICON_ADD_SLIDESHOW = "Slideshow.png";
    public static String ICON_ADD_LINK = "Link.png";

    public static String ICON_ADD_HEADING = "heading.png";
    public static String ICON_ADD_P = "p.png";
    public static String ICON_ADD_LIST = "list.png";

    // UI SETTINGS
    public static String DEFAULT_COMPONENT_IMAGE = "banner.jpg";
    public static int DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int DEFAULT_SLIDE_SHOW_HEIGHT = 500;

    //default tex
    public static String LABEL_PAGE_TITLE = "Title";
    public static String LABEL_STUDENT_NAME = "Student Name: ";
    public static String LABEL_BANNER = "Banner: ";
    public static String LABEL_FOOTER = "Footer: ";
    public static String LABEL_SAVE_UNSAVED_WORK = "Save unsaved work?";
    // CSS STYLE SHEET CLASSES

    // CSS - FOR THE DIALOG
    public static String CSS_CLASS_DIALOG_PANE = "dialog_pane";
    public static String CSS_CLASS_DIALOG_BUTTON_PANE = "dialog_button_pane";
    public static String CSS_CLASS_DIALOG_OPTION_BUTTON = "dialog_option_button";
    public static String CSS_CLASS_DIALOG_LABEL = "dialog_label";

    // CSS - FOR THE TOP TOOLBARS
    public static String CSS_CLASS_HORIZONTAL_TOOLBAR_PANE = "horizontal_toolbar_pane";
    public static String CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";
    //CSS - FOR THE LEFT SIDE OF TOOLBARS
    public static String CSS_CLASS_VERTICAL_TOOLBAR_PANE = "vertical_toolbar_pane";
    public static String CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    //CSS - RIGHT SIDE
    public static String CSS_CLASS_PAGE_EDIT_PANE = "page_edit_pane";
    public static String CSS_CLASS_PAGE_EDIT_SCROLLPANE = "page_edit_scrollpane";
    public static String CSS_CLASS_PAGE_EDIT_TITLES_VBOX = "page_edit_titles_vbox";
    public static String CSS_CLASS_PAGE_EDIT_TOOLBAR_PANE = "page_edit_toolbar_pane";
    public static String CSS_CLASS_PAGE_EDIT_TOOLBAR_BUTTON = "page_edit_toolbar_button";
    public static String CSS_CLASS_PAGE_EDIT_TITLES_LABEL = "page_edit_titles_label";
    public static String CSS_CLASS_PAGE_EDIT_REMOVE_BUTTON = "page_edit_remove_button";

    //CSS - MIDDLE
    public static String CSS_CLASS_COMPONENT_LABEL = "component_label";
    public static String CSS_CLASS_COMPONENT_TEXTFIELD = "text-field";

    public static String CSS_CLASS_COMPONENT_PANE = "component_pane";
    public static String CSS_CLASS_PAGE_PANE = "page_pane";

    //CSS - ELEMENTS
    public static String CSS_CLASS_COMPONENT = "component";
    public static String CSS_CLASS_SELECTED_COMPONENT = "selected_component";
    public static String CSS_CLASS_PAGE = "page";
    public static String CSS_CLASS_SELECTED_PAGE = "selected_page";

    // CSS - SLIDESHOW EDITING
    public static String CSS_CLASS_BANNER_PANE = "title_pane";
    public static String CSS_CLASS_BANNER_TEXT = "banner_text";
    public static String CSS_CLASS_TITLE_TEXT_FIELD = "title_text_field";

    // CSS - PANELS
    public static String CSS_CLASS_WORKSPACE = "workspace";

}
