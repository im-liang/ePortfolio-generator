/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.file;

import static e.StartUpConstants.PATH_EPORTFOLIO;
import static e.controller.EPortfolioEditController.COLORTEMPLATE;
import static e.controller.EPortfolioEditController.LAYOUTTEMPLATE;
import e.model.Component;
import e.model.EPortfolio;
import e.model.Page;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 * Using JSON to read and write ePortfolio data files
 *
 * @author jieliang
 */
public class EPortfolioFileManager {

    public static String JSON_STUDENT_NAME = "student_name";
    public static String JSON_PAGES = "pages";
    public static String JSON_PAGE_TITLE = "page_title";
    public static String JSON_PAGE_FONT = "page_font";
    public static String JSON_PAGE_FOOTER = "footer";
    public static String JSON_PAGE_PATH = "page_path";
    public static String JSON_PAGE_LAYOUT = "layout";
    public static String JSON_PAGE_COLOR = "color";
    public static String JSON_BANNER = "banner";
    public static String JSON_BANNER_FILE_NAME = "banner_file_name";
    public static String JSON_BANNER_FILE_PATH = "banner_file_path";
    public static String JSON_COMPONENTS = "components";
    public static String JSON_COMPONENT_TYPE = "component_type";
    public static String JSON_COMPONENT_CONTENT = "component_content";
    public static String JSON_COMPONENT_PATH = "component_path";
    public static String JSON_COMPONENT_FONT_FLOAT = "component_font_float";
    public static String JSON_COMPONENT_WIDTH = "component_width";
    public static String JSON_COMPONENT_HEIGHT = "component_height";
    public static String JSON_COMPONENT_CAPTION = "component_caption";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    private List<Component> componentsString;

    public void saveEPortfolio(EPortfolio ePortfolioToSave, String ePortfolioStudentName) throws IOException {
        StringWriter sw = new StringWriter();

        //BUILD THE COMPONENTS ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(ePortfolioToSave.getPages());

        //BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject ePortfolioJsonObject = Json.createObjectBuilder()
                .add(JSON_STUDENT_NAME, ePortfolioToSave.getStudentName())
                .add(JSON_PAGES, pagesJsonArray)
                .build();

        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(ePortfolioJsonObject);
        jsonWriter.close();

        //INIT THE WRITER
        String jsonFilePath = PATH_EPORTFOLIO + SLASH + ePortfolioStudentName + JSON_EXT;
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(ePortfolioJsonObject);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(jsonFilePath);
        pw.write(prettyPrinted);
        pw.close();
        System.out.println(prettyPrinted);
    }

