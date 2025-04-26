package com.microservice.kafka.consumer.service;

import com.microservice.kafka.consumer.dto.StatisticDto;

/**
 * StatisticService
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
public interface StatisticService {
    void saveSendMail(StatisticDto statisticDto);
}
