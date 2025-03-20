package org.abc_psk.practice08;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Test04FluxCreate {

    private static final Logger logger = LoggerFactory.getLogger(Test04FluxCreate.class);

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.create(
                sink -> {
                    for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                        logger.info("Generating {}", i);
                        sink.next(i);
                        Util.sleep2(Duration.ofMillis(50));
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        flux
                // .onBackpressureBuffer()
                // .onBackpressureError()
                 .onBackpressureBuffer(2)
                // .onBackpressureDrop()
                // .onBackpressureLatest()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Test04FluxCreate::timeConsumingTask)
                .subscribe();

        Util.sleep(60);
    }

    public static int timeConsumingTask (int i) {
        logger.info("Receiving {}", i);
        Util.sleep(1);
        return i;
    }
}
