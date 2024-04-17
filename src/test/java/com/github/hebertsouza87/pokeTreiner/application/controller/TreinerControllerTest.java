package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.domain.service.TreinerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TreinerControllerBehaviorTest {

    @Mock
    private TreinerService treinerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TreinerController treinerController = new TreinerController(treinerService);
        mockMvc = MockMvcBuilders.standaloneSetup(treinerController).build();
    }

    @Test
    void register() throws Exception {
        when(treinerService.register(any(TreinerEntity.class))).thenReturn(new TreinerEntity());

        mockMvc.perform(post("/treiner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ash\",\"email\":\"ash@pokemon.com\",\"age\":10}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getTreiner() throws Exception {
        when(treinerService.findByIdWithPokemons(eq(1L))).thenReturn(new TreinerEntity());

        mockMvc.perform(get("/treiner/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTreiner() throws Exception {
        when(treinerService.updateTreiner(eq(1L), any(TreinerEntity.class))).thenReturn(new TreinerEntity());

        mockMvc.perform(put("/treiner/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ash\",\"email\":\"ash@pokemon.com\",\"age\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTreiner() throws Exception {
        mockMvc.perform(delete("/treiner/1"))
                .andExpect(status().isNoContent());
    }
}