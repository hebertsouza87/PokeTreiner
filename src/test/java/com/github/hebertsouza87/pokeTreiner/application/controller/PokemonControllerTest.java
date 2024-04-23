package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.domain.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;


    @Test
    void getPokemonsByTrainerId() throws Exception {
        when(pokemonService.getPokemonsByTrainerId(1L)).thenReturn(Collections.singletonList(new PokemonEntity(1L, "Pikachu", 25, 1)));

        mockMvc.perform(get("/treiner/1/pokemon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void pokemonLevelUp() throws Exception {
        when(pokemonService.levelUp(1L)).thenReturn(new PokemonEntity(1L, "Pikachu", 25, 2));

        mockMvc.perform(put("/treiner/1/pokemon/1/levelUp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}