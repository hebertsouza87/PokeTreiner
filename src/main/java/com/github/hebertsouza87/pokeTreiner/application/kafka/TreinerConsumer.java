package com.github.hebertsouza87.pokeTreiner.application.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hebertsouza87.pokeTreiner.application.model.TreinerJson;
import com.github.hebertsouza87.pokeTreiner.domain.service.PokemonService;
import com.github.hebertsouza87.pokeTreiner.domain.service.TreinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TreinerConsumer {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TreinerConsumer.class);

    private final PokemonService pokemonService;
    private final TreinerService treinerService;

    @Autowired
    public TreinerConsumer(PokemonService pokemonService, TreinerService treinerService) {
        this.pokemonService = pokemonService;
        this.treinerService = treinerService;
    }

    @KafkaListener(topics = "createdTreiner", groupId = "${kafka.consumer.group-id}")
    public void consume(String message) throws IOException {
        TreinerJson treinerJson = new ObjectMapper().readValue(message, TreinerJson.class);
        try {
            pokemonService.giveStarterPokemon(treinerService.findById(treinerJson.getId()));
        } catch (Exception e) {
            log.error("Error while giving starter pokemon to treiner", e);
        }
    }
}