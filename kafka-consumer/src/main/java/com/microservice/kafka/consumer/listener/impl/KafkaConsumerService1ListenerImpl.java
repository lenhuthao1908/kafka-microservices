package com.microservice.kafka.consumer.listener.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.KafkaMessageEntity;
import com.microservice.kafka.consumer.listener.KafkaConsumerService1Listener;
import com.microservice.kafka.consumer.service.KafkaConsumerService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumerService1ListenerImpl implements KafkaConsumerService1Listener {
    @Autowired
    private KafkaConsumerService1 kafkaConsumerService1;

    private final static String CREATE_TYPE = "create";
    private final static String UPDATE_TYPE = "update";
    private final static String DELETE_TYPE = "delete";
    private final static String LIST_TYPE = "list";


    @Override
    public String listener(String value) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaMessageDto kafkaMessageDto = objectMapper.readValue(value, KafkaMessageDto.class);

        if (kafkaMessageDto == null || kafkaMessageDto.getType() == null) {
            return objectMapper.writeValueAsString(
                    ResponseEntity.badRequest().body("Invalid message format").getBody()
            );
        }

        switch (kafkaMessageDto.getType()) {
            case CREATE_TYPE -> {
                kafkaConsumerService1.create(kafkaMessageDto);
                return objectMapper.writeValueAsString(
                        ResponseEntity.ok("Created successfully").getBody()
                );
            }
            case LIST_TYPE -> {
                List<KafkaMessageEntity> list = kafkaConsumerService1.getList(); // giả sử trả List<String>
                return objectMapper.writeValueAsString(list);
            }
            default -> {
                return objectMapper.writeValueAsString(
                        ResponseEntity.badRequest().body("Unsupported message type").getBody()
                );
            }
        }
    }


}
