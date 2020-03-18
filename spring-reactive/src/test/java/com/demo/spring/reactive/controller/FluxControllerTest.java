package com.demo.spring.reactive.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void getFlux_Count() {
        Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier
                .create(integerFlux)
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void getFlux_Items() {
        Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(11)
                .expectNext(22)
                .expectNext(33)
                .verifyComplete();
    }

    @Test
    public void getFlux_Size() {
        webTestClient.get().uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .expectBodyList(Integer.class)
                .hasSize(3);
    }

    @Test
    public void getFluxDelay_ConsumeWith_Lambda() {
        List<Integer> expectedIntegerList = Arrays.asList(11, 22, 33);
        webTestClient
                .get().uri("/fluxDelay")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith((response) -> {
                    assertEquals(expectedIntegerList, response.getResponseBody());
                });
    }

    @Test
    public void getFluxStream_LongStreamFlux() {
        Flux<Long> longStreamFlux = webTestClient.get().uri("/fluxStream")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();
        StepVerifier.create(longStreamFlux)
                .expectNext(11l)
                .expectNext(22l)
                .expectNext(33l)
                .thenCancel()
                .verify();
    }
}