package org.abc_psk.practice08;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test02LimitRate {

    private static Logger logger = LoggerFactory.getLogger(Test02LimitRate.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "25");
        Flux<Integer> flux = Flux.generate(
                () -> 1,
                (state, sink) -> {
                    System.out.println("Generating " + state);
                    sink.next(state);
                    return ++state;
                });

        flux
                .limitRate(2)
                .publishOn(Schedulers.boundedElastic())
                .map(Test01BackpressureHandling::timeConsumingTask)
                .subscribe(Util.subscriber());
        Util.sleep(60);
    }

    public static int timeConsumingTask (int i) {
        Util.sleep(1);
        return i;
    }
}
