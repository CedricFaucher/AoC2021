package com.example.aoc2021.dayEight;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EightController {
    private final EightService service;

    public EightController(EightService service) {
        this.service = service;
    }

    @GetMapping("eight/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("eight/secondPart")
    public long secondPart() {
        return service.secondPart();
    }
}
