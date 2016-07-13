package sample.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by markus on 11/06/16.
 */
@RestController
@RequestMapping(path = "/service", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class RestService {

    @RequestMapping(path = "/hello/{user}")
    public ResponseEntity<?> simpleThing(@PathVariable String user) {
        if ("unknown".equals(user)) {
            throw new IllegalArgumentException("This user is invalid!");
        }
        return new ResponseEntity<Object>(greet(user), HttpStatus.OK);
    }

    String greet(String user) {
        return String.format("Hello %s!", user);
    }

    /**
     * Example for handling one specific exception, like IllegalArgumentException which code be an indicator for bad requests
     *
     * @param exception exception thrown
     * @return response with status code BAD_REQUEST and exception.getMessage()
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic handler for all other occuring exceptions
     *
     * @param exception exception thrown
     * @return response with status code INTERAL_SERVER_ERROR and exception.getMessage()
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
