package sample.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by markus on 06/08/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {

    private String name;
    private static final String MESSAGE = "Hello %s!";

    public Greeting(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getMessage() {
        return String.format(MESSAGE, name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greeting greeting = (Greeting) o;
        return Objects.equals(name, greeting.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public String toString() {
        return "Greeting{" +
                "name='" + name + '\'' +
                '}';
    }
}
