package com.example.aoc2021.dayTen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenController {
    private final TenService service;

    public TenController(TenService service) {
        this.service = service;
    }

    @GetMapping("ten/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("ten/secondPart")
    public double secondPart() {
        return service.secondPart();
    }
}
