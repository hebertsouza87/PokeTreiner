package com.github.hebertsouza87.pokeTreiner.application.kafka;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class TreinerProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private TreinerProducer treinerProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        treinerProducer = new TreinerProducer(kafkaTemplate);
    }

    @Test
    void shouldSendTreinerToKafka() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@pokemon.com", 10);
        treinerProducer.createdTreiner(treiner);

        verify(kafkaTemplate).send(eq("createdTreiner"), any(String.class));
    }
}