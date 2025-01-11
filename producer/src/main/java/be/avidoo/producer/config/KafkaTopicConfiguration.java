package be.avidoo.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    public static final String TOPIC_NAME = "myTopic";
    public static final String TOPIC_NAME_JSON = TOPIC_NAME + "Json";
    public static final String TOPIC_NAME_WIKIMEDIA = "wikimedia_recentchange";

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME)
                .build();
    }

    @Bean
    public NewTopic topicJson() {
        return TopicBuilder.name(TOPIC_NAME_JSON)
                .build();
    }

    @Bean
    public NewTopic topicWikimedia() {
        return TopicBuilder.name(TOPIC_NAME_WIKIMEDIA)
                .build();
    }
}
