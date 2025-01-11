package be.avidoo.consumer.kafka;

import be.avidoo.consumer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consume(String message) {
        LOGGER.info("Received message='{}'", message);
    }

    @KafkaListener(topics = "myTopicJson", groupId = "myGroup", containerFactory = "userFactory")
    public void consume(User user) {
        LOGGER.info("Received message='{}'", user);
    }
}
