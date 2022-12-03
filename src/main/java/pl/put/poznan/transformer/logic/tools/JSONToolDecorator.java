package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Json tool decorator.
 */
abstract class JSONToolDecorator implements IJSONTool {

    /**
     * The Object mapper.
     */
    protected final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Wrappee.
     */
    protected IJSONTool wrappee = null;

    public JSONToolDecorator() {

    }

    /**
     * Instantiates a new Json tool decorator.
     *
     * @param tool the tool
     */
    public JSONToolDecorator(IJSONTool tool) {
        this.wrappee = tool;
    }

    public JSONObject decorate(JSONObject json) throws JSONException {
        if (wrappee != null)
            return wrappee.decorate(json);
        else return json;
    }




}
