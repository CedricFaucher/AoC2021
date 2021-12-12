package com.example.aoc2021.dayEleven;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElevenController {
    private final ElevenService service;

    public ElevenController(ElevenService service) {
        this.service = service;
    }

    @GetMapping("eleven/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("eleven/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
