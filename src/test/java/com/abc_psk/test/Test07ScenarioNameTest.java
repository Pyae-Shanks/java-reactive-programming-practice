package com.abc_psk.test;

import org.abc_psk.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Test07ScenarioNameTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    @Test
    public void rangeTest() {
        var option = StepVerifierOptions.create().scenarioName("range Test 1 to 3");
        StepVerifier.create(getItems(), option)
                .expectNext(11)
                .as("First should 11")
                .expectNext(12)
                .as("Second should 12")
                .expectComplete()
                .verify();
    }
}
