package com.microservice.kafka.consumer.service.impl;

import com.microservice.kafka.consumer.dto.KafkaMessageDto;
import com.microservice.kafka.consumer.entity.MessageEntity;
import com.microservice.kafka.consumer.repository.MessageRepository;
import com.microservice.kafka.consumer.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public String create(KafkaMessageDto kafkaMessageDto) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(kafkaMessageDto.getMessage());
        messageRepository.save(messageEntity);
        return "Successfully created!";
    }

    @Override
    public List<MessageEntity> getList() {
        return messageRepository.findAll();
    }
}
