package com.example.randomapi.controller;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {

    @GetMapping("/random")
    public ResponseEntity<Map<String, Integer>> getRandom() {
        int number = ThreadLocalRandom.current().nextInt(1, 101);
        return ResponseEntity.ok(Map.of("random", number));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Random Number Generator</title>
            </head>
            <body>
                <h1>Get Random Number</h1>
                <button id="randomBtn">Get Random Number</button>
                <p id="result"></p>
                <script>
                    document.getElementById('randomBtn').addEventListener('click', async () => {
                        const response = await fetch('/random');
                        const data = await response.json();
                        document.getElementById('result').textContent = 'Random number: ' + data.random;
                    });
                </script>
            </body>
            </html>
            """;
    }
}
