package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by markus on 14/05/16.
 */
public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public Main() {
    }

    public String greet() {
        LOGGER.debug("called greet() at {}", System.currentTimeMillis());
        return String.format("Hello %s!", System.getProperty("user.name"));
    }

    public static void main(String[] args) {
        System.out.println(new Main().greet());
    }
}
