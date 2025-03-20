package org.abc_psk.practice13;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Test02ContextAppendUpdate {

    private static Logger log = LoggerFactory.getLogger(Test02ContextAppendUpdate.class);

    public static void main(String[] args) {
        demo2();
    }

    private static void demo1 () {
        getWelcomeMessage1()
                .contextWrite(Context.of("user", "a").put("count", 10))
                .contextWrite(Context.of("user", "b"))
                .subscribe(System.out::println);
    }

    private static void demo2 () {
        getWelcomeMessage1()
//                .contextWrite(ctx -> Context.empty())
                .contextWrite(ctx -> ctx.put("count", 10))
                .contextWrite(ctx -> ctx.delete("user2"))
                .contextWrite(Context.of("user", "a").put("count", 10))
                .contextWrite(Context.of("user2", "b"))
                .subscribe(System.out::println);
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
