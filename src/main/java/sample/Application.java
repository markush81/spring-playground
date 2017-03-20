package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by markus on 14/05/16.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("args: {}", (Object[]) args);
        new SpringApplicationBuilder(Application.class)
                .listeners((ApplicationListener<ApplicationFailedEvent>) event -> System.exit(-1))
                .run(args);
    }
}
