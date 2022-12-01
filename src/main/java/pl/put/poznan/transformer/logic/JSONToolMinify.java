package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JSONToolMinify extends JSONToolDecorator{
    public JSONToolMinify(IJSONTool tool) {
        super(tool);
    }

    @Override
    public String decore(String json) throws IOException {
        return /*minify*/(super.decore(json));
    }

    public String minify(JsonNode validatedJSON){
        return validatedJSON.toString();
    }
}
