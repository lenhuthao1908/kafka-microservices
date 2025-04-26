package com.microservice.kafka.consumer.repository;

import com.microservice.kafka.consumer.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StatisticRepository
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, Long> {
}
