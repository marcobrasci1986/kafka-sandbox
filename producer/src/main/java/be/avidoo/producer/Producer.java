package be.avidoo.producer;

import be.avidoo.producer.kafka.WikimediaChangesProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Producer implements CommandLineRunner {

    private final WikimediaChangesProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producer.sendMessage();
    }
}
