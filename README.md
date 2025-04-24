# `Kafka Microservices with Spring Boot`

This project integrates `Apache Kafka` into a `microservices architecture` to demonstrate the interaction flow between `producer` and `consumer`.

## `Architecture Overview`

- **Producer**: Sends requests to Kafka topics (`service1_topic`,...) and waits for a response using `ReplyingKafkaTemplate`.
- **Consumer**: Listens to Kafka topics and replies via `@SendTo("reply_topic")`.

## `Technologies Used`

- **Java 21**, **Spring Boot 3**, **Spring Kafka**
- **Apache Kafka**, **Docker Compose**

## `Key Modules`

- **kafka-producer**: Exposes REST API to produce messages.
- **kafka-consumer**: Listens and processes messages from Kafka.
- **docker-compose**: Sets up Kafka & Zookeeper.
- **kafdrop**: UI management kafka.

## `Document kafka configuration confluent platform`

- **kafka-producer**: https://docs.confluent.io/platform/current/clients/producer.html
- **kafka-consumer**: https://docs.confluent.io/platform/current/installation/configuration/consumer-configs.html

## `Getting Started`

To start Kafka and Zookeeper via Docker, run the following command:

```bash
#docker-compose up -d
