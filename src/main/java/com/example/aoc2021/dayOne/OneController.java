package com.example.aoc2021.dayOne;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneController {
    private final OneService service;

    public OneController(OneService service) {
        this.service = service;
    }

    @GetMapping("one/firstPart")
    public String firstPart() {
        return service.firstPart();
    }

    @GetMapping("one/secondPart")
    public String secondPart() {
        return service.secondPart();
    }
}
