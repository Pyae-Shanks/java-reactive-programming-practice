package org.abc_psk.practice05;

import reactor.core.publisher.Flux;

public class Test08SwitchIfEmpty {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .filter(i -> i > 11)
                .switchIfEmpty(switchEmpty())
                .subscribe(System.out::println);
    }

    public static Flux<Integer> switchEmpty() {
        return Flux.range(100, 2);
    }
}
