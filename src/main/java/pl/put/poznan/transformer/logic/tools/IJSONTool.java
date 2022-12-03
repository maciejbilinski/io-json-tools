package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import java.io.IOException;

/**
 * The interface Ijson tool.
 */
interface IJSONTool {
    /**
     * Decorate json object.
     *
     * @param json the json
     * @return the json object
     * @throws IOException the io exception
     */
    public JSONObject decorate(JSONObject json) throws JSONException;
}

