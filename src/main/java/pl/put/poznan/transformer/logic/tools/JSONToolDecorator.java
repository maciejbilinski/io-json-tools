package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

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

    /**
     * Parse string into JsonNode object
     *
     * @param json string with data in JSON format
     * @return null if string doesn't contain valid json, jsonNode otherwise
     */
    protected JsonNode parse(String json) throws JSONException {
        try {
            JsonNode node = objectMapper.readTree(json);
            if (node.isObject()) return node;
        } catch (Exception ignored) {
            throw new JSONException("Invalid JSON" + ignored.getMessage());
        }
        return null;
    }

    /**
     * Extract 'json' param from JsonNode object
     *
     * @param json parsed JSON
     * @return null if json doesn't contain 'json' property in proper format, value of 'json' otherwise
     */
    protected JsonNode getInput(JsonNode json) throws JSONException {
        try {
            JsonNode input = json.get("json");
            if (input.isObject()) return input;
        } catch (Exception ignored) {
            throw new JSONException("Invalid JSON" + ignored.getMessage());
        }
        return null;
    }

    /**
     * Extract 'json' param from JsonNode object
     *
     * @param json parsed JSON
     * @return null if json doesn't contain 'keys' property in proper format, value of 'keys' otherwise
     */
    protected List<String> getKeys(JsonNode json) throws JSONException {
        try {
            JsonNode keys = json.get("keys");
            if (keys.isArray()) {
                List<String> output = new ArrayList<>();
                for (JsonNode key : keys) {
                    if (!key.isTextual()) return null;
                    output.add(key.asText());
                }
                return output;
            }
        } catch (Exception ignored) {
            throw new JSONException("Invalid JSON" + ignored.getMessage());
        }
        return null;
    }

    /**
     * Extract 'text1' and 'text2' params from JsonNode object
     *
     * @param json parsed JSON
     * @return null if missing or incorrect 'text1' and 'text2' params, array of texts otherwise
     */
    protected String[] getTexts(JsonNode json) throws JSONException {
        String[] texts = new String[2];
        try {
            JsonNode jsonText1 = json.get("text1");
            if (!jsonText1.isTextual()) return null;
            texts[0] = jsonText1.asText();

            JsonNode jsonText2 = json.get("text2");
            if (!jsonText2.isTextual()) return null;
            texts[1] = jsonText2.asText();
            return texts;
        } catch (Exception ignored) {
            throw new JSONException("Invalid JSON" + ignored.getMessage());
        }
    }


}
