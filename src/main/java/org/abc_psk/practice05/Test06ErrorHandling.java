package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test06ErrorHandling {

    public static void main(String[] args) {
        errorContinue();
    }

    public static void hardCodedError() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(-1)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -1)
                .subscribe(System.out::println);
    }

    public static void dynamicError() {

        Mono.error(new ArithmeticException())
                //.map(i -> i == 5 ? i / 0 : i)
                .onErrorResume(error -> fallBack())
                .onErrorResume(ArithmeticException.class, error -> anotherFallBack())
                .subscribe(System.out::println);
    }

    public static Mono<Integer> fallBack() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(1, 10));
    }

    public static Mono<Integer> anotherFallBack() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(11, 20));
    }

    public static void errorComplete() {
        Mono.error(new ArithmeticException()).onErrorComplete().subscribe(Util.subscriber());
    }

    public static void errorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((error, item) -> System.out.println("Something went wrong on continue " + error + " on this item " + item))
                .subscribe(System.out::println);
    }

}
