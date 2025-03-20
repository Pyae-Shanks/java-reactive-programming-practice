package org.abc_psk.practice11;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Test02Retry {

    public static void main(String[] args) {
        demo2();

        Util.sleep(10);
    }

    private static void demo1() {
        getName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                        .doBeforeRetry(s -> System.out.println("Retrying"))
                                .filter(throwable -> RuntimeException.class.equals(throwable.getClass()))
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getName() {

        var atomic = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if(atomic.incrementAndGet() < 3) {
                throw new RuntimeException("Error");
            }
            return Util.faker().country().name();
        }).doOnError(s -> System.out.println("Error: " + s))
          .doOnSubscribe(s -> System.out.println("Subscribed"));
    }
}