    /**
     * This method loads the contents of a JSON file representing an ePortfolio
     * into a EPortfolio objecct.
     *
     * @param ePortfolioToLoad The ePortfolio to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
    public void loadEPortfolio(EPortfolio ePortfolioToLoad, String jsonFilePath) throws IOException {
        //LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);

        //LOAD
        ePortfolioToLoad.reset();
        ePortfolioToLoad.setStudentName(json.getString(JSON_STUDENT_NAME));
        JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
        for (int i = 0; i < jsonPagesArray.size(); i++) {
            JsonObject pageJso = jsonPagesArray.getJsonObject(i);
            JsonArray jsonComponentsArray = pageJso.getJsonArray(JSON_COMPONENTS);
            

            LAYOUTTEMPLATE = pageJso.getString(JSON_PAGE_LAYOUT);
            COLORTEMPLATE = pageJso.getString(JSON_PAGE_COLOR);
            ePortfolioToLoad.addPage(pageJso.getString(JSON_PAGE_TITLE),
                    pageJso.getString(JSON_PAGE_FONT),
                    pageJso.getString(JSON_PAGE_FOOTER),
                    pageJso.getString(JSON_BANNER),
                    pageJso.getString(JSON_BANNER_FILE_NAME),
                    pageJso.getString(JSON_BANNER_FILE_PATH)
            );

            for (int j = 0; j < jsonComponentsArray.size(); j++) {

                JsonObject componentJso = jsonComponentsArray.getJsonObject(j);
                //CONTENT FOR COMPONENTS
                ArrayList<String> component_content = new ArrayList<String>();
                String[] content_temp = componentJso.getString(JSON_COMPONENT_CONTENT).split(",");
                for (int k = 0; k < content_temp.length; k++) {
                    component_content.add(content_temp[k]);
                }
                //CAPTIONS
                ArrayList<String> component_caption = new ArrayList<String>();
                if (!componentJso.getString(JSON_COMPONENT_CAPTION).isEmpty()) {
                    String[] caption_temp = componentJso.getString(JSON_COMPONENT_CAPTION).split(",");
                    for (int k = 0; k < content_temp.length; k++) {
                        component_caption.add(caption_temp[k]);
                    }
                }

                ePortfolioToLoad.getPage(i).addComponentJson(componentJso.getString(JSON_COMPONENT_TYPE),
                        component_content,
                        componentJso.getString(JSON_COMPONENT_PATH),
                        componentJso.getInt(JSON_COMPONENT_WIDTH),
                        componentJso.getInt(JSON_COMPONENT_HEIGHT),
                        componentJso.getString(JSON_COMPONENT_FONT_FLOAT),
                        component_caption);
            }
        }
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

    private JsonArray makePagesJsonArray(List<Page> pages) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Page page : pages) {
            JsonObject jso = makePageJsonObject(page);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makePageJsonObject(Page page) {
        JsonArray componentsJsonArray = makeComponentsJsonArray(page.getComponents());
        JsonObject jso;
        if (page.getBannerImageName().isEmpty()) {
            jso = Json.createObjectBuilder()
                    .add(JSON_PAGE_TITLE, page.getPageTitle())
                    .add(JSON_PAGE_FONT, page.getFont())
                    .add(JSON_PAGE_LAYOUT, LAYOUTTEMPLATE)
                    .add(JSON_PAGE_COLOR, COLORTEMPLATE)
                    .add(JSON_PAGE_FOOTER, page.getFooter())
                    .add(JSON_BANNER, page.getBanner())
                    .add(JSON_BANNER_FILE_NAME, "")
                    .add(JSON_BANNER_FILE_PATH, "")
                    .add(JSON_COMPONENTS, componentsJsonArray)
                    .build();
        } else {
            jso = Json.createObjectBuilder()
                    .add(JSON_PAGE_TITLE, page.getPageTitle())
                    .add(JSON_PAGE_FONT, page.getFont())
                    .add(JSON_PAGE_LAYOUT, LAYOUTTEMPLATE)
                    .add(JSON_PAGE_COLOR, COLORTEMPLATE)
                    .add(JSON_PAGE_FOOTER, page.getFooter())
                    .add(JSON_BANNER, page.getBanner())
                    .add(JSON_BANNER_FILE_NAME, page.getBannerImageName())
                    .add(JSON_BANNER_FILE_PATH, page.getBannerImagePath())
                    .add(JSON_COMPONENTS, componentsJsonArray)
                    .build();
        }
        return jso;
    }

    private JsonArray makeComponentsJsonArray(List<Component> components) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Component component : components) {
            JsonObject jso = makeComponentJsonObject(component);
            jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeComponentJsonObject(Component component) {
        String componentContent = "";
        String componentCaption = "";
        for (int i = 0; i < component.getComponentContent().size(); i++) {
            if (i == component.getComponentContent().size() - 1) {
                componentContent += component.getComponentContent().get(i);
            } else {
                componentContent += component.getComponentContent().get(i) + ",";
            }
        }
        for (int i = 0; i < component.getComponentCaption().size(); i++) {
            if (i == component.getComponentCaption().size() - 1) {
                componentCaption += component.getComponentCaption().get(i);
            } else {
                componentCaption += component.getComponentCaption().get(i) + ",";
            }
        }
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_COMPONENT_TYPE, component.getComponentType())
                .add(JSON_COMPONENT_CONTENT, componentContent)
                .add(JSON_COMPONENT_PATH, component.getComponentPath())
                .add(JSON_COMPONENT_FONT_FLOAT, component.getComponentFont_Float())
                .add(JSON_COMPONENT_WIDTH, component.getComponentWidth())
                .add(JSON_COMPONENT_HEIGHT, component.getComponentHeight())
                .add(JSON_COMPONENT_CAPTION, componentCaption)
                .build();
        return jso;
    }

    private List<Component> getComponents() {
        return componentsString;
    }

    private void setComponents(List<Component> initComponentsString) {
        componentsString = initComponentsString;
    }
}
