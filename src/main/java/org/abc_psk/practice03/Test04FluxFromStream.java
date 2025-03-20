package org.abc_psk.practice03;

import reactor.core.publisher.Flux;

import java.util.List;

public class Test04FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var stream = list.stream();

        var flux = Flux.fromStream(list::stream);

        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);

    }
}
