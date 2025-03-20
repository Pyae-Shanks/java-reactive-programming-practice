package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Test09Timeout {

    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(2), fallBack())
                .subscribe(Util.subscriber());

        Util.sleep(6);
    }

    public static Mono<String> getProductName () {
        return Mono.fromSupplier(() -> "service - " + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(4));
    }

    public static Mono<String> fallBack () {
        return Mono.fromSupplier(() -> "fallback - " + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println("Do First"));
    }
}
