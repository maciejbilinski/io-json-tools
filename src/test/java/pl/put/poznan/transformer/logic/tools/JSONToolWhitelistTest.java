package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolWhitelistTest extends BaseTest {

    private static JSONToolDecorator whitelist;

    @Test
    void testIsWhitelistDecoratorWorking(){
        String[] filterList = {"remainKey"};
        whitelist = new JSONToolWhitelist(filterList);

        String fullJson = "{\"key\":\"value\",\"remainKey\":\"remainValue\"}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = whitelist.decorate(input);
            assertEquals(output.getJson(), "{\"remainKey\":\"remainValue\"}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsWhitelistDecoratorWorkingWithEmptyFilterList() {
        String[] filterList = {};
        whitelist = new JSONToolWhitelist(filterList);

        String fullJson = "{\"key\":\"value\",\"secondKey\":\"secondValue\"}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = whitelist.decorate(input);
            assertEquals(output.getJson(), "{}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsWhitelistDecoratorWorkingWithNestedJSON() {
        String[] filterList = {"remainKey"};
        whitelist = new JSONToolWhitelist(filterList);

        String fullJson = "{\"key\":\"value\",\"remainKey\":{\"nestedKey\": \"nestedValue\"}}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = whitelist.decorate(input);
            assertEquals(output.getJson(), "{\"remainKey\":{\"nestedKey\":\"nestedValue\"}}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsWhitelistDecoratorWorkingWithArrays() {
        String[] filterList = {"remainKey"};
        whitelist = new JSONToolWhitelist(filterList);

        String fullJson = "{\"key\":[\"value1\",\"value2\"],\"remainKey\":[\"remainValue1\",\"remainValue2\"]}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = whitelist.decorate(input);
            assertEquals(output.getJson(), "{\"remainKey\":[\"remainValue1\",\"remainValue2\"]}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsWhitelistDecoratorThrowingException() {
        String[] filterList = {"remainKey"};
        whitelist = new JSONToolWhitelist(filterList);
        assertThrows(JSONException.class, ()->whitelist.decorate(new JSONObject(notAJson)));
    }
}