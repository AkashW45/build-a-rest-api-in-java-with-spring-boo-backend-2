package com.example.randomapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(RandomController.class)
class RandomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRandom_shouldReturn200AndNumberBetween1And100() throws Exception {
        mockMvc.perform(get("/random")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.random").isNumber())
                .andExpect(jsonPath("$.random", allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(100))));
    }

    @Test
    void getRandom_multipleCalls_shouldAlwaysReturnNumbersInRange() throws Exception {
        for (int i = 0; i < 10; i++) {
            mockMvc.perform(get("/random")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.random", allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(100))));
        }
    }

    @Test
    void health_shouldReturn200AndStatusOk() throws Exception {
        mockMvc.perform(get("/health")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void randomEndpoint_withPostMethod_shouldReturn405() throws Exception {
        mockMvc.perform(post("/random")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}