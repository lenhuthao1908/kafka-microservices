package com.microservice.kafka.consumer.middleware;

import com.microservice.kafka.consumer.listener.StatisticListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * StatisticMiddleware
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Component
public class StatisticMiddleware {
    @Autowired
    private StatisticListener statisticListener;

    private final static String STATISTIC_TOPIC = "statistic_topic";

    @KafkaListener(
            topics = {
                    STATISTIC_TOPIC
            },
            groupId = "consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, String> request) throws Exception {
        String topic = request.topic();
        String value = request.value();
        switch (topic) {
            case STATISTIC_TOPIC -> statisticListener.listener(value);
            default -> throw new Exception("Invalid topic: " + topic);
        }
    }
}
