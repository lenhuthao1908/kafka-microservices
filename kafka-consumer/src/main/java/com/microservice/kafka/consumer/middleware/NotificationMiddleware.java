package com.microservice.kafka.consumer.middleware;

import com.microservice.kafka.consumer.listener.ExampleListener;
import com.microservice.kafka.consumer.listener.NotificationListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * NotificationMiddleware
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Component
public class NotificationMiddleware {
    @Autowired
    private NotificationListener notificationListener;

    private final static String NOTIFICATION_TOPIC = "notification_topic";
    private final static String STATISTIC_TOPIC = "statistic_topic";

    @KafkaListener(
            topics = {
                    NOTIFICATION_TOPIC
            },
            groupId = "consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, String> request) throws Exception {
        String topic = request.topic();
        String value = request.value();
        switch (topic) {
            case NOTIFICATION_TOPIC -> notificationListener.listener(value);
            case STATISTIC_TOPIC -> {}
            default -> throw new Exception("Invalid topic: " + topic);
        }
    }
}
