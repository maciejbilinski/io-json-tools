package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import java.io.IOException;

/**
 * The type Json tool minify.
 */
public class JSONToolMinify extends JSONToolDecorator {
    /**
     * Instantiates a new Json tool minify.
     *
     * @param tool the tool
     */
    public JSONToolMinify(IJSONTool tool) {
        super(tool);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        return minify(super.decorate(json));
    }

    /**
     * Minify json object.
     *
     * @param validatedJSON the validated json
     * @return the json object
     */
    public JSONObject minify(JSONObject validatedJSON){
        return new JSONObject(validatedJSON.toString()) ;
    }
}
