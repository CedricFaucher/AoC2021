package com.example.aoc2021.dayNine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NineController {
    private final NineService service;

    public NineController(NineService service) {
        this.service = service;
    }

    @GetMapping("nine/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("nine/secondPart")
    public long secondPart() {
        return service.secondPart();
    }
}
