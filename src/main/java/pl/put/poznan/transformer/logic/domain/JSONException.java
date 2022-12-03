package pl.put.poznan.transformer.logic.domain;

import java.io.IOException;

public class JSONException extends IOException {
    public JSONException(String invalid_json) {
        super(invalid_json);
    }
}
