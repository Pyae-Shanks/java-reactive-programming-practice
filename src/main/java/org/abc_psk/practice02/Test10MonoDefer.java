package org.abc_psk.practice02;

import reactor.core.publisher.Mono;

import java.util.List;

public class Test10MonoDefer {
    /*
        Creating publisher is a lightweight operation.
        Execution time-consuming business logic can be delayed.
    */

    public static void main(String[] args) {
        Mono.defer(Test10MonoDefer::createPublisher).subscribe(System.out::println);
    }

    public static Mono<Integer> createPublisher() {
        System.out.println("Creating Publisher");
        return Mono.fromSupplier(() -> sum(List.of(1,2,3)));
    }

    public static int sum(List<Integer> intList) {
        System.out.println("Finding the Total of integers");
        return intList.stream().mapToInt(i -> i).sum();
    }

}
