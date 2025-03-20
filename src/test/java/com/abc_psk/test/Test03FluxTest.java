package com.abc_psk.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Test03FluxTest {

    private Logger log = LoggerFactory.getLogger(Test03FluxTest.class);

    private Flux<Integer> getItems() {
        return Flux.just(1,2,3)
                .log();
    }

    @Test
    public void fluxTest() {
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }
}
