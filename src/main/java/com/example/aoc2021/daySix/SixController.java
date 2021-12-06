package com.example.aoc2021.daySix;

import com.example.aoc2021.dayFive.FiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SixController {
    private final SixService service;

    public SixController(SixService service) {
        this.service = service;
    }

    @GetMapping("six/firstPart")
    public int firstPart(@RequestParam(value = "numberOfDays") int numberOfDays) {
        return service.firstPart(numberOfDays);
    }

    @GetMapping("six/secondPart")
    public long secondPart(@RequestParam(value = "numberOfDays") int numberOfDays) {
        return service.secondPart(numberOfDays);
    }
}
