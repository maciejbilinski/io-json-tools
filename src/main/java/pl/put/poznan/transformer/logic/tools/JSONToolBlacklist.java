package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

/**
 * The type Json tool blacklist.
 */
public class JSONToolBlacklist extends JSONToolFilter {
    /**
     * Instantiates a new Json tool blacklist.
     *
     * @param tool the tool
     */
    public JSONToolBlacklist(IJSONTool tool) {
        super(tool);
    }

    /**
     * Instantiates a new Json tool blacklist.
     *
     * @param tool       the tool
     * @param filterList the filter list
     */
    public JSONToolBlacklist(IJSONTool tool, String[] filterList) {
        super(tool, filterList);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        if (wrappee != null)
            return super.decorate(json);
        else
            return blacklist(json);
    }


    /**
     * Whitelist json object.
     * //TODO zaimplementować metodę która usunie z jsona te klucze które są wisywane do
     * filterlist w kontruktorze
     *
     * @param json the json
     * @return the json object
     */
    private JSONObject blacklist(JSONObject json) {
        if (filterList == null)
            return json;
        else return new JSONObject("to musisz zmienić");
    }


}
