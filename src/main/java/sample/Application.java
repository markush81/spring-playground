package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by markus on 14/05/16.
 */
@SpringBootApplication
public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("args: {}", (Object[]) args);
        SpringApplication.run(Application.class, args);
    }
}
