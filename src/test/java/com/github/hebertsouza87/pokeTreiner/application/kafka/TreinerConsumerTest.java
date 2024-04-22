package com.github.hebertsouza87.pokeTreiner.application.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hebertsouza87.pokeTreiner.application.model.TreinerJson;
import com.github.hebertsouza87.pokeTreiner.domain.service.PokemonService;
import com.github.hebertsouza87.pokeTreiner.domain.service.TreinerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TreinerConsumerTest {

    @Mock
    private PokemonService pokemonService;

    @Mock
    private TreinerService treinerService;

    private TreinerConsumer treinerConsumer;

    @BeforeEach
    void setUp() {
        treinerConsumer = new TreinerConsumer(pokemonService, treinerService);
    }

    @Test
    void consume() throws Exception {
        String message = "{\"id\":1}";
        TreinerJson treinerJson = new ObjectMapper().readValue(message, TreinerJson.class);

        treinerConsumer.consume(message);

        verify(treinerService, times(1)).findById(treinerJson.getId());
        verify(pokemonService, times(1)).giveStarterPokemon(any());
    }
}