package pl.put.poznan.transformer.logic;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JSONToolsService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parse string into JsonNode object
     * @param json string with data in JSON format
     * @return null if string doesn't contain valid json, jsonNode otherwise
     */
    public JsonNode parse(String json){
        try{
            JsonNode node = objectMapper.readTree(json);
            if(node.isObject()) return node;
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Extract 'json' param from JsonNode object
     * @param json parsed JSON
     * @return null if json doesn't contain 'json' property in proper format, value of 'json' otherwise
     */
    public JsonNode getInput(JsonNode json){
        try{
            JsonNode input = json.get("json");
            if(input.isObject()) return input;
        }catch(Exception ignored){
        }
        return null;
    }

    /**
     * Extract 'json' param from JsonNode object
     * @param json parsed JSON
     * @return null if json doesn't contain 'keys' property in proper format, value of 'keys' otherwise
     */
    public List<String> getKeys(JsonNode json){
        try{
            JsonNode keys = json.get("keys");
            if(keys.isArray()){
                List<String> output = new ArrayList<>();
                for (JsonNode key: keys) {
                    if(!key.isTextual()) return null;
                    output.add(key.asText());
                }
                return output;
            }
        }catch(Exception ignored){}
        return null;
    }

    /**
     * Extract 'text1' and 'text2' params from JsonNode object
     * @param json parsed JSON
     * @return null if missing or incorrect 'text1' and 'text2' params, array of texts otherwise
     */
    public String[] getTexts(JsonNode json){
        String[] texts = new String[2];
        try{
            JsonNode jsonText1 = json.get("text1");
            if(!jsonText1.isTextual()) return null;
            texts[0] = jsonText1.asText();

            JsonNode jsonText2 = json.get("text2");
            if(!jsonText2.isTextual()) return null;
            texts[1] = jsonText2.asText();
            return texts;
        }catch(Exception ignored){}
        return null;
    }

    /**
     * Convert JsonNode to minified string
     * @param validatedJSON validated JSON
     * @return minified string in JSON format
     */
    public String minify(JsonNode validatedJSON){
        return validatedJSON.toString();
    }

    /**
     * Convert JsonNode to pretty string
     *
     * @param validatedJSON validated JSON
     * @return pretty string in JSON format
     */


    /* TODO trzeba zmienic te medody poniżej  żeby wykorzystywały JSONToole */

    public String pretty(JsonNode validatedJSON){
        return validatedJSON.toPrettyString();
    }

    public JsonNode subset(JsonNode validatedJson, List<String> keys){
        throw new UnsupportedOperationException();
    }

    public JsonNode skip(JsonNode validatedJson, List<String> keys){
        throw new UnsupportedOperationException();
    }

    public Integer[] compare(String text1, String text2){
        throw new UnsupportedOperationException();
    }
}
