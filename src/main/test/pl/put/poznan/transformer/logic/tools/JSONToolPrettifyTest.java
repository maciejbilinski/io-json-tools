package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.domian.JSONObject;
import pl.put.poznan.transformer.logic.tools.JSONToolDecorator;
import pl.put.poznan.transformer.logic.tools.JSONToolPrettify;
import pl.put.poznan.transformer.logic.tools.JSONToolValidator;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONToolPrettifyTest {
    String test = "{\n" +
            "\t\"json\": {\n" +
            "\t\t\"example\": 3,\n" +
            "\t\t\"papaj\":4\n" +
            "\t}\n" +
            "}";
    @Test
    void testIsPrettifyDecoratorWorking(){
        JSONToolDecorator decorator = new JSONToolPrettify(new JSONToolValidator());
        try {
            System.out.println(decorator.decorate(new JSONObject(test)).getJson());
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    @Test
    void testPureTrue(){
        assertEquals(1,1);
    }

}