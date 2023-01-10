package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolMinifyTest extends BaseTest {
    private static JSONToolDecorator minify;

    @BeforeAll
    static void init(){
        minify = new JSONToolMinify();
    }

    @Test
    void testIsMinifyDecoratorWorking(){
        JSONObject output;
        JSONObject input = new JSONObject(prettyJson1);
        try {
            output = minify.decorate(input);
            assertEquals(output.getJson(), miniJson1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsMinifyDecorationWorkingWithNestedJSON(){
        String pretty = "{\n  \"simple\" : {\n    \"nested\" : {\n        \"example\": 3\n        },\n    \"papaj\" : 4\n  }\n}";
        JSONObject output;
        JSONObject input = new JSONObject(pretty);
        try {
            output = minify.decorate(input);
            assertEquals(output.getJson(), "{\"simple\":{\"nested\":{\"example\":3},\"papaj\":4}}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testIsMinifyDecorationWorkingWithArray(){
        String pretty = "{\n  \"simple\" : [\n    \"a\",\n    \"b\",\n    \"c\"\n    ]\n}";
        JSONObject output;
        JSONObject input = new JSONObject(pretty);
        try {
            output = minify.decorate(input);
            assertEquals(output.getJson(), "{\"simple\":[\"a\",\"b\",\"c\"]}");
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }

    @Test
    void testDoesMinifyDecoratorThrowingException(){
        JSONToolDecorator mini = new JSONToolMinify();
        assertThrows(JSONException.class,()->mini.decorate(new JSONObject(notAJson)));
    }
}