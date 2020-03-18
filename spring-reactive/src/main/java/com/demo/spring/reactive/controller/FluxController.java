package com.demo.spring.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FluxController {
    //    http://localhost:8080/flux
    @GetMapping("/flux")
    public Flux<Integer> getFlux() {
        return Flux.just(11, 22, 33)
                .log();
    }

    //    http://localhost:8080/fluxDelay
    @GetMapping("/fluxDelay")
    public Flux<Integer> getFluxDelay() {
        return Flux.just(11, 22, 33)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    //    http://localhost:8080/fluxStream
    @GetMapping(value = "/fluxStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFluxStream() {
        return Flux.just(11, 22, 33)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}
