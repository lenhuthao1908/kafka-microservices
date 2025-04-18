package com.microservices.kafka.test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.restassured.RestAssured.given;

/**
 * KafkaProducerTest
 *
 * @author haoln
 * @version 01-00
 * @since 4/18/2025
 */
public class KafkaProducerTest {

    private static final Faker faker = new Faker();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void testPostRequestsInParallel() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            executor.submit(() -> {
                try {
                    String topic = "service1_topic";
                    String message = faker.pokemon().name();
                    String type = "create";

                    String json = String.format(
                            "{\"topic\":\"%s\",\"message\":\"%s\",\"type\":\"%s\"}",
                            topic, message, type
                    );

                    given()
                            .header("Content-Type", "application/json")
                            .body(json)
                            .when()
                            .post("/api/v1/message/create")
                            .then()
                            .statusCode(200);

                    System.out.println("Sent: " + message);
                } catch (Exception e) {
                    System.err.println("Error request: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
            System.out.printf("Count post request: %d\n", i);
        }
        latch.await();
        executor.shutdown();
    }

    @Test
    public void testGetListDataConsumerInParallel() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            executor.submit(() -> {
                try {
                    String topic = "service1_topic";
                    String type = "list";

                    given()
                            .header("Content-Type", "application/json")
                            .when()
                            .get("/api/v1/message/list/" + topic)
                            .then()
                            .statusCode(200);

                    System.out.printf("GET success: /list/%s\n", topic);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
            System.out.printf("Count get request: %d\n", i);
        }
        latch.await();
        executor.shutdown();
    }
}
