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
}
