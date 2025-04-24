package com.microservice.kafka.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.producer.common.ApiCommon;
import com.microservice.kafka.producer.dto.AccountDto;
import com.microservice.kafka.producer.dto.MessageDto;
import com.microservice.kafka.producer.dto.StatisticDto;
import com.microservice.kafka.producer.enums.TopicEnums;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * AccountController
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
@RestController
@RequestMapping(ApiCommon.API_V1_URI + ApiCommon.ACCOUNT_URI)
public class AccountController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(ApiCommon.CREATE_URI)
    public AccountDto createAccount(@RequestBody AccountDto accountDto) throws JsonProcessingException {
        StatisticDto statisticDto = StatisticDto
                .builder()
                .message("Account: " + accountDto.getEmail() + " created")
                .createdDate(new Date())
                .build();
        /*send notification*/
        MessageDto messageDto = MessageDto.builder()
                .to(accountDto.getEmail())
                .toName(accountDto.getName())
                .subject("Hello " + accountDto.getName() + "!")
                .content("I leaning kafka")
                .build();
        kafkaTemplate.send(new ProducerRecord<>(TopicEnums.NOTIFICATION_TOPIC.getName(), objectMapper.writeValueAsString(messageDto)));
        kafkaTemplate.send(new ProducerRecord<>(TopicEnums.STATISTIC_TOPIC.getName(), objectMapper.writeValueAsString(statisticDto)));
        return accountDto;
    }
}
