package com.example.aoc2021.dayThree;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreeController {
    private final ThreeService service;

    public ThreeController(ThreeService service) {
        this.service = service;
    }

    @GetMapping("three/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("three/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
