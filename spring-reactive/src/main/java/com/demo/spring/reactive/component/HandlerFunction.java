package com.demo.spring.reactive.component;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

//@Controller
@Component
public class HandlerFunction {
    public Mono<ServerResponse> monoServer(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(11).log(), Integer.class);
    }

    public Mono<ServerResponse> fluxServer(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(11, 22, 33)
                        .delayElements(Duration.ofSeconds(1))
                        .log(), Integer.class);
    }
}
