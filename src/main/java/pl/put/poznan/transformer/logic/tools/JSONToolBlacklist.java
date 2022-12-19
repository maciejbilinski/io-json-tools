package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import java.util.Arrays;
import java.util.List;

import static pl.put.poznan.transformer.logic.tools.JSONToolBox.parse;

/**
 * The type Json tool blacklist.
 */
public class JSONToolBlacklist extends JSONToolFilter {
    private static Logger logger = LoggerFactory.getLogger(JSONToolBlacklist.class);

    /**
     * Instantiates a new Json tool minify.
     *
     * @param filterList the filter list
     */
    public JSONToolBlacklist(String[] filterList) {
        super(filterList);
    }

    /**
     * Instantiates a new Json tool blacklist.
     *
     * @param tool       the tool
     * @param filterList the filter list
     */
    public JSONToolBlacklist(IJSONTool tool, String[] filterList) {
        super(tool, filterList);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        logger.debug("Decorating!");
        if (wrappee != null) {
            logger.debug("I am a wrapper.");
            return super.decorate(json);
        } else {
            logger.debug("I am a Starting point");
            return blacklist(json);
        }
    }


    /**
     * Blacklist json object.
     *
     * @param json the json
     * @return the json object
     */
    private JSONObject blacklist(JSONObject json) throws JSONException {
        if (filterList.length == 0) {
            logger.info("BlackList is empty");
            logger.info("No skip performed, returned:\n" + json.getJson());
            return json;
        } else {
            logger.info("BlackList is not empty");
            JSONObject newJson = new JSONObject((((ObjectNode)parse(json.getJson())).remove(Arrays.asList(filterList))).toString());
            logger.info("Skip returned:\n" + newJson.getJson());
            return newJson;
        }
    }


}
