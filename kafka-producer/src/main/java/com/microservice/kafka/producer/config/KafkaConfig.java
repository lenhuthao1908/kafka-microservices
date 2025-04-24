package com.microservice.kafka.producer.config;

import com.microservice.kafka.producer.enums.TopicEnums;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private static final String REPLY_TOPIC = "reply_topic";

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configs.put(ProducerConfig.ACKS_CONFIG, "all");
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "reply_group");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

//    @Bean
//    public ConcurrentMessageListenerContainer<String, String> replyContainer(
//            ConsumerFactory<String, String> consumerFactory) {
//
//        ContainerProperties containerProperties = new ContainerProperties(REPLY_TOPIC);
//        return new ConcurrentMessageListenerContainer<>(consumerFactory, containerProperties);
//    }

//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
//            ProducerFactory<String, String> pf,
//            ConcurrentMessageListenerContainer<String, String> repliesContainer) {
//        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
//    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(4);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaAdmin.NewTopics defaultTopics() {
        List<NewTopic> topicList = new ArrayList<>();
        for (TopicEnums topic : TopicEnums.values()) {
            NewTopic newTopic = new NewTopic(topic.getName(), topic.getNumPartitions(), topic.getReplicationFactor());
            topicList.add(newTopic);
        }

        return new KafkaAdmin.NewTopics(topicList.toArray(new NewTopic[0]));
    }
}

