package com.microservice.kafka.consumer.listener.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.MessageEntity;
import com.microservice.kafka.consumer.listener.ExampleListener;
import com.microservice.kafka.consumer.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleListenerImpl implements ExampleListener {
    @Autowired
    private ExampleService exampleService;

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
                exampleService.create(kafkaMessageDto);
                return objectMapper.writeValueAsString(
                        ResponseEntity.ok("Created successfully").getBody()
                );
            }
            case LIST_TYPE -> {
                List<MessageEntity> list = exampleService.getList();
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
