package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sample.persistence.UserRepository;
import sample.persistence.model.User;
import sample.service.model.Greeting;

/**
 * Created by markus on 11/06/16.
 */
@RestController
@RequestMapping(path = "/service", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class RestService {

    private final UserRepository userRepository;

    @Autowired
    public RestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/hello/{user}")
    public ResponseEntity<Greeting> simpleThing(@PathVariable String user) {
        if (user == null || StringUtils.isEmpty(user) || "unknown".equals(user)) {
            throw new IllegalArgumentException("This user is invalid!");
        }
        return new ResponseEntity<>(new Greeting(user), HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<User>> list() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> get(@RequestParam Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
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
