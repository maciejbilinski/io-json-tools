package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import pl.put.poznan.transformer.logic.JSONShowDiff;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.tools.IJSONTool;

import java.util.Map;

import static pl.put.poznan.transformer.rest.RestTools.*;

/**
 * Class to handle all REST API requests
 */
class RestHandlers {
    private final Logger logger;

    /**
     * Map exception to RestException
     * @param exception any caught exception
     * @return RestException with 500 HTTP Code and Internal Error message
     */
    private RestException internalError(Exception exception){
        logger.error(exception.toString());
        return new RestException(500, "Internal Error");
    }

    public RestHandlers(Logger logger){
        this.logger = logger;
    }

    /**
     * Handles requests in which only decoration is needed
     *
     * @param payload payload from request
     * @param tool any IJSONTool tool
     * @return consistent response of the request
     */
    public ResponseEntity<String> handleDecoratorRequest(String payload, IJSONTool tool){
        try{
            try{
                return ResponseEntity.status(200).body(tool.decorate(transformPayloadForDecorator(payload)).getJson());
            }catch (JSONException jsonException){
                throw internalError(jsonException);
            }
        }catch (RestException exception) {
            return ResponseEntity.status(exception.httpCode).body(exception.getHTTPBody());
        }
    }

    /**
     * Handles requests in which JSONToolFilter is used
     *
     * @param payload payload from request
     * @param toolGetter interface with defined method constructing JSONToolFilter
     * @return consistent response of the request
     */
    public ResponseEntity<String> handleFilterRequest(String payload, JSONToolFilterGetterInterface toolGetter){
        try{
            try{
                TransformedPayloadForFilter output = transformPayloadForFilter(payload);
                return ResponseEntity.status(200).body(toolGetter.get(output.keys.toArray(new String[0])).decorate(output.json).getJson());
            }catch (JSONException jsonException){
                throw internalError(jsonException);
            }
        }catch (RestException exception) {
            return ResponseEntity.status(exception.httpCode).body(exception.getHTTPBody());
        }
    }

    /**
     * Handles request for two texts comparison
     *
     * @param payload payload from request
     * @return consistent response of the request
     */
    public ResponseEntity<String> handleComparisonRequest(String payload){
        final ObjectMapper objectMapper = new ObjectMapper();
        final JSONShowDiff tool = new JSONShowDiff();

        try{
            String[] texts = transformPayloadForComparison(payload);

            try{
                return ResponseEntity.status(200).body(objectMapper.writeValueAsString(Map.of("differences", tool.compare(texts[0], texts[1]))));
            }catch (JsonProcessingException exception){
                throw internalError(exception);
            }

        }catch (RestException exception) {
            return ResponseEntity.status(exception.httpCode).body(exception.getHTTPBody());
        }
    }
}
