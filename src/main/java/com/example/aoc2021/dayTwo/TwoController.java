package com.example.aoc2021.dayTwo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwoController {
    private final TwoService service;

    public TwoController(TwoService service) {
        this.service = service;
    }

    @GetMapping("two/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("two/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
