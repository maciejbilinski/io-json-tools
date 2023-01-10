package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.domain.JSONObject;

import java.util.List;

/**
 * Helper class to return pair of values
 */
class TransformedPayloadForFilter {
    /**
     * Extracted json from payload
     */
    public final JSONObject json;

    /**
     * Extracted keys from payload
     */
    public final List<String> keys;

    /**
     * Instantiates a new output of transformPayloadForFilter method
     *
     * @param json extracted json from payload
     * @param keys extracted keys from payload
     */
    public TransformedPayloadForFilter(JSONObject json, List<String> keys){
        this.json = json;
        this.keys = keys;
    }
}