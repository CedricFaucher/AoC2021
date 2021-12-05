package com.example.aoc2021.dayFour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FourController {
    private final FourService service;

    public FourController(FourService service) {
        this.service = service;
    }

    @GetMapping("four/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("four/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
