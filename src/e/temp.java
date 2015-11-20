/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e;

import e.model.Page;
import e.model.EPortfolio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import static e.Test.JSON_CAPTION;
import static e.Test.JSON_EXT;
import static e.Test.JSON_PAGES;
import static e.Test.JSON_STUDENTNAME;
import static e.Test.PATH_EPORTFOLIO;
import static e.Test.SLASH;

/**
 *
 * @author jieliang
 */
public class temp {
    
    /**
     * This method saves all the data associated with a slide show to a JSON
     * file.
     *
     * @param slideShowToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    public void saveEPortfolio(EPortfolio ePortfolioToSave) throws IOException {
        StringWriter sw = new StringWriter();

        // BUILD THE SLIDES ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(ePortfolioToSave.getPages());

        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject slideShowJsonObject = Json.createObjectBuilder()
                .add(JSON_STUDENTNAME, ePortfolioToSave.getStudentName())
                .add(JSON_PAGES, pagesJsonArray)
                .build();

        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(slideShowJsonObject);
        jsonWriter.close();

        // INIT THE WRITER
        String studentName = "" + ePortfolioToSave.getStudentName();
        String jsonFilePath = PATH_EPORTFOLIO + SLASH + studentName + JSON_EXT;
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(slideShowJsonObject);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(jsonFilePath);
        pw.write(prettyPrinted);
        pw.close();
        System.out.println(prettyPrinted);
    }

//    /**
//     * This method loads the contents of a JSON file representing a slide show
//     * into a SlideSShowModel objecct.
//     *
//     * @param slideShowToLoad The slide show to load
//     * @param jsonFilePath The JSON file to load.
//     * @throws IOException
//     */
//    public void loadSlideShow(EPortfolio ePortfolioToLoad, String jsonFilePath) throws IOException {
//	// LOAD THE JSON FILE WITH ALL THE DATA
//	JsonObject json = loadJSONFile(jsonFilePath);
//
//	// NOW LOAD THE COURSE
//	ePortfolioToLoad.reset();
//	ePortfolioToLoad.setTitle(json.getString(JSON_TITLE));
//	JsonArray jsonSlidesArray = json.getJsonArray(JSON_SLIDES);
//	for (int i = 0; i < jsonSlidesArray.size(); i++) {
//	    JsonObject slideJso = jsonSlidesArray.getJsonObject(i);
//	    ePortfolioToLoad.addSlide(slideJso.getString(JSON_IMAGE_FILE_NAME),
//		    slideJso.getString(JSON_IMAGE_PATH),
//		    slideJso.getString(JSON_CAPTION));
//	}
//    }
//
//    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
//    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
//	InputStream is = new FileInputStream(jsonFilePath);
//	JsonReader jsonReader = Json.createReader(is);
//	JsonObject json = jsonReader.readObject();
//	jsonReader.close();
//	is.close();
//	return json;
//    }
//
//    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
//	JsonObject json = loadJSONFile(jsonFilePath);
//	ArrayList<String> items = new ArrayList();
//	JsonArray jsonArray = json.getJsonArray(arrayName);
//	for (JsonValue jsV : jsonArray) {
//	    items.add(jsV.toString());
//	}
//	return items;
//    }
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
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_IMAGE_FILE_NAME, slide.getImageFileName())
                .add(JSON_IMAGE_PATH, slide.getImagePath())
                .add(JSON_CAPTION, slide.getCaption())
                .build();
        return jso;
    }

    private JsonArray makeComponentsJsonArray(List<Component> components) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
    }
    private JsonObject makeComponentJsonObject(Component component) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
    }
}
