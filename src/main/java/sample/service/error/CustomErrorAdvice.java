package sample.service.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomErrorAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorAdvice.class);

    /**
     * Example for handling one specific exception, like IllegalArgumentException which code be an indicator for bad requests
     *
     * @param exception exception thrown
     *
     * @return response with status code BAD_REQUEST and exception.getMessage()
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleException(IllegalArgumentException exception) {
        LOGGER.error("Exception {}", exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic handler for all other occuring exceptions
     *
     * @param exception exception thrown
     *
     * @return response with status code INTERAL_SERVER_ERROR and exception.getMessage()
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(RuntimeException exception) {
        LOGGER.error("Exception {}", exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
