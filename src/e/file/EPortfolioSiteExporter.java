/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.file;

import e.model.Component;
import e.model.EPortfolio;
import e.model.Page;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author jieliang
 */
public class EPortfolioSiteExporter {

    // WE'LL USE THIS TO BUILD PATHS
    public static String SLASH = "/";
    public static String JSON_EXT = ".json";

    // HERE ARE THE DIRECTORIES WE CARE ABOUT
    public static String BASE_DIR = "./base/";
    public static String SITES_DIR = "./sites/";
    public static String CSS_DIR = "css/";
    public static String DATA_DIR = "data/";
    public static String EPORTFOLIO_DIR = DATA_DIR + "ePortfilio/";
    public static String ICONS_DIR = "icons/";
    public static String IMG_DIR = "img/";
    public static String JS_DIR = "js/";

    // AND HERE ARE THE FILES WE CARE ABOUT
    public static String INDEX_FILE = "index.html";
    public static String STYLESHEET_FILE = "slideshow_maker.css";
    public static String JS_FILE = "SlideshowMaker.js";
    public static String DATA_FILE = "SlideShowData.json";

    public void exportSite(EPortfolio slideShowToExport) throws IOException {
        // THE SITE HOME PATH
        String homeSitePath = SITES_DIR + slideShowToExport.getStudentName() + SLASH;

	// NOW MAKE THE SITE DIRECTORIES AND COPY OVER THE FILES
        // THAT ONLY NEED TO BE COPIED ONCE
        File siteDir = new File(homeSitePath);

        // FIRST DELETE THE OLD FILES IN CASE THINGS
        // LIKE THE PAGE FORMAT MAY HAVE CHANGED
        if (siteDir.exists()) {
            deleteDir(siteDir);
        }

        // NOW MAKE THE HOME DIR
        siteDir.mkdir();

        // MAKE THE CSS, DATA, IMG, AND JS DIRECTORIES
        new File(homeSitePath + CSS_DIR).mkdir();
        new File(homeSitePath + DATA_DIR).mkdir();
        new File(homeSitePath + ICONS_DIR).mkdir();
        new File(homeSitePath + IMG_DIR).mkdir();
        new File(homeSitePath + JS_DIR).mkdir();

        // NOW COPY OVER THE HTML, CSS, ICON, AND JAVASCRIPT FILES
        copyAllFiles(BASE_DIR, homeSitePath);
        copyAllFiles(BASE_DIR + CSS_DIR, homeSitePath + CSS_DIR);
        copyAllFiles(BASE_DIR + ICONS_DIR, homeSitePath + ICONS_DIR);
        copyAllFiles(BASE_DIR + JS_DIR, homeSitePath + JS_DIR);

        // NOW FOR THE TWO THINGS THAT WE HAVE TO COPY OVER EVERY TIME,
        // NAMELY, THE DATA FILE AND THE IMAGES
        // FIRST COPY THE DATA FILE
        Path dataSrcPath = new File(EPORTFOLIO_DIR + slideShowToExport.getStudentName() + JSON_EXT).toPath();
        Path dataDestPath = new File(homeSitePath + DATA_DIR + DATA_FILE).toPath();

        Files.copy(dataSrcPath, dataDestPath);

        // AND NOW ALL THE SLIDESHOW IMAGES
        for (Page s : slideShowToExport.getPages()) {
            for (Component c : s.getComponents()) {
                Path srcImgPath = new File(s. + SLASH + s.getImageFileName()).toPath();
                Path destImgPath = new File(homeSitePath + IMG_DIR + s.getImageFileName()).toPath();
                Files.copy(srcImgPath, destImgPath);
            }

        }
    }

    public void deleteDir(File dir) {
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                deleteDir(f);
                f.delete();
            } else {
                f.delete();
            }
        }
        dir.delete();
    }

    public void copyAllFiles(String sourceFile, String destinationDir) throws IOException {
        File srcDir = new File(sourceFile);
        File[] files = srcDir.listFiles();
        for (File f : files) {
            Path srcPath = f.toPath();
            Path newPath = new File(destinationDir).toPath();
            if (!f.isDirectory()) {
                Files.copy(srcPath, newPath.resolve(srcPath.getFileName()));
            }
        }
    }

}
