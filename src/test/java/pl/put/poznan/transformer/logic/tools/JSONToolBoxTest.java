package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JSONToolBoxTest extends BaseTest {

    static ObjectMapper mockedMapper;
    static JsonNode mockedNotObject;
    static JsonNode mockedIsObject;
    static JsonNode node;


    @BeforeAll
    static void setUp() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

         node = objectMapper.readTree("{\"json\":{\"example\":3,\"papaj\":4}}");

         mockedMapper = mock(ObjectMapper.class);
        when(mockedMapper.readTree(anyString())).thenReturn(node);


        mockedIsObject = mock(JsonNode.class);
        when(mockedIsObject.isObject()).thenReturn(true);
        when(mockedIsObject.get("json")).thenReturn(objectMapper.readTree("{\"json\":{\"example\":3,\"papaj\":4}}"));

        mockedNotObject = mock(JsonNode.class);
        when(mockedNotObject.isObject()).thenReturn(false);

    }

    @Test
    void testObjectMapperReadTree_thenDoesNotThrowException(){
        try {
            JsonNode tmp = mockedMapper.readTree("");
            assertTrue(tmp.isObject());
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void testGetInputNotJsonNode_thenThrowException() {
        assertThrows(JSONException.class, ()->JSONToolBox.getInput(mockedNotObject));
    }

    @Test
    void testGetInputJsonNode_thenDoesNotThrowException(){
        assertDoesNotThrow(()->JSONToolBox.getInput(mockedIsObject));
    }

    @Test
    void getKeysInputNotJsonNode_thenThrowException() {
        assertThrows(JSONException.class, ()->JSONToolBox.getKeys(mockedNotObject));
    }

    @Test
    void getKeysInputJsonNode_thenThrowException() {
        assertThrows(JSONException.class, ()->JSONToolBox.getKeys(mockedIsObject));
    }

    @Test
    void getTextsInputNotJsonNode_thenThrowException() {
        assertThrows(JSONException.class, ()->JSONToolBox.getTexts(mockedNotObject));
    }

    @Test
    void getTextsInputJsonNode_thenThrowException() {
        assertThrows(JSONException.class, ()->JSONToolBox.getTexts(mockedIsObject));
    }
}