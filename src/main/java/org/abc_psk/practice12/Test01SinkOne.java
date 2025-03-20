package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Test01SinkOne {

    public static void main(String[] args) {
        demo3();
    }

    private static void demo1() {
        Sinks.One<Object> sink = Sinks.one();
        sink.asMono().subscribe(System.out::println);
        sink.tryEmitValue("Hi");
    }

    private static void demo2() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("mike"));
        mono.subscribe(Util.subscriber("Sam"));
        sink.tryEmitValue("Hi");
    }

    private static void demo3() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("mike"));

        sink.emitValue("hi", ((signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }));

        sink.emitValue("hello", ((signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }));
    }
}
