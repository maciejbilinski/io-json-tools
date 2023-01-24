package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

/**
 * The interface IJSON Tool.
 */
public interface IJSONTool {

    /**
     * Decorate json object.
     *
     * @param json the json
     * @return the json object
     * @throws JSONException the io exception
     */
    JSONObject decorate(JSONObject json) throws JSONException;
}

