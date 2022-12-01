package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.JSONToolsService;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class JSONToolsController {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSONToolsService service = new JSONToolsService();

    @RequestMapping(value="minify", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> minify(
        @RequestBody String payload
    ) {
        logger.info("POST /api/v1/minify");
        JsonNode jsonNode = service.parse(payload);
        if(jsonNode == null){
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        }else{
            JsonNode json = service.getInput(jsonNode);
            if(json == null){
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'json' property!\"}");
            }
            if(jsonNode.size() != 1){
                return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'json' property!\"}");
            }
            try{
                return ResponseEntity.status(200).body(service.minify(json));
            }catch(Exception exception){
                logger.error(exception.toString());
                return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
            }
        }
    }

    @RequestMapping(value="pretty", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> pretty(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/pretty");
        JsonNode jsonNode = service.parse(payload);
        if(jsonNode == null){
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        }else{
            JsonNode json = service.getInput(jsonNode);
            if(json == null){
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'json' property!\"}");
            }
            if(jsonNode.size() != 1){
                return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'json' property!\"}");
            }
            try{
                return ResponseEntity.status(200).body(service.pretty(json));
            }catch(Exception exception){
                logger.error(exception.toString());
                return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
            }
        }
    }

    @RequestMapping(value="subset", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> subset(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/subset");
        JsonNode jsonNode = service.parse(payload);
        if(jsonNode == null){
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        }else{
            JsonNode json = service.getInput(jsonNode);
            if(json == null){
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'json' property!\"}");
            }
            List<String> keys = service.getKeys(jsonNode);
            if(keys == null){
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'keys' property!\"}");
            }
            if(jsonNode.size() != 2){
                return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'json' and 'keys' properties!\"}");
            }
            try{
                return ResponseEntity.status(200).body(service.minify(service.subset(json, keys)));
            }catch(Exception exception){
                logger.error(exception.toString());
                return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
            }
        }
    }

    @RequestMapping(value="skip", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> skip(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/skip");
        JsonNode jsonNode = service.parse(payload);
        if (jsonNode == null) {
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        } else {
            JsonNode json = service.getInput(jsonNode);
            if (json == null) {
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'json' property!\"}");
            }
            List<String> keys = service.getKeys(jsonNode);
            if (keys == null) {
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'keys' property!\"}");
            }
            if (jsonNode.size() != 2) {
                return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'json' and 'keys' properties!\"}");
            }
            try{
                return ResponseEntity.status(200).body(service.minify(service.skip(json, keys)));
            }catch(Exception exception){
                logger.error(exception.toString());
                return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
            }
        }
    }

    @RequestMapping(value="compare", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> compare(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/compare");
        JsonNode jsonNode = service.parse(payload);
        if (jsonNode == null) {
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        } else {
            String[] texts = service.getTexts(jsonNode);
            if(texts == null){
                return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'text1' or 'text2' properties!\"}");
            }
            if (jsonNode.size() != 2) {
                return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'text1' and 'text2' properties!\"}");
            }

            try{
                return ResponseEntity.status(200).body(objectMapper.writeValueAsString(Map.of("differences", service.compare(texts[0], texts[1]))));
            }catch(Exception exception){
                logger.error(exception.toString());
                return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
            }
        }
    }
}


