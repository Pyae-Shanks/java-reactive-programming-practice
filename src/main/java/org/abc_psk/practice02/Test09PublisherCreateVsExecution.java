package org.abc_psk.practice02;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

public class Test09PublisherCreateVsExecution {
    /*
        Creating publisher is a lightweight operation.
        Execution time-consuming business logic can be delayed.
    */

    public static void main(String[] args) {
        getName().subscribe(Util.subscriber());
    }

    public static Mono<String> getName() {
        System.out.println("This is executing");
        return Mono.fromSupplier(() -> {
            System.out.println("This will execute only after subscribe");
            return Util.faker().name().firstName();
        });
    }
}
