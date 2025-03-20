package org.abc_psk.practice13;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Test03ContextPropagation {

    static Logger log = LoggerFactory.getLogger(Test03ContextPropagation.class);

    public static void main(String[] args) {

        getWelcomeMessage1()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user", "b"))
                .subscribe(System.out::println);
        Util.sleep(3);
    }

    private static Mono<String> getWelcomeMessage () {
        return Mono.fromSupplier(() -> {
            return "Welcome";
        });
    }

    private static Mono<String> getWelcomeMessage1 () {
        return Mono.deferContextual((ctx) -> {
            log.info("CTX: {}", ctx);
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            else {
                return Mono.error(new RuntimeException("No user"));
            }
        });
    }

    private static Mono<String> producer1 () {
        return Mono.deferContextual(ctx -> {
            log.info("Producer1 : {}", ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic()).cast(String.class);
    }

    private static Mono<String> producer2 () {
        return Mono.deferContextual(ctx -> {
            log.info("Producer2 : {}", ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.parallel()).cast(String.class);
    }
}
