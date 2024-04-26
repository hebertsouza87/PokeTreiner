package com.github.hebertsouza87.pokeTreiner.application.kafka;

import com.github.hebertsouza87.pokeTreiner.domain.entity.TreinerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class TreinerProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private Logger logger;

    private TreinerProducer treinerProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        treinerProducer = new TreinerProducer(kafkaTemplate);
    }

    @Test
    void shouldSendTreinerToKafka() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@pokemon.com", 10);

        assertTrue(treinerProducer.createdTreiner(treiner));

        verify(kafkaTemplate).send(eq("createdTreiner"), any(String.class));
    }

    @Test
    void shouldLogErrorWhenKafkaExceptionOccurs() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@pokemon.com", 10);

        doThrow(new KafkaException("Test exception")).when(kafkaTemplate).send(any(), any());

        assertFalse(treinerProducer.createdTreiner(treiner));


    }
}