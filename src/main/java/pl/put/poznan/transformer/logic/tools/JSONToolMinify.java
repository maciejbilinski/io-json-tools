package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

/**
 * The type Json tool minify.
 */
public class JSONToolMinify extends JSONToolDecorator {

    /**
     * Instantiates a new Json tool minify.
     */
    public JSONToolMinify(){}

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
        if (wrappee != null)
            return minify(super.decorate(json));
        else
            return minify(json);
    }

    /**
     * Minify json object.
     *
     * @param validatedJSON the validated json
     * @return the json object
     */
    public JSONObject minify(JSONObject validatedJSON) throws JSONException {
        return new JSONObject(parse(validatedJSON.getJson()).toString());
    }
}
