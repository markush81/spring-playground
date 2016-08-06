package sample.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sample.service.model.Greeting;

/**
 * Created by markus on 11/06/16.
 */
@RestController
@RequestMapping(path = "/service", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class RestService {

    @GetMapping(path = "/hello/{user}")
    public ResponseEntity<sample.service.model.Greeting> simpleThing(@PathVariable String user) {
        if (user == null || StringUtils.isEmpty(user) || "unknown".equals(user)) {
            throw new IllegalArgumentException("This user is invalid!");
        }
        return new ResponseEntity<>(new sample.service.model.Greeting(user), HttpStatus.OK);
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
