package com.example.aoc2021.dayFive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FiveController {
    private final FiveService service;

    public FiveController(FiveService service) {
        this.service = service;
    }

    @GetMapping("five/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("five/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
