/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.file;

import e.model.Component;
import e.model.EPortfolio;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 * Using JSON to read and write ePortfolio data files
 *
 * @author jieliang
 */
public class EPortfolioFileManager {

    public static String JSON_STUDENT_NAME = "student_name";
    public static String JSON_PAGES = "pages";
    public static String JSON_PAGE_TITLE = "page_title";
    public static String JSON_PAGE_PATH = "page_path";
    public static String JSON_BANNER = "banner";
    public static String JSON_BANNER_FILE_NAME = "banner_file_name";
    public static String JSON_BANNER_FILE_PATH = "banner_file_path";
    public static String JSON_COMPONENTS = "components";
    public static String JSON_COMPONENT_TYPE = "component_type";
    public static String JSON_COMPONENT_NAME = "component_name";
    public static String JSON_COMPONENT_PATH = "component_path";
    public static String JSON_COMPONENT_FONT = "component_font";
    public static String JSON_COMPONENT_DESIREDTEXT = "component_desiredText";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";

    public void saveEPortfolio(EPortfolio ePortfolioToSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadEPortfolio(EPortfolio ePortfolioToLoad, String absolutePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }

    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList<String> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            items.add(jsV.toString());
        }
        return items;
    }

    private JsonArray makeSlidesJsonArray(List<Component> components) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Component component : components) {
            JsonObject jso = makeComponentJsonObject(component);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeComponentJsonObject(Component component) {
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_COMPONENT_TYPE, component.getComponentType())
                .add(JSON_COMPONENT_NAME, component.getComponentFileName())
                .add(JSON_COMPONENT_PATH, component.getComponentPath())
                .add(JSON_COMPONENT_FONT, component.getComponentFont())
                .add(JSON_COMPONENT_DESIREDTEXT, component.getComponentDesiredText())
                .build();
        return jso;
    }
    
    private JsonObject make
}
