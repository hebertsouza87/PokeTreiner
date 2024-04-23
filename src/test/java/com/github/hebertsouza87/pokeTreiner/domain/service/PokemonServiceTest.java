package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.gateway.PokeApiGateway;
import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import com.github.hebertsouza87.pokeTreiner.application.repository.PokemonRepo;
import com.github.hebertsouza87.pokeTreiner.application.repository.TreinerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokeApiGateway pokeApiGateway;

    @Mock
    private PokemonRepo pokemonRepo;

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private TreinerRepo treinerRepo;

    private PokemonEntity pokemon;
    private TreinerEntity treiner;

    @BeforeEach
    void setUp() {
        pokemon = new PokemonEntity(1L, "Pikachu", 25, 1);
        treiner = new TreinerEntity("Ash Ketchum", "ash@pokemon.com", 11);
        treiner.setPokemons(new ArrayList<>());
    }

    @Test
    public void giveStarterPokemon() {
        when(pokeApiGateway.getPokemon(anyInt())).thenReturn(PokemonJson.fromModel(pokemon));
        when(pokemonRepo.save(any(PokemonEntity.class))).thenReturn(new PokemonEntity(1L, "Pikachu", 25, 1));

        PokemonEntity result = pokemonService.giveStarterPokemon(treiner);

        assertEquals(1, result.getLevel());
        verify(pokeApiGateway).getPokemon(anyInt());
        verify(pokemonRepo).save(any(PokemonEntity.class));
    }

    @Test
    public void levelUp() {
        when(pokemonRepo.findById(anyLong())).thenReturn(java.util.Optional.of(pokemon));
        when(pokemonRepo.save(any(PokemonEntity.class))).thenReturn(pokemon);

        PokemonEntity result = pokemonService.levelUp(1L);

        assertEquals(2, result.getLevel());
        verify(pokemonRepo).findById(anyLong());
        verify(pokemonRepo).save(any(PokemonEntity.class));
    }
}