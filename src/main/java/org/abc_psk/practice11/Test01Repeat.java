package org.abc_psk.practice11;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Test01Repeat {

    public static void main(String[] args) {

        demo4();
        Util.sleep(3);
    }

    private static void demo1 () {
        getName().repeat(3).subscribe(System.out::println);
    }

    private static void demo2 () {
        getName().repeat()
                .takeUntil(s -> s.equalsIgnoreCase("Canada"))
                .subscribe(System.out::println);
    }

    private static void demo3 () {
        var atomic = new AtomicInteger(0);
        getName().repeat(() -> atomic.incrementAndGet() < 3).subscribe(System.out::println);
    }

    private static void demo4 () {
        getName().repeatWhen(flux -> flux.delayElements(Duration.ofMillis(2000))).subscribe(System.out::println);
    }

    private static Mono<String> getName() {
        return Mono.fromSupplier(() -> {
            return Util.faker().country().name();
        });
    }
}
