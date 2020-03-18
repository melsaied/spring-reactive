package com.demo.spring.reactive;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
public class FluxTest {
    @Test
    public void fluxTest() {
        Flux<String> messages = Flux.just("message 0", "message 1", "message 2", "message 3", "message 4");
        messages.subscribe(System.out::println);
    }

    @Test
    public void fluxTestErr() {
        Flux<String> messages = Flux.just("message 0", "message 1", "message 2", "message 3", "message 4")
                .concatWith(Flux.error(new RuntimeException("Error message 1")))
                .concatWith(Flux.just("message 5"))
                .log();

        messages.subscribe(System.out::println);
        messages.subscribe(System.out::println, (e) -> System.err.println("Exception is : " + e));
    }

    @Test
    public void fluxElementsTest_WithoutError() {
        Flux<String> messages = Flux.just("message 0", "message 1", "message 2", "message 3", "message 4")
                .log();

        StepVerifier.create(messages)
                .expectNextCount(5)
                .verifyComplete();

        StepVerifier.create(messages)
                .expectNext("message 0", "message 1", "message 2", "message 3", "message 4")
                .verifyComplete();

        StepVerifier.create(messages)
                .expectNext("message 0")
                .expectNext("message 1")
                .expectNext("message 2")
                .expectNext("message 3")
                .expectNext("message 4")
                .verifyComplete();
    }

    @Test
    public void fluxElementsTest_WithError() {
        Flux<String> messages = Flux.just("message 0", "message 1", "message 2", "message 3", "message 4")
                .concatWith(Flux.error(new RuntimeException("Error message 1")))
                .log();

        StepVerifier.create(messages)
                .expectNextCount(5)
//                .expectError()
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Error message 1")
                .verify();
    }
}

