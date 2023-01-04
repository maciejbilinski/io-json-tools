package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import java.util.List;

import static pl.put.poznan.transformer.logic.tools.JSONToolBox.*;

/**
 * Container for simple parsing, extracting and transforming payload from request
 */
abstract class RestTools {

    /**
     * Parse string into JsonNode object
     *
     * @param payload payload from request
     * @return JsonNode
     * @throws RestException if payload is not valid JSON
     */
    public static JsonNode parsePayload(String payload) throws RestException {
        JsonNode jsonNode;

        try{
            jsonNode = parse(payload);
        }catch(JSONException exception){
            throw new RestException(400, "Payload is not valid JSON!");
        }

        return jsonNode;
    }

    /**
     * Ensures that JsonNode has proper size
     *
     * @throws RestException if JsonNode doesn't have proper size
     */
    public static void ensureJsonNodeSize(JsonNode jsonNode, int size, String errorMsg) throws RestException {
        if(jsonNode.size() != size)
            throw new RestException(400, errorMsg);
    }

    /**
     * Extract 'json' param from JsonNode object
     *
     * @param jsonNode parsed JSON
     * @return value of 'json' key
     * @throws RestException if missing or broken 'json' property
     */
    public static JsonNode extractInput(JsonNode jsonNode) throws RestException{
        JsonNode json;
        try{
            json = getInput(jsonNode);
        }catch(JSONException exception){
            throw new RestException(400, "Missing or broken 'json' property!");
        }

        return json;
    }

    /**
     * Extract 'keys' param from JsonNode object
     *
     * @param jsonNode parsed JSON
     * @return value of 'keys'
     * @throws RestException if missing or broken 'keys' property
     */
    public static List<String> extractKeys(JsonNode jsonNode) throws RestException {
        List<String> keys;
        try{
            keys = getKeys(jsonNode);
        }catch (JSONException exception){
            throw new RestException(400, "Missing or broken 'keys' property!");
        }
        return keys;
    }

    /**
     * Extract 'text1' and 'text2' params from JsonNode object
     *
     * @param jsonNode parsed JSON
     * @return array of texts
     * @throws RestException if missing or broken 'text1' or 'text2' properties
     */
    public static String[] extractTexts(JsonNode jsonNode) throws RestException{
        String[] texts;
        try{
            texts = getTexts(jsonNode);
        }catch(JSONException exception){
            throw new RestException(400, "Missing or broken 'text1' or 'text2' properties!");
        }
        return texts;
    }

    /**
     * Full transformation from payload to JSONObject which can be easily decorated
     *
     * @param payload payload from request
     * @return JSONObject
     * @throws RestException if anything goes wrong
     */
    public static JSONObject transformPayloadForDecorator(String payload) throws RestException{
        JsonNode jsonNode = parsePayload(payload);
        ensureJsonNodeSize(jsonNode, 1, "Request body should contain only 'json' property!");
        JsonNode json = extractInput(jsonNode);
        return new JSONObject(json.toString());
    }

    /**
     * Full transformation from payload to JSONObject which can be easily decorated & keys needed to construct JSONToolFilter
     *
     * @param payload payload from request
     * @return TransformedPayloadForFilter - JSONObject & keys needed to construct JSONToolFilter
     * @throws RestException if anything goes wrong
     */
    public static TransformedPayloadForFilter transformPayloadForFilter(String payload) throws RestException{
        JsonNode jsonNode = parsePayload(payload);
        ensureJsonNodeSize(jsonNode, 2, "Request body should contain only 'json' and 'keys' properties!");
        JsonNode json = extractInput(jsonNode);
        List<String> keys = extractKeys(jsonNode);
        return new TransformedPayloadForFilter(new JSONObject(json.toString()), keys);
    }

    /**
     * Full transformation from payload to String[] which can be easily compared
     *
     * @param payload payload from request
     * @return array of texts
     * @throws RestException if anything goes wrong
     */
    public static String[] transformPayloadForComparison(String payload) throws RestException{
        JsonNode jsonNode = parsePayload(payload);
        ensureJsonNodeSize(jsonNode, 2, "Request body should contain only 'text1' and 'text2' properties!");
        return extractTexts(jsonNode);
    }
}
