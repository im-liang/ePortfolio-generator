package e.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
import ssm.model.Slide;
import ssm.model.SlideShowModel;

/**
 * This class uses the JSON standard to read and write slideshow data files.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowFileManager {

    // JSON FILE READING AND WRITING CONSTANTS

    public static String JSON_TITLE = "title";
    public static String JSON_SLIDES = "slides";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_CAPTION = "caption";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";

    /**
     * This method saves all the data associated with a slide show to a JSON
     * file.
     *
     * @param slideShowToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    public void saveSlideShow(SlideShowModel slideShowToSave) throws IOException {
	StringWriter sw = new StringWriter();

	// BUILD THE SLIDES ARRAY
	JsonArray slidesJsonArray = makeSlidesJsonArray(slideShowToSave.getSlides());

	// NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
	JsonObject slideShowJsonObject = Json.createObjectBuilder()
		.add(JSON_TITLE, slideShowToSave.getTitle())
		.add(JSON_SLIDES, slidesJsonArray)
		.build();

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);

	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(slideShowJsonObject);
	jsonWriter.close();

	// INIT THE WRITER
	String slideShowTitle = "" + slideShowToSave.getTitle();
	String jsonFilePath = PATH_SLIDE_SHOWS + SLASH + slideShowTitle + JSON_EXT;
	OutputStream os = new FileOutputStream(jsonFilePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(slideShowJsonObject);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(jsonFilePath);
	pw.write(prettyPrinted);
	pw.close();
	System.out.println(prettyPrinted);
    }

    /**
     * This method loads the contents of a JSON file representing a slide show
     * into a SlideSShowModel objecct.
     *
     * @param slideShowToLoad The slide show to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
    public void loadSlideShow(SlideShowModel slideShowToLoad, String jsonFilePath) throws IOException {
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(jsonFilePath);

	// NOW LOAD THE COURSE
	slideShowToLoad.reset();
	slideShowToLoad.setTitle(json.getString(JSON_TITLE));
	JsonArray jsonSlidesArray = json.getJsonArray(JSON_SLIDES);
	for (int i = 0; i < jsonSlidesArray.size(); i++) {
	    JsonObject slideJso = jsonSlidesArray.getJsonObject(i);
	    slideShowToLoad.addSlide(slideJso.getString(JSON_IMAGE_FILE_NAME),
		    slideJso.getString(JSON_IMAGE_PATH),
		    slideJso.getString(JSON_CAPTION));
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

    private JsonArray makeSlidesJsonArray(List<Slide> slides) {
	JsonArrayBuilder jsb = Json.createArrayBuilder();
	for (Slide slide : slides) {
	    JsonObject jso = makeSlideJsonObject(slide);
	    jsb.add(jso);
	}
	JsonArray jA = jsb.build();
	return jA;
    }

    private JsonObject makeSlideJsonObject(Slide slide) {
	JsonObject jso = Json.createObjectBuilder()
		.add(JSON_IMAGE_FILE_NAME, slide.getImageFileName())
		.add(JSON_IMAGE_PATH, slide.getImagePath())
		.add(JSON_CAPTION, slide.getCaption())
		.build();
	return jso;
    }
}
