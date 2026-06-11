package com.example.randomapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RandomApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        // verifies the application context starts successfully
    }

    @Test
    void healthEndpointShouldReturn200() {
        ResponseEntity<String> response = restTemplate.getForEntity("/health", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void randomEndpointShouldReturnValidNumber() {
        ResponseEntity<Integer> response = restTemplate.getForEntity("/random", Integer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Integer number = response.getBody();
        assertThat(number).isNotNull().isBetween(1, 100);
    }

    @Test
    void randomEndpointShouldReturnJsonContentType() {
        ResponseEntity<String> response = restTemplate.getForEntity("/random", String.class);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }

    @Test
    void randomEndpointMultipleCallsShouldReturnNumbersInRange() {
        for (int i = 0; i < 10; i++) {
            Integer number = restTemplate.getForObject("/random", Integer.class);
            assertThat(number).isBetween(1, 100);
        }
    }
}