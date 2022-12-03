package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domain.JSONException;

import java.util.ArrayList;
import java.util.List;

abstract class JSONToolBox {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parse string into JsonNode object
     *
     * @param json string with data in JSON format
     * @return null if string doesn't contain valid json, jsonNode otherwise
     */
    static public JsonNode parse(String json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();


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
    static public JsonNode getInput(JsonNode json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();

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
    static public List<String> getKeys(JsonNode json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();

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
    static public String[] getTexts(JsonNode json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();

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
