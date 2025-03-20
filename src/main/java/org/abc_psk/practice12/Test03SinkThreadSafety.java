package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Test03SinkThreadSafety {

    public static void main(String[] args) {
        demo2();
    }

    public static void demo1() {
        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = objectMany.asFlux();

        // arraylist is not thread safe.
        // intentionally chosen for demo purpose
        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int j = i;
            CompletableFuture.runAsync(() -> {
                objectMany.tryEmitNext(j);
            });
        }

        Util.sleep(5);
        System.out.println(list.size());
    }

    public static void demo2() {
        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = objectMany.asFlux();
        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int j = i;
            CompletableFuture.runAsync(() -> {
                objectMany.emitNext(j, (signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }

        Util.sleep(5);
        System.out.println(list.size());
    }
}
