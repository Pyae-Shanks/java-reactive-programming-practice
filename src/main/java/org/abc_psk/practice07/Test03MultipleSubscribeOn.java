package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test03MultipleSubscribeOn {

    private static Logger log = LoggerFactory.getLogger(Test03MultipleSubscribeOn.class);

    public static void main(String[] args) {


        Flux<Object> flux = Flux.create(sink -> {

            log.info("Subscribed to Flux");
            for (int i = 0; i < 2; i++) {
                log.info("Generating {}", i);
                sink.next(i);
            }
            sink.complete();
        })
          .subscribeOn(Schedulers.newParallel("TEST"))
          .doFirst(() -> log.info("First"))
//          .subscribeOn(Schedulers.boundedElastic())
          .doFirst(() -> log.info("Second"))
          .doOnNext(next -> log.info("Value {}", next));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("Sub1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("Sub2"));

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        Util.sleep(2);
    }
}
