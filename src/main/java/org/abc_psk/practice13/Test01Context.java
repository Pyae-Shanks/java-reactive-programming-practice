package org.abc_psk.practice13;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Test01Context {

    private static final Logger log = LoggerFactory.getLogger(Test01Context.class);

    public static void main(String[] args) {

        getWelcomeMessage1()
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


}
