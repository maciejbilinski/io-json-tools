package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

interface IJSONTool {
    String decore(String json) throws IOException;
}

