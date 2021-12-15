package com.example.aoc2021.dayFourteen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FourteenController {
    private final FourteenService service;

    public FourteenController(FourteenService service) {
        this.service = service;
    }

    @GetMapping("fourteen/firstPart")
    public long firstPart(@RequestParam(value = "steps") int steps) {
        return service.firstPart(steps);
    }

    @GetMapping("fourteen/secondPart")
    public long secondPart(@RequestParam(value = "steps") int steps) {
        return service.secondPart(steps);
    }
}
