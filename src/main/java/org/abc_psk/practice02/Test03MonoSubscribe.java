package org.abc_psk.practice02;

import reactor.core.publisher.Mono;

public class Test03MonoSubscribe {

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);

        mono.subscribe( i -> System.out.println(i.toString())); // onNext with request call.
        mono.subscribe(
                i -> System.out.println(i.toString()),
                e -> System.out.println(e.getMessage()),
                () -> System.out.println("Done"),
                s -> s.request(10)
        );
    }
}
