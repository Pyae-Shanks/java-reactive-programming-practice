package org.abc_psk.practice08;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test03MultipleSubscribers {
    private static Logger logger = LoggerFactory.getLogger(Test03MultipleSubscribers.class);

    public static void main(String[] args) {

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
                .subscribe(Util.subscriber("Subscriber1"));

        flux
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("Subscriber2"));

        Util.sleep(60);
    }

    public static int timeConsumingTask (int i) {
        Util.sleep(1);
        return i;
    }
}
