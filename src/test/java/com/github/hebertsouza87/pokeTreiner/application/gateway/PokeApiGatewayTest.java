package com.github.hebertsouza87.pokeTreiner.application.gateway;

import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PokeApiGatewayTest {

    @Mock
    private RestTemplate restTemplate;

    private PokeApiGateway pokeApiGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokeApiGateway = new PokeApiGateway();
        pokeApiGateway.setRestTemplate(restTemplate);
        pokeApiGateway.setApiUrl("http://pokeapi.co/api/v2");
    }

    @Test
    void getPokemon() {
        PokemonJson expectedPokemon = new PokemonJson("bulbasaur", 1);
        when(restTemplate.getForEntity(any(String.class), eq(PokemonJson.class), eq(1)))
                .thenReturn(new ResponseEntity<>(expectedPokemon, HttpStatus.OK));

        PokemonJson result = pokeApiGateway.getPokemon(1);

        assertEquals(expectedPokemon, result);
    }

    @Test
    void getPokemonFallback() {
        PokemonJson expectedPokemon = new PokemonJson("pickachu", 25);

        PokemonJson result = pokeApiGateway.getPokemonFallback(1, new RuntimeException());

        assertEquals(expectedPokemon, result);
    }
}