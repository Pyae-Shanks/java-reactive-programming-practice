package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Test05GroupedFlux {

    public static final Logger log = LoggerFactory.getLogger(Test05GroupedFlux.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500))
                .map( i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)
                .flatMap(flux -> processEvents(flux))
                .subscribe(System.out::println);

        Util.sleep(60);
    }

    public static Mono<Void> processEvents (GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("Received Flux for {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(integer -> log.info("Key: {}, Item: {}", groupedFlux.key(), integer))
                .doOnComplete( () -> log.info("Complete {}", groupedFlux.key()))
                .then();

    }
}
