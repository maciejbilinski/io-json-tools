package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import java.io.IOException;

/**
 * The type Json tool validator.
 */
public class JSONToolValidator implements IJSONTool {
    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        if (isValid(json.getJson()))
            return json;
        else
            throw (new JSONException("Invalid JSON"));
    }

    /**
     * Is valid boolean.
     *
     * @param json the json
     * @return the boolean
     */
    public static boolean isValid(String json) {
        boolean retValue = true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = factory.createParser(json);
            JsonNode jsonObj = mapper.readTree(parser);
            System.out.println(jsonObj.toString());
        }
        catch(JsonParseException jpe) {
            retValue = false;
        }
        catch(IOException ioe) {
            retValue = false;
        }
        return retValue;
    }

}
