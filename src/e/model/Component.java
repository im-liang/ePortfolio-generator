/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.model;

import java.util.ArrayList;

/**
 *
 * @author jieliang
 */
public class Component {

    ArrayList<String> componentContent = new ArrayList<String>();
    String componentPath;
    String componentType;
    String componentFont_Float;
    String componentWidth;
    String componentHeight;
    ArrayList<String> componentCaption = new ArrayList<String>();

    //image
    public Component(String initComponentType, ArrayList<String> initComponentContent, String initComponentPath, String initComponentWidth, String initComponentHeight, String initComponentFont_Float, ArrayList<String> initComponentCaption) {
        componentContent = initComponentContent;
        componentPath = initComponentPath;
        componentWidth = initComponentWidth;
        componentHeight = initComponentHeight;
        componentType = initComponentType;
        componentFont_Float = initComponentFont_Float;
        componentCaption = initComponentCaption;
    }

    // header & list
    public Component() {
        this("",null,"","","","",null);
    }
//
//    // paragraph
//    public Component(String initComponentType, ArrayList<String> initComponentContent,String initComponentFont_Float) {
//        this(initComponentType,initComponentContent,"","","",initComponentFont_Float,null);
//    }
//
//    // video
//    public Component(String initComponentType, ArrayList<String> initComponentContent, String initComponentPath, String initComponentWidth, String initComponentHeight, ArrayList<String> initComponentCaption) {
//        this(initComponentType,initComponentContent,initComponentPath, initComponentWidth, initComponentHeight, "", initComponentCaption);
//    }
//
//    // slideshow
//    public Component(String initComponentType, ArrayList<String> initComponentContent,) {
//
//    }
//
//    // header
//    public Component(String initComponentType, ArrayList<String> initComponentContent,) {
//
//    }

    public ArrayList<String> getComponentContent() {
        return componentContent;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String initComponentPath) {
        componentPath = initComponentPath;
    }

    public String getComponentType() {
        return componentType;
    }

    public String getComponentWidth() {
        return componentWidth;
    }

    public void setComponentWidth(String initComponentWidth) {
        componentWidth = initComponentWidth;
    }

    public void setComponentHeight(String initComponentHeight) {
        componentHeight = initComponentHeight;
    }

    public String getComponentHeight() {
        return componentHeight;
    }

    public void setComponentType(String initComponentType) {
        componentType = initComponentType;
    }

    public String getComponentFont_Float() {
        return componentFont_Float;
    }

    public void setComponentFont_Float(String initComponentFont_Float) {
        componentFont_Float = initComponentFont_Float;
    }

    public ArrayList<String> getComponentCaption() {
        return componentCaption;
    }

}
