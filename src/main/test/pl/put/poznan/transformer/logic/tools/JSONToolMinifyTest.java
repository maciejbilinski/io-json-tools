package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domian.JSONException;
import pl.put.poznan.transformer.logic.domian.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolMinifyTest extends BaseTest {
    @Test
    void testIsMinifyDecoratorWorking(){
        JSONToolDecorator pretty = new JSONToolMinify();
        JSONObject ouput = new JSONObject("pap");
        JSONObject input = new JSONObject(prettyJson1);
        try {
            ouput = pretty.decorate(input);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(ouput.getJson(), miniJson1);
    }

    @Test
    void testIsMinifyDecoratorThrowingExepcion(){
        JSONToolDecorator pretty = new JSONToolPrettify();
        assertThrows(JSONException.class,()->pretty.decorate(new JSONObject(notAJson)));
    }
}