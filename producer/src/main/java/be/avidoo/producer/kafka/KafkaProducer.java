package be.avidoo.producer.kafka;

import be.avidoo.producer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static be.avidoo.producer.config.KafkaTopicConfiguration.TOPIC_NAME;
import static be.avidoo.producer.config.KafkaTopicConfiguration.TOPIC_NAME_JSON;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, User> kafkaUserTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, User> kafkaUserTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaUserTemplate = kafkaUserTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message='{}' to topic='{}'", message, TOPIC_NAME);
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendUser(User user) {
        LOGGER.info("Sending message='{}' to topic='{}'", user, TOPIC_NAME_JSON);

        Message<User> kafkaMessage = MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME_JSON)
                .build();
        kafkaUserTemplate.send(kafkaMessage);
    }
}
