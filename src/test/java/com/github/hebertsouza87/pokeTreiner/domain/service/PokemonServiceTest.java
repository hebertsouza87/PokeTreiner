package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.application.repository.PokemonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepo pokemonRepo;

    @InjectMocks
    private PokemonService pokemonService;

    @Test
    public void getPokemonsByTrainerId() {
        PokemonEntity pokemon1 = new PokemonEntity();
        PokemonEntity pokemon2 = new PokemonEntity();
        List<PokemonEntity> expectedPokemons = Arrays.asList(pokemon1, pokemon2);

        given(pokemonRepo.findByTreinerId(1L)).willReturn(expectedPokemons);

        List<PokemonEntity> actualPokemons = pokemonService.getPokemonsByTrainerId(1L);

        assertEquals(expectedPokemons, actualPokemons);
        verify(pokemonRepo).findByTreinerId(1L);
    }
}