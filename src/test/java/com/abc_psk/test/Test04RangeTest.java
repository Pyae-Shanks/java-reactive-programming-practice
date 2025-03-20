package com.abc_psk.test;

import org.abc_psk.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Test04RangeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 50)
                .log();
    }

    private Flux<Integer> random() {
        return Flux.range(1, 50)
                .map(i -> Util.faker().random().nextInt(1, 100));
    }

    @Test
    public void rangeTest() {
        StepVerifier.create(getItems())
                .expectNext(1,2, 3)
                .expectNextCount(47)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest2() {
        StepVerifier.create(getItems())
                .expectNext(1,2, 3)
                .expectNextCount(22)
                .expectNext(26, 27, 28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void randomTest() {
        StepVerifier.create(getItems())
                .expectNextMatches(i -> i > 0 && i < 101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    public void randomTest2() {
        StepVerifier.create(getItems())
                .thenConsumeWhile(i -> i > 0 && i < 101)
                .expectComplete()
                .verify();
    }
}
