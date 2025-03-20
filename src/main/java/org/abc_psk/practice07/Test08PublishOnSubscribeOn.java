package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test08PublishOnSubscribeOn {

    private static Logger log = LoggerFactory.getLogger(Test08PublishOnSubscribeOn.class);

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {

                    log.info("Subscribed to Flux");
                    for (int i = 0; i < 2; i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doFirst(() -> log.info("First"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("Second"))
                .doOnNext(next -> log.info("Value {}", next));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable1);

        Util.sleep(2);
    }
}
