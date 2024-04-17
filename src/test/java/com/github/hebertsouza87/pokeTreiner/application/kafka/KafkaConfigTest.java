package com.github.hebertsouza87.pokeTreiner.application.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class KafkaConfigTest {

    @Autowired
    private ProducerFactory<String, String> producerFactory;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @Test
    public void producerFactory_ShouldBeConfigured() {
        assertNotNull(producerFactory);
    }

    @Test
    public void kafkaTemplate_ShouldBeConfigured() {
        assertNotNull(kafkaTemplate);
    }

    @Test
    public void consumerFactory_ShouldBeConfigured() {
        assertNotNull(consumerFactory);
    }
}