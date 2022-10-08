package sample.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("kafka")
@Configuration
public class KafkaTopicConfiguration {

    private KafkaProperties kafkaProperties;

    KafkaTopicConfiguration(final KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public NewTopic sample() {
        return new NewTopic("sample", 4, (short) 1);
    }
}
