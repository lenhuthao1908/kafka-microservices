package com.microservice.kafka.consumer.service.impl;

import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.KafkaMessageEntity;
import com.microservice.kafka.consumer.repository.KafkaMessageRepository;
import com.microservice.kafka.consumer.service.KafkaConsumerService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService1Impl implements KafkaConsumerService1 {
    @Autowired
    private KafkaMessageRepository kafkaMessageRepository;

    @Override
    public String create(KafkaMessageDto kafkaMessageDto) {
        KafkaMessageEntity kafkaMessageEntity = new KafkaMessageEntity();
        kafkaMessageEntity.setMessage(kafkaMessageDto.getMessage());
        kafkaMessageRepository.save(kafkaMessageEntity);
        return "Successfully created!";
    }

    @Override
    public List<KafkaMessageEntity> getList() {
        return kafkaMessageRepository.findAll();
    }
}
