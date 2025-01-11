package be.avidoo.producer.kafka;

import be.avidoo.producer.wikimedia.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static be.avidoo.producer.config.KafkaTopicConfiguration.TOPIC_NAME_WIKIMEDIA;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikimediaChangesProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        // read real time data from wikimedia
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, TOPIC_NAME_WIKIMEDIA);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.SECONDS.sleep(10);
    }


}
