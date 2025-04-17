package com.microservice.kafka.consumer.service;

import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.KafkaMessageEntity;

import java.util.List;

public interface KafkaConsumerService1 {
    String create(KafkaMessageDto kafkaMessageDto);
    List<KafkaMessageEntity> getList();
}
