package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by markus on 14/05/16.
 */
@SpringBootApplication
public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    public static BeanFactoryPostProcessor initializeDispatcherServlet() {
        return beanFactory -> {
            BeanDefinition bean = beanFactory.getBeanDefinition(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
            bean.getPropertyValues().add("loadOnStartup", 1);
        };
    }

    public static void main(String[] args) {
        LOGGER.info("args: {}", (Object[]) args);
        SpringApplication.run(Application.class, args);
    }
}
