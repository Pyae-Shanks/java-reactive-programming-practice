package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Test03Window {

    public static void main(String[] args) {
        eventStream()
                .window(6)
                .flatMap(flux -> processEvents(flux))
                .subscribe();

        Util.sleep(10);
    }

    public static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                // .take(10)
                .concatWith(Flux.never())
                .map( event -> "Event-" + event);
    }

    public static Mono<Void> processEvents (Flux<String> flux) {
        return flux.doOnNext(i -> System.out.print("*"))
                .doOnComplete(() -> System.out.println())
                .then();
    }
}
