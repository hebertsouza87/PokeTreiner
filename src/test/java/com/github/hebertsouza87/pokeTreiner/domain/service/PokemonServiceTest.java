package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.gateway.PokeApiGateway;
import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import com.github.hebertsouza87.pokeTreiner.application.repository.PokemonRepo;
import com.github.hebertsouza87.pokeTreiner.application.repository.TreinerRepo;
import com.github.hebertsouza87.pokeTreiner.domain.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.domain.entity.TreinerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Mock
    private TreinerService treinerService;

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

    @Test
    public void addPokemonToTreiner() {
        when(treinerService.findByIdWithPokemons(anyLong())).thenReturn(treiner);
        when(pokeApiGateway.getPokemon(anyInt())).thenReturn(PokemonJson.fromModel(pokemon));
        when(pokemonRepo.save(any(PokemonEntity.class))).thenReturn(pokemon);

        PokemonEntity result = pokemonService.addPokemonToTreiner(1L, 25);

        assertEquals("Pikachu", result.getName());
        verify(treinerService).findByIdWithPokemons(anyLong());
        verify(pokeApiGateway).getPokemon(anyInt());
        verify(pokemonRepo).save(any(PokemonEntity.class));
    }

    @Test
    public void getPokemonsByTrainerId() {
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(pokemon);
        when(pokemonRepo.findByTreinerId(anyLong())).thenReturn(pokemons);

        List<PokemonEntity> result = pokemonService.getPokemonsByTrainerId(1L);

        assertEquals(1, result.size());
        assertEquals("Pikachu", result.get(0).getName());
        verify(pokemonRepo).findByTreinerId(anyLong());
    }

    @Test
    public void releasePokemon() {
        when(pokemonRepo.findById(anyLong())).thenReturn(Optional.of(pokemon));

        pokemonService.releasePokemon(1L);

        verify(pokemonRepo).findById(anyLong());
        verify(pokemonRepo).delete(any(PokemonEntity.class));
    }

    @Test
    public void addPokemonToTreiner_MaxLimitExceeded() {
        when(treinerService.findByIdWithPokemons(anyLong())).thenReturn(treiner);
        List<PokemonEntity> pokemons = new ArrayList<>();
        for (int i = 0; i < PokemonService.MAX_POKEMONS_PER_TRAINER; i++) {
            pokemons.add(new PokemonEntity());
        }
        treiner.setPokemons(pokemons);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pokemonService.addPokemonToTreiner(1L, 25);
        });

        String expectedMessage = "Treiner already has 6 pokemons";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getPokemonById_NotFound() {
        when(pokemonRepo.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pokemonService.getPokemonById(1L);
        });

        String expectedMessage = "Pokemon not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}