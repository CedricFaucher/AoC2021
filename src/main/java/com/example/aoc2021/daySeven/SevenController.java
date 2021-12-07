package com.example.aoc2021.daySeven;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SevenController {
    private final SevenService service;

    public SevenController(SevenService service) {
        this.service = service;
    }

    @GetMapping("seven/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("seven/secondPart")
    public long secondPart() {
        return service.secondPart();
    }
}
