package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import java.util.Objects;

import static pl.put.poznan.transformer.logic.tools.JSONToolBox.parse;

/**
 * The type Json tool minify.
 */
public class JSONToolMinify extends JSONToolDecorator {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolMinify.class);

    /**
     * Instantiates a new Json tool minify.
     * That is not a wrapper
     */
    public JSONToolMinify() {
    }

    /**
     * Instantiates a new Json tool minify.
     *
     * @param tool the IJSONTool to wraparound
     */
    public JSONToolMinify(IJSONTool tool) {
        super(tool);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        logger.debug("Decorating!");
        if (wrappee != null) {
            logger.debug("I am a wrapper.");
            return minify(super.decorate(json));
        } else {
            logger.debug("I am a Starting point");
            return minify(json);
        }
    }

    /**
     * Minify json object.
     *
     * @param json the JSONObject for minification
     * @return the minified json object, throws JOSNExeption if json is invalid
     */
    public JSONObject minify(JSONObject json) throws JSONException {
        logger.info("Minifing:\n" + json.getJson());
        JSONObject tmpJson = new JSONObject(parse(json.getJson()).toString());
        logger.info("Minifacion returned:\n" + tmpJson.getJson());
        return tmpJson;
    }
}