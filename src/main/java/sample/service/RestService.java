package sample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sample.persistence.UserRepository;
import sample.persistence.model.User;
import sample.service.model.Greeting;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestService {

    private final UserRepository userRepository;

    @Autowired
    public RestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{user}")
    public ResponseEntity<Greeting> simpleThing(@PathVariable String user) {
        if (user == null || ObjectUtils.isEmpty(user) || "unknown".equals(user)) {
            throw new IllegalArgumentException("This user is invalid!");
        }
        return new ResponseEntity<>(new Greeting(user), HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Iterable<User>> list() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> get(@RequestParam Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
