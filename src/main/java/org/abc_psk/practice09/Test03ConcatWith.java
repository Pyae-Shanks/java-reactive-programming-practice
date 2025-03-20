package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test03ConcatWith {

    private static final Logger log = LoggerFactory.getLogger(Test03ConcatWith.class);

    public static void main(String[] args) {

        demo1();
        Util.sleep(3);
    }

    public static void demo1 () {
        producer1()
                .concatWithValues(9, 10)
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1 () {
        return Flux.just(1,2,3)
                .doOnSubscribe(s -> log.info("Subscribed to producer 1"))
                .delayElements(Duration.ofMillis(10));
    }

    public static Flux<Integer> producer2 () {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("Subscribed to producer 2"))
                .delayElements(Duration.ofMillis(10));
    }
}
