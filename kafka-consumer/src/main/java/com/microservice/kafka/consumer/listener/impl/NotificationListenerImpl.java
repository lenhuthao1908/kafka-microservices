package com.microservice.kafka.consumer.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.consumer.dto.MessageDto;
import com.microservice.kafka.consumer.listener.NotificationListener;
import com.microservice.kafka.consumer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * NotificationListenerImpl
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Component
public class NotificationListenerImpl implements NotificationListener {
    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void listener(String value) throws Exception {
        MessageDto messageDto = objectMapper.readValue(value, MessageDto.class);
        try {
            emailService.sendMail(messageDto);
        } catch (Exception e) {
            throw new Exception("Send email failed: " + e.getMessage());
        }

    }
}
