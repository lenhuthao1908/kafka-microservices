package com.microservice.kafka.consumer.middleware;

import com.microservice.kafka.consumer.listener.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExampleMiddleware {
    @Autowired
    private ExampleListener exampleListener;

    @Autowired
    private NotificationListener notificationListener;

    private final static String EXAMPLE_TOPIC = "example_topic";
    private final static String NOTIFICATION_TOPIC = "notification_topic";
    private final static String STATISTIC_TOPIC = "statistic_topic";

    @KafkaListener(
            topics = {
                    EXAMPLE_TOPIC,
                    NOTIFICATION_TOPIC,
                    STATISTIC_TOPIC
            },
            groupId = "consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, String> request) throws Exception {
        String topic = request.topic();
        String value = request.value();
        switch (topic) {
            case EXAMPLE_TOPIC -> exampleListener.listener(value);
            case NOTIFICATION_TOPIC -> notificationListener.listener(value);
            default -> throw new Exception("Invalid topic: " + topic);
        }
    }
}
