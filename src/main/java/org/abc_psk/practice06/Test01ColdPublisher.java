package org.abc_psk.practice06;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Test01ColdPublisher {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(0);
        Flux<Integer> flux = Flux.create(fluxSink -> {

            for (int i = 0; i < 10; i++) {
                fluxSink.next(count.incrementAndGet());
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("smith"));
    }
}
