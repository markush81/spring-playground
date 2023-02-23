package sample.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.service.model.Greeting;

@Profile("kafka")
@RestController
@RequestMapping(path = "/kafka-reset", produces = {MediaType.APPLICATION_JSON_VALUE})
public class KafkaResetOffsetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaResetOffsetController.class);

    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private KafkaProperties kafkaProperties;
    private AdminClient adminClient;

    KafkaResetOffsetController(final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry, final KafkaProperties kafkaProperties) {
        this.adminClient = AdminClient.create(kafkaProperties.buildAdminProperties());
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{groupId}/{topic}", "/{groupId}/{topic}/{offset}"})
    public ResponseEntity<Greeting> resetGroupToOffset(@PathVariable String groupId, @PathVariable String topic, @PathVariable(required = false) Optional<Long> offset) {
        LOGGER.info("Reseting {} to {}", topic, offset);
        kafkaListenerEndpointRegistry.getAllListenerContainers().forEach(messageListenerContainer -> {
            if (messageListenerContainer.getGroupId().equals(groupId)) {
                var newOffset = new OffsetAndMetadata(offset.orElse(0L));
                var offsets = new HashMap<TopicPartition, OffsetAndMetadata>();
                messageListenerContainer.getAssignedPartitions().forEach(topicPartition -> {
                    if (topicPartition.topic().equals(topic)) {
                        offsets.put(topicPartition, newOffset);
                    }
                });
                messageListenerContainer.stop();
                adminClient.alterConsumerGroupOffsets(messageListenerContainer.getGroupId(), offsets);
                messageListenerContainer.start();
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
