package pl.put.poznan.transformer.rest;

/**
 * Exception representing REST API failure
 * It can be simply mapped to ResponseEntity
 */
public class RestException extends Exception{
    /**
     * HTTP Code which should be used in ResponseEntity as status
     */
    public final int httpCode;

    /**
     * Error message which should be used in ResponseEntity body
     */
    private final String message;

    /**
     * Instantiates a new REST API Exception.
     *
     * @param httpCode correct http response status <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">...</a>
     * @param message detailed message what went wrong
     */
    public RestException(int httpCode, String message){
        this.httpCode = httpCode;
        this.message = message;
    }

    /**
     * @return HTTP Body in JSON format which can be used in ResponseEntity
     */
    public String getHTTPBody(){
        return String.format("{\"error\":\"%s\"}", this.message);
    }
}
