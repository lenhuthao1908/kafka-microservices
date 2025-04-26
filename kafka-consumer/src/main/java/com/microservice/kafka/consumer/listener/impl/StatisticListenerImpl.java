package com.microservice.kafka.consumer.listener.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.consumer.dto.StatisticDto;
import com.microservice.kafka.consumer.listener.StatisticListener;
import com.microservice.kafka.consumer.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * StatisticListenerImpl
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Component
public class StatisticListenerImpl implements StatisticListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StatisticService statisticService;

    @Override
    public void listener(String value) throws Exception {
        StatisticDto statisticDto = objectMapper.readValue(value, StatisticDto.class);
        try {
            statisticService.saveSendMail(statisticDto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
