package com.example.aoc2021.dayTwelve;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwelveController {
    private final TwelveService service;

    public TwelveController(TwelveService service) {
        this.service = service;
    }

    @GetMapping("twelve/firstPart")
    public int firstPart() {
        return service.firstPart();
    }

    @GetMapping("twelve/secondPart")
    public int secondPart() {
        return service.secondPart();
    }
}
