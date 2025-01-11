package be.avidoo.producer.controller;

import be.avidoo.producer.kafka.KafkaProducer;
import be.avidoo.producer.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http://localhost:8081/api/v1/kafka/publish?message=hello-world
    @GetMapping("/publish")
    public ResponseEntity<String> publish(
            @RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message send to topic");
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        kafkaProducer.sendUser(user);
        return ResponseEntity.ok("Json Message send to topic");
    }
}
