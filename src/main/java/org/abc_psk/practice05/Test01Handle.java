package org.abc_psk.practice05;

import reactor.core.publisher.Flux;

public class Test01Handle {

    public static void main(String[] args) {
        var flux = Flux.range(1, 10);
        var flux1 = flux
                .filter(i -> i != 7)
                .handle((i, sink) -> {
                    switch (i) {
                        case 1 -> sink.next(-1);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException());
                        default -> sink.next(i);
            }
        });

        // flux.subscribe(System.out::println);
         flux1.subscribe(System.out::println);
    }
}
