package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Test05TakeOperator {

    public static void main(String[] args) {
       //  IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
        takeWhileOperator();
    }

    public static void takeOperator() {
        Flux.range(1, 10)
                .take(3)
                .subscribe(System.out::println);
    }

    public static void takeWhileOperator() {
        Flux.range(1, 10)
                //.takeUntil(i -> i == 5) // Stop when the condition is met.
                .takeWhile(i -> i < 5) // Stop when the condition is NOT met.
                .subscribe(System.out::println);
    }
}
