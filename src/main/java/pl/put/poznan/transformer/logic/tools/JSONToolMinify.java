package pl.put.poznan.transformer.logic.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static pl.put.poznan.transformer.logic.tools.JSONToolBox.parse;

/**
 * The type Json tool minify.
 */
public class JSONToolMinify extends JSONToolDecorator {
    private static Logger logger = LoggerFactory.getLogger(JSONToolMinify.class);


    /**
     * Instantiates a new Json tool minify.
     */
    public JSONToolMinify() {
    }

    /**
     * Instantiates a new Json tool minify.
     *
     * @param tool the tool
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
     * @param validatedJSON the validated json
     * @return the json object
     */
    public JSONObject minify(JSONObject validatedJSON) throws JSONException {
        logger.info("Minifing:\n" + validatedJSON.getJson());
        JSONObject tmpJson = new JSONObject(parse(validatedJSON.getJson()).toString());
        logger.info("Minifacion returned:\n" + tmpJson.getJson());
        return tmpJson;
    }
}
