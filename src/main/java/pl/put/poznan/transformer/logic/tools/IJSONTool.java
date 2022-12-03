package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

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
    JSONObject decorate(JSONObject json) throws JSONException;
}

