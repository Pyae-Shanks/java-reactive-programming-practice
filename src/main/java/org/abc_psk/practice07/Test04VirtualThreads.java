package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test04VirtualThreads {

    private static Logger log = LoggerFactory.getLogger(Test04VirtualThreads.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");
        Flux<Object> flux = Flux.create(sink -> {

                    log.info("Subscribed to Flux");
                    for (int i = 0; i < 2; i++) {
                        log.info("Generating {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doFirst(() -> log.info("First {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("Second"))
                .doOnNext(next -> log.info("Value {}", next));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable1);

        Util.sleep(2);
    }
}
