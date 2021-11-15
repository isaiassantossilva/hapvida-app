package com.hapvida.hapvidaapp.adapters.rest.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/health")
public class HealthController {
    
    @GetMapping
    public Health health(){
        return new Health("UP");
    }

    @Getter
    @RequiredArgsConstructor
    private final class Health{
        private final String status;
    }
}
