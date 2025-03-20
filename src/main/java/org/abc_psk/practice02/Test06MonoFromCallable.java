package org.abc_psk.practice02;

import reactor.core.publisher.Mono;

import java.util.List;

public class Test06MonoFromCallable {

    public static void main(String[] args) {

        // Mono.just(sum(List.of(1,2,3)));

        var mono = Mono.fromCallable(() -> sum(List.of(1,2,3)));
        mono.subscribe(System.out::println);
        
    }

    public static int sum(List<Integer> intList) throws Exception {
        System.out.println("Finding the Total of integers");
        return intList.stream().mapToInt(i -> i).sum();
    }
}
