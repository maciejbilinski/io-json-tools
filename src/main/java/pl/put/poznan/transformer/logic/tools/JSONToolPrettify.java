package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import java.io.IOException;

/**
 * The type Json tool prettify.
 */
public class JSONToolPrettify extends JSONToolDecorator {


    /**
     * Instantiates a new Json tool prettify.
     *
     * @param json the json
     */
    public JSONToolPrettify(IJSONTool json) {
        super(json);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        return pretty(super.decorate(json));
    }

    /**
     * Pretty json object.
     *
     * @param json the json
     * @return the json object
     */
    public JSONObject pretty(JSONObject json){
        return new JSONObject(parse(json.getJson()).toPrettyString());
    }
}
