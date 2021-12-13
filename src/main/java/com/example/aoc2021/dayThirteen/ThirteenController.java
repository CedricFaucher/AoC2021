package com.example.aoc2021.dayThirteen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirteenController {
    private final ThirteenService service;

    public ThirteenController(ThirteenService service) {
        this.service = service;
    }

    @GetMapping("thirteen/firstPart")
    public long firstPart() {
        return service.firstPart();
    }

    @GetMapping("thirteen/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
