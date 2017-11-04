package sample;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Bean
    public EmbeddedServletContainerCustomizer tomcatCustomizer(TomcatGracefulShutdown tomcatGracefulShutdown) {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers(tomcatGracefulShutdown);
            }
        };
    }

    @Component
    class TomcatGracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

        private volatile Connector connector;

        @Override
        public void customize(Connector connector) {
            this.connector = connector;
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            if (this.connector != null) {
                this.connector.pause();
                Executor executor = this.connector.getProtocolHandler().getExecutor();
                if (executor instanceof ThreadPoolExecutor) {
                    try {
                        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                        threadPoolExecutor.shutdown();
                        if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                            LOGGER.warn("Tomcat thread pool did not shut down gracefully within 30 seconds. Proceeding with forceful shutdown");
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

    }
}
