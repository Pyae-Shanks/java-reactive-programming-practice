package com.abc_psk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Test02EmptyErrorTest {

    Mono<String> getUserName(int userId) {
        return switch (userId){
            case 1 -> Mono.empty();
            case 2 -> Mono.just("Shanks");
            default -> Mono.error(new RuntimeException("Invalid"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUserName(2))
                .expectNext("Shanks")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUserName(1))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest() {
        StepVerifier.create(getUserName(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUserName(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUserName(3))
                .verifyError();
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUserName(3))
                .expectErrorMessage("Invalid")
                .verify();
    }

    @Test
    public void errorTest5() {
        StepVerifier.create(getUserName(3))
                .consumeErrorWith( e -> {
                    Assertions.assertEquals(RuntimeException.class, e.getClass());
                    Assertions.assertEquals("Invalid1", e.getMessage());
                })
                .verify();
    }}
