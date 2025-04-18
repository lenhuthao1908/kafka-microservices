package com.microservice.kafka.consumer.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.consumer.listener.KafkaConsumerService1Listener;
import com.microservice.kafka.consumer.listener.KafkaConsumerService2Listener;
import com.microservice.kafka.consumer.listener.KafkaConsumerService3Listener;
import com.microservice.kafka.consumer.listener.KafkaConsumerService4Listener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaMiddleware {
    @Autowired
    private KafkaConsumerService1Listener kafkaConsumerService1Listener;
    private KafkaConsumerService2Listener kafkaConsumerService2Listener;
    private KafkaConsumerService3Listener kafkaConsumerService3Listener;
    private KafkaConsumerService4Listener kafkaConsumerService4Listener;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String SERVICE1_TOPIC = "service1_topic";
    private final static String SERVICE2_TOPIC = "service2_topic";
    private final static String SERVICE3_TOPIC = "service3_topic";
    private final static String SERVICE4_TOPIC = "service4_topic";

    @KafkaListener(
            topics = {
                    SERVICE1_TOPIC, SERVICE2_TOPIC, SERVICE3_TOPIC, SERVICE4_TOPIC
            },
            groupId = "consumer-group-1",
            containerFactory = "kafkaListenerContainerFactory"
    )
//    @SendTo("reply_topic")
    public String listen(ConsumerRecord<String, String> request) throws Exception {
        String topic = request.topic();
        String value = request.value();
        String response;

        switch (topic) {
            case SERVICE1_TOPIC -> response = kafkaConsumerService1Listener.listener(value);
            case SERVICE2_TOPIC -> response = kafkaConsumerService2Listener.listener(value);
            case SERVICE3_TOPIC -> response = kafkaConsumerService3Listener.listener(value);
            case SERVICE4_TOPIC -> response = kafkaConsumerService4Listener.listener(value);
            default -> response = "Invalid topic: " + topic;
        }
        return response;
    }
}
