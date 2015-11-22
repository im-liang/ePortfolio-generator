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
    public static String DEFAULT_STUDENTNAME = "John Doe";
    public static String DEFAULT_PAGETITLE = "title";

    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String PATH_DATA = "./data/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    public static String PATH_CSS = "/e/style/";
    public static String STYLE_SHEET_UI = PATH_CSS + "EPortfolioMakerStyle.css";

    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    public static String ICON_WINDOW_LOGO = "Logo.png";
    public static String ICON_NEW_EPORTFOLIO = "New.png";
    public static String ICON_LOAD_EPORTFOLIO  = "Load.png";
    public static String ICON_SAVE_EPORTFOLIO  = "Save.png";
    public static String ICON_SAVE_AS_EPORTFOLIO  = "SaveAs.png";
    public static String ICON_EXPORT_EPORTFOLIO = "Export.png";
    public static String ICON_EXIT = "Exit.png";
    
    public static String ICON_ADD_PAGE = "Add.png";
    public static String ICON_REMOVE_PAGE = "Remove.png";
    public static String ICON_BANNER_IMAGE = "pic.png";
    public static String ICON_LAYOUT_TEMPLATE = "Layout.png";
    public static String ICON_COLOR_TEMPLATE = "Color.png";
    
    
    public static String ICON_ADD_TEXT = "Text.png";
    public static String ICON_ADD_IMAGE = "pic.png";
    public static String ICON_ADD_VIDEO = "Video.png";
    public static String ICON_ADD_SLIDESHOW = "Slideshow.png";
    public static String ICON_ADD_LINK = "Link.png";

    // UI SETTINGS
    public static String    DEFAULT_SLIDE_IMAGE = "DefaultStartSlide.png";
    public static int	    DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int	    DEFAULT_SLIDE_SHOW_HEIGHT = 500;
    
    //default tex
    public static String LABEL_PAGE_TITLE = "Title";
    public static String LABEL_STUDENT_NAME = "Student Name";
    public static String LABEL_FOOTER = "Footer";
    public static String LABEL_SAVE_UNSAVED_WORK = "Save unsaved work?";
    // CSS STYLE SHEET CLASSES
    
    // CSS - FOR THE LANGUAGE SELECTION DIALOG
    public static String    CSS_CLASS_LANG_DIALOG_PANE = "lang_dialog_pane";
    public static String    CSS_CLASS_LANG_PROMPT = "lang_prompt";
    public static String    CSS_CLASS_LANG_COMBO_BOX = "lang_combo_box";
    public static String    CSS_CLASS_LANG_OK_BUTTON = "lang_ok_button";

    // CSS - FOR THE TOOLBARS
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_PANE = "horizontal_toolbar_pane";
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_PANE = "vertical_toolbar_pane";
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";

    // CSS - SLIDESHOW EDITING
    public static String    CSS_CLASS_TITLE_PANE = "title_pane";
    public static String    CSS_CLASS_TITLE_PROMPT = "title_prompt";
    public static String    CSS_CLASS_TITLE_TEXT_FIELD = "title_text_field";
    public static String    CSS_CLASS_CAPTION_PROMPT = "caption_prompt";
    public static String    CSS_CLASS_CAPTION_TEXT_FIELD = "caption_text_field";
    
    // CSS - PANELS
    public static String    CSS_CLASS_WORKSPACE = "workspace";
    public static String    CSS_CLASS_SLIDES_EDITOR_PANE = "slides_editor_pane";
    public static String    CSS_CLASS_SLIDE_EDIT_VIEW = "slide_edit_view";
    public static String    CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW = "selected_slide_edit_view";

    // UI LABELS
    public static String    LABEL_SLIDE_SHOW_TITLE = "slide_show_title";
    public static String    LABEL_LANGUAGE_SELECTION_PROMPT = "Select a Language:";
    public static String    OK_BUTTON_TEXT = "OK";
}
