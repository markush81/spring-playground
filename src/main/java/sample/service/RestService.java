package sample.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<Object>(greet(user), HttpStatus.OK);
    }

    String greet(String user) {
        return String.format("Hello %s!", user);
    }

}
