package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JSONToolPrettify extends JSONToolDecorator {


public JSONToolPrettify(IJSONTool json) {
        super(json);
    }

    @Override
    public String decore(String json) throws IOException {
        return /*pretty*/(super.decore(json)) + "pretty";
    }

    public String pretty(String json){
        return parse(json).toPrettyString();
    }
}
