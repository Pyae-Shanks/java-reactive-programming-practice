package com.abc_psk.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Test06VirtualTimeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    @Test
    public void rangeTest() {
        StepVerifier.create(getItems())
                .expectNext(1,2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimeTest() {
        StepVerifier.withVirtualTime( () -> getItems())
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimeTest2() {
        StepVerifier.withVirtualTime( () -> getItems())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2,3,4,5)
                .expectComplete()
                .verify();
    }
}
