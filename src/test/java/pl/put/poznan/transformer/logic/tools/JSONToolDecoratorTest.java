package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolDecoratorTest extends BaseTest {
    @Test
    void testPrettyMnini(){
        JSONObject mini = new JSONObject(miniJson1);
        JSONObject output = new JSONObject("tmp");
        JSONToolDecorator decorator = new JSONToolPrettify(new JSONToolMinify());
        try {
            output = decorator.decorate(mini);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        assertEquals(output.getJson(), prettyJson1rn);
    }

    @Test
    void testMminiPretty(){
        JSONObject pretty = new JSONObject(prettyJson1rn);
        JSONObject output = new JSONObject("tmp");
        JSONToolDecorator decorator = new JSONToolMinify(new JSONToolPrettify());
        try {
            output = decorator.decorate(pretty);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        assertEquals(output.getJson(),miniJson1);
    }

    @Test
    void testMminiMini(){
        JSONObject pretty = new JSONObject(prettyJson1rn);
        JSONObject output = new JSONObject("tmp");
        JSONToolDecorator decorator = new JSONToolMinify(new JSONToolMinify());
        try {
            output = decorator.decorate(pretty);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        assertEquals(output.getJson(),miniJson1);
    }

    @Test
    void testPrettyPretty(){
        JSONObject mini = new JSONObject(miniJson1);
        JSONObject output = new JSONObject("tmp");
        JSONToolDecorator decorator = new JSONToolPrettify(new JSONToolPrettify());
        try {
            output = decorator.decorate(mini);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        assertEquals(output.getJson(), prettyJson1rn);
    }

}