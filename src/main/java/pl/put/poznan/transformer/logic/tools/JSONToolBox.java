package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domain.JSONException;

import java.util.ArrayList;
import java.util.List;

public abstract class JSONToolBox {
    /**
     * Parse string into JsonNode object
     *
     * @param json string with data in JSON format
     * @return JsonNode
     * @throws JSONException if json is invalid or is not the object
     */
    static public JsonNode parse(String json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(json);
            if (node.isObject()) return node;
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
        throw new JSONException("Invalid JSON - input is not object");
    }

    /**
     * Extract 'json' param from JsonNode object
     *
     * @param json parsed JSON
     * @return value of 'json' key
     * @throws JSONException if json doesn't contain 'json' property in proper format
     */
    static public JsonNode getInput(JsonNode json) throws JSONException {
        try {
            JsonNode input = json.get("json");
            if (input.isObject()) return input;
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
        throw new JSONException("Invalid JSON - input is not object");
    }

    /**
     * Extract 'json' param from JsonNode object
     *
     * @param json parsed JSON
     * @return value of 'keys'
     * @throws JSONException if json doesn't contain 'keys' property in proper format
     */
    static public List<String> getKeys(JsonNode json) throws JSONException {
        try {
            JsonNode keys = json.get("keys");
            if (keys.isArray()) {
                List<String> output = new ArrayList<>();
                for (JsonNode key : keys) {
                    if (!key.isTextual()) throw new JSONException("Invalid JSON - element of key 'keys' is not a text");
                    output.add(key.asText());
                }
                return output;
            }
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
        throw new JSONException("Invalid JSON - key 'keys' is not array");
    }

    /**
     * Extract 'text1' and 'text2' params from JsonNode object
     *
     * @param json parsed JSON
     * @throws JSONException if missing or incorrect 'text1' and 'text2' params
     * @return array of texts
     */
    static public String[] getTexts(JsonNode json) throws JSONException {
        String[] texts = new String[2];
        try {
            JsonNode jsonText1 = json.get("text1");
            if (!jsonText1.isTextual()) throw new JSONException("Invalid JSON - key 'text1' is not a text");
            texts[0] = jsonText1.asText();

            JsonNode jsonText2 = json.get("text2");
            if (!jsonText2.isTextual()) throw new JSONException("Invalid JSON - key 'text2' is not a text");
            texts[1] = jsonText2.asText();
            return texts;
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
    }
}
