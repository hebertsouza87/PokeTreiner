package com.github.hebertsouza87.pokeTreiner.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.model.TreinerJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TreinerProducer {

    private static final Logger logger = LoggerFactory.getLogger(TreinerProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TreinerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createdTreiner(TreinerEntity treiner) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new TreinerJson(treiner));
            kafkaTemplate.send("createdTreiner", json);
        } catch (JsonProcessingException e) {
            logger.error("Error on send createdTreiner message", e);
        }
    }
}
