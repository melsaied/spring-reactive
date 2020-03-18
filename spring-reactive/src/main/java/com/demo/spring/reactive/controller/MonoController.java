package com.demo.spring.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {
    //    http://localhost:8080/mono
    @GetMapping("/mono")
    public Mono<Integer> getMono() {
        return Mono.just(11)
                .log();
    }
}
