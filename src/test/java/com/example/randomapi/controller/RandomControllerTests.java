package com.example.randomapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RandomController.class)
class RandomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRandom_returnsJsonWithRandomNumberBetween1And100() throws Exception {
        mockMvc.perform(get("/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.random").isNumber())
                .andExpect(jsonPath("$.random").value(
                        org.hamcrest.Matchers.both(
                                org.hamcrest.Matchers.greaterThanOrEqualTo(1))
                                .and(org.hamcrest.Matchers.lessThanOrEqualTo(100))));
    }

    @Test
    void health_returnsStatusOk() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void home_returnsHtmlPageWithButton() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(xpath("//h1").string("Get Random Number"))
                .andExpect(xpath("//button[@id='randomBtn']").string("Get Random Number"))
                .andExpect(xpath("//script").exists());
    }

    @Test
    void getRandom_withInvalidMethod_returnsMethodNotAllowed() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/random"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void invalidEndpoint_returnsNotFound() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }
}