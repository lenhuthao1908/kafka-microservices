package com.microservice.kafka.consumer.service.impl;

import com.microservice.kafka.consumer.dto.StatisticDto;
import com.microservice.kafka.consumer.entity.StatisticEntity;
import com.microservice.kafka.consumer.repository.StatisticRepository;
import com.microservice.kafka.consumer.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * StatisticServiceImpl
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public void saveSendMail(StatisticDto statisticDto) {
        try {
            StatisticEntity statisticEntity = StatisticEntity
                    .builder()
                    .message(statisticDto.getMessage())
                    .createdDate(new Date())
                    .build();

            statisticRepository.save(statisticEntity);
        }  catch (Exception e) {
            log.error("save statistic failed: " + e.getMessage());
        }
    }
}
