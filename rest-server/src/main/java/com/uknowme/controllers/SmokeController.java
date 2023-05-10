package com.uknowme.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/smoke")
@AllArgsConstructor
public class SmokeController {

    private record SmokeResponse(String message) {
    }

    @GetMapping
    public SmokeResponse getSmoke() {
        return new SmokeResponse("Smoke test passed!");
    }
}
