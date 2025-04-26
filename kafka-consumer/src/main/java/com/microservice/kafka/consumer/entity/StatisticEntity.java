package com.microservice.kafka.consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * StatisticEntity
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Entity
@Table(name = "kafka_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private Date createdDate;
}
