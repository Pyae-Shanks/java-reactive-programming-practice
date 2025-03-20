package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test02SubscribeOn {

    private static Logger log = LoggerFactory.getLogger(Test02SubscribeOn.class);

    public static void main(String[] args) {


        Flux<Object> flux = Flux.create(sink -> {

            log.info("Subscribed to Flux");
            for (int i = 0; i < 2000; i++) {
                log.info("Generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(next -> log.info("Value {}", next));

        flux
            .doFirst(() -> log.info("First"))
            .subscribeOn(Schedulers.boundedElastic())
            .doFirst(() -> log.info("Second"))
            .subscribe(Util.subscriber());

        Util.sleep(2);
    }
}
