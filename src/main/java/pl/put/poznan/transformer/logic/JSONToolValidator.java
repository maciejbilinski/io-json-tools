package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONToolValidator implements IJSONTool{
    @Override
    public String decore(String json) throws IOException {
        if (isValid(json))
            return json + "papaj";
        else
            throw (new IOException("Invalid JSON"));
    }

    public static boolean isValid(String json) {
        boolean retValue = true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = factory.createParser(json);
            JsonNode jsonObj = mapper.readTree(parser);
            System.out.println(jsonObj.toString());
        }
        catch(JsonParseException jpe) {
            retValue = false;
        }
        catch(IOException ioe) {
            retValue = false;
        }
        return retValue;
    }

}
