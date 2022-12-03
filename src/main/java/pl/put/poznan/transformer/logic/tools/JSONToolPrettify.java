package pl.put.poznan.transformer.logic.tools;

import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static pl.put.poznan.transformer.logic.tools.JSONToolBox.parse;

/**
 * The type Json tool prettify.
 */
public class JSONToolPrettify extends JSONToolDecorator {
    public JSONToolPrettify() {
        super();
    }

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
        if (wrappee != null)
            return pretty(super.decorate(json));
        else return pretty(json);
    }

    /**
     * Pretty json object.
     *
     * @param json the json
     * @return the json object
     */
    public JSONObject pretty(JSONObject json) throws JSONException {
        return new JSONObject(parse(json.getJson()).toPrettyString());
    }
}
