package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test05Merge {

    private static final Logger log = LoggerFactory.getLogger(Test05Merge.class);

    public static void main(String[] args) {

        demo2();

        Util.sleep(4);
    }

    public static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    public static void demo2() {
        producer1()
                .mergeWith(producer2())
                .mergeWith(producer3())
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1 () {
        return Flux.just(1,2,3)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    public static Flux<Integer> producer2 () {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    public static Flux<Integer> producer3 () {
        return Flux.just(11, 12, 13)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
}
