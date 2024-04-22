package com.github.hebertsouza87.pokeTreiner.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MetricsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMetricsEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/metrics"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}