package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolBlacklistTest extends BaseTest {
    private static JSONToolDecorator blacklist;

    @Test
    void testIsBlacklistDecoratorWorking(){
        String[] filterList = {"skipKey"};
        blacklist = new JSONToolBlacklist(filterList);

        String fullJson = "{\"key\":\"value\",\"skipKey\":\"skipValue\"}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = blacklist.decorate(input);
            assertEquals(output.getJson(), "{\"key\":\"value\"}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsBlacklistDecoratorWorkingWithEmptyFilterList() {
        String[] filterList = {};
        blacklist = new JSONToolBlacklist(filterList);

        String fullJson = "{\"key\":\"value\",\"secondKey\":\"secondValue\"}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = blacklist.decorate(input);
            assertEquals(output.getJson(), "{\"key\":\"value\",\"secondKey\":\"secondValue\"}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsBlacklistDecoratorWorkingWithNestedJSON() {
        String[] filterList = {"skipKey"};
        blacklist = new JSONToolBlacklist(filterList);

        String fullJson = "{\"key\":\"value\",\"skipKey\":{\"nestedKey\": \"nestedValue\"}}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = blacklist.decorate(input);
            assertEquals(output.getJson(), "{\"key\":\"value\"}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsBlacklistDecoratorWorkingWithArrays() {
        String[] filterList = {"skipKey"};
        blacklist = new JSONToolBlacklist(filterList);

        String fullJson = "{\"key\":[\"value1\",\"value2\"],\"skipKey\":[\"skipValue1\",\"skipValue2\"]}";

        JSONObject output;
        JSONObject input = new JSONObject(fullJson);
        try {
            output = blacklist.decorate(input);
            assertEquals(output.getJson(), "{\"key\":[\"value1\",\"value2\"]}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsBlacklistDecoratorThrowingException() {
        String[] filterList = {"skipKey"};
        blacklist = new JSONToolBlacklist(filterList);
        assertThrows(JSONException.class, ()->blacklist.decorate(new JSONObject(notAJson)));
    }
}