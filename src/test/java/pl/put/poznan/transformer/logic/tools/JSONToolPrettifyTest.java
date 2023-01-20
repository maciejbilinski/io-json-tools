package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;


class JSONToolPrettifyTest extends BaseTest {
    private static JSONToolDecorator prettify;

    @BeforeAll
    static void init() {prettify = new JSONToolPrettify();}

    @Test
    void testIsPrettifyDecoratorWorking(){
        JSONObject output;
        JSONObject input = new JSONObject(miniJson1);
        try {
            output = prettify.decorate(input);
            assertEquals(output.getJson().replace("\r", ""), prettyJson1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsPrettifyDecorationWorkingWithNestedJSON(){
        String mini = "{\"simple\":{\"nested\":{\"example\":3},\"papaj\":4}}";
        JSONObject output;
        JSONObject input = new JSONObject(mini);
        try {
            output = prettify.decorate(input);
            assertEquals(output.getJson().replace("\r", ""), "{\n  \"simple\" : {\n    \"nested\" : {\n      \"example\" : 3\n    },\n    \"papaj\" : 4\n  }\n}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsPrettifyDecorationWorkingWithArray(){
        String mini = "{\"simple\":[\"a\",\"b\",\"c\"]}";
        JSONObject output;
        JSONObject input = new JSONObject(mini);
        try {
            output = prettify.decorate(input);
            assertEquals(output.getJson().replace("\r", ""), "{\n  \"simple\" : [ \"a\", \"b\", \"c\" ]\n}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsPrettifyDecoratorThrowingException(){
        JSONToolDecorator pretty = new JSONToolPrettify();
        assertThrows(JSONException.class,()->pretty.decorate(new JSONObject(notAJson)));
    }
}