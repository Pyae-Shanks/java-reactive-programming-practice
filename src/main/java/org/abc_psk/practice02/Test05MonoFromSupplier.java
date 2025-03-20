package org.abc_psk.practice02;

import reactor.core.publisher.Mono;

import java.util.List;

public class Test05MonoFromSupplier {

    public static void main(String[] args) {

        // Mono.just(sum(List.of(1,2,3)));

//        mono.subscribe(System.out::println);
        sum();

    }

    public static Mono<Integer> sum() {
        System.out.println("Finding the Total of integers");
        return Mono.fromSupplier(() -> 2 * 3);
    }
}
