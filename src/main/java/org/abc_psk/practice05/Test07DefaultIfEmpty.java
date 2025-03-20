package org.abc_psk.practice05;

import reactor.core.publisher.Mono;

public class Test07DefaultIfEmpty {

    public static void main(String[] args) {

        Mono.empty().defaultIfEmpty("Hello").subscribe(System.out::println);
    }
}
