package com.abc_psk.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Test01MonoTest {

    private static Logger log = LoggerFactory.getLogger(Test01MonoTest.class);

    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier( () -> "Product " + id)
                .doFirst( () -> log.info("Invoked"));
    }

    @Test
    public void productTest() {
        StepVerifier.create(getProduct(1))
                .expectNext("Product 1")
                .expectComplete()
                .verify();
    }
}
