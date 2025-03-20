package com.abc_psk.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Test08ContextTest {

    Mono<String> getWelcomeMessage1 () {
        return Mono.deferContextual((ctx) -> {
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            else {
                return Mono.error(new RuntimeException("No user"));
            }
        });
    }

    @Test
    public void welcomeMessageTest () {

        var option = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(getWelcomeMessage1(), option)
                .expectNext("Welcome sa")
                .verifyComplete();
    }
}
