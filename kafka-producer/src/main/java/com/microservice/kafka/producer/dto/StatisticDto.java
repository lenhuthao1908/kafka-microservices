package com.microservice.kafka.producer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * StatisticDto
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
@Getter
@Setter
@Builder
public class StatisticDto {
    private String message;
    private Date createdDate;
}
