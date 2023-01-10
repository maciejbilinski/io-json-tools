package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.tools.JSONToolBlacklist;
import pl.put.poznan.transformer.logic.tools.JSONToolMinify;
import pl.put.poznan.transformer.logic.tools.JSONToolPrettify;
import pl.put.poznan.transformer.logic.tools.JSONToolWhitelist;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class JSONToolsController {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);
    private static final RestHandlers handlers = new RestHandlers(logger);

    @RequestMapping(value="minify", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> minify(
        @RequestBody String payload
    ) {
        final JSONToolMinify tool = new JSONToolMinify();
        logger.info("POST /api/v1/minify");
        return handlers.handleDecoratorRequest(payload, tool);
    }

    @RequestMapping(value="pretty", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> pretty(
            @RequestBody String payload
    ) {
        final JSONToolPrettify tool = new JSONToolPrettify();
        logger.info("POST /api/v1/pretty");
        return handlers.handleDecoratorRequest(payload, tool);

    }

    @RequestMapping(value="whitelist", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> whitelist(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/whitelist");
        return handlers.handleFilterRequest(payload, JSONToolWhitelist::new);
    }

    @RequestMapping(value="blacklist", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> blacklist(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/blacklist");
        return handlers.handleFilterRequest(payload, JSONToolBlacklist::new);
    }

    @RequestMapping(value="compare", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> compare(
            @RequestBody String payload
    ) {
        logger.info("POST /api/v1/compare");
        return handlers.handleComparisonRequest(payload);
    }
}


