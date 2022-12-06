package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;


class JSONToolPrettifyTest extends BaseTest {

    @Test
    void testIsPrettifyDecoratorWorking(){
        JSONToolDecorator pretty = new JSONToolPrettify();
        JSONObject ouput = new JSONObject("pap");
        try {
            ouput = pretty.decorate(new JSONObject(miniJson1));
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        //assertEquals((ouput.getJson()), prettyJson1rn);
        assertEquals(ouput.getJson(),prettyJson1n);
    }

    @Test
    void testIsPrettufyDecoratorThrowingExepcion(){
        JSONToolDecorator pretty = new JSONToolPrettify();
        assertThrows(JSONException.class,()->pretty.decorate(new JSONObject(notAJson)));
    }
}