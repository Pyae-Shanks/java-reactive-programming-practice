package org.abc_psk.practice05;

import reactor.core.publisher.Flux;

public class Test05Subscribe {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .doOnNext(System.out::println)
                .subscribe();
    }
}
