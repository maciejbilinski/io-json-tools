package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import java.io.IOException;

/**
 * The type Json tool whitelist.
 */
public class JSONToolWhitelist extends JSONToolFilter {

    /**
     * Instantiates a new Json tool whitelist.
     *
     * @param tool the tool
     */
    public JSONToolWhitelist(IJSONTool tool) {
        super(tool);
    }

    /**
     * Instantiates a new Json tool whitelist.
     *
     * @param tool       the tool
     * @param filterList the filter list
     */
    public JSONToolWhitelist(IJSONTool tool, String[] filterList) {
        super(tool, filterList);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        return whitelist(super.decorate(json));
    }

    /**
     * Whitelist json object.
     *TODO zaimplementować metodę która zostawi w jsonie tylko te klucze które są wisywane do
     * filterlist w kontruktorze
     *
     * @param json the json
     * @return the json object
     */
    private JSONObject whitelist(JSONObject json){
        return json;
    }
}
