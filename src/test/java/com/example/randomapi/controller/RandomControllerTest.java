package com.example.randomapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RandomController.class)
class RandomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRandomNumberBetween1And100() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.random").isNumber())
                .andExpect(jsonPath("$.random").value(org.hamcrest.Matchers.greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.random").value(org.hamcrest.Matchers.lessThanOrEqualTo(100)));
    }

    @Test
    void shouldReturnHealthStatusOk() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }
}
