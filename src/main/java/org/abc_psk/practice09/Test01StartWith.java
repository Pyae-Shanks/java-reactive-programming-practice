package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Test01StartWith {

    private static final Logger log = LoggerFactory.getLogger(Test01StartWith.class);

    public static void main(String[] args) {

        demo5();
        Util.sleep(3);
    }

    public static void demo1 () {
        producer1()
                .startWith(9, 10)
                .subscribe(Util.subscriber());
    }

    public static void demo2 () {
        producer1()
                .startWith(List.of(-2, -1, 0))
                .subscribe(Util.subscriber());
    }

    public static void demo3 () {
        producer2()
                .startWith(producer1())
                .subscribe(Util.subscriber());
    }

    public static void demo4 () {
        producer2()
                .startWith(producer1())
                .startWith(1000)
                .subscribe(Util.subscriber());
    }

    public static void demo5 () {
        producer1()
                .startWith(0)
                .startWith(producer2())
                .startWith(49, 50)
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
