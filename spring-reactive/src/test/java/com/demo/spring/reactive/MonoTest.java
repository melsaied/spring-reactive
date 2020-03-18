package com.demo.spring.reactive;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {
    @Test
    public void monoTest() {
        Mono<String> message = Mono.just("message 0");

        message.subscribe(System.out::println);
    }

    @Test
    public void monoElementTest_WithoutError() {
        Mono<String> message = Mono.just("message 0");

        StepVerifier.create(message.log())
                .expectNext("message 0")
                .verifyComplete();
    }


    @Test
    public void monoElementTest_WithError() {
        StepVerifier.create(Mono.error(new RuntimeException("Error message 1")).log())
//                .expectError()
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Error message 1")
                .verify();
    }
}

