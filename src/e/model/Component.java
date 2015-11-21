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

    String componentFileName;
    ArrayList<String> componentPath;//
    String componentType;
    String componentFont;
    ArrayList<String> componentDesiredText;//

    public Component(String initComponentFileName, ArrayList<String> initComponentPath, String initComponentType, String initComponentFont, ArrayList<String> initComponentDesiredText) {
        componentFileName = initComponentFileName;
        componentPath = initComponentPath;
        componentType = initComponentType;
        componentFont = initComponentFont;
        componentDesiredText = initComponentDesiredText;
    }
    public String getComponentFileName() {
        return componentFileName;
    }
    public void setComponentFileName(String initComponentFileName) {
        componentFileName = initComponentFileName;
    }
    
    public ArrayList<String> getComponentPath() {
        return componentPath;
    }
    public void setComponentPath(int position, String initComponentPath) {
        componentPath.set(position, initComponentPath);
    }
    public void addComponentPath(String initComponentPath) {
        componentPath.add(initComponentPath);
    }
    public void removeComponentPath(int position) {
        componentPath.remove(position);
    }
    
    public String getComponentType() {
        return componentType;
    }
    public void setComponentType(String initComponentType) {
        componentType = initComponentType;
    } 
    
    public String getComponentFont() {
        return componentFont;
    }
    public void setComponentFont(String initComponentFont) {
        componentFont = initComponentFont;
    }
    
    public ArrayList<String> getComponentDesiredText() {
        return componentDesiredText;
    }
    public void setComponentDesiredText(int position, String initComponentDesiredText) {
        componentDesiredText.set(position, initComponentDesiredText);
    }
    public void addComponentDesiredText(String initComponentDesiredText) {
        componentDesiredText.add(initComponentDesiredText);
    }
    public void removeComponentDesiredText(int position) {
        componentDesiredText.remove(position);
    }
}
