package com.microservice.kafka.consumer.service;

import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.MessageEntity;

import java.util.List;

public interface ExampleService {
    String create(KafkaMessageDto kafkaMessageDto);
    List<MessageEntity> getList();
}
