package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Sinks;

public class Test04MultiCast {

    public static void main(String[] args) {
        demo1();
    }

    // warmup
    private static void demo2() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("hi2");
        sink.tryEmitNext("hi3");

        Util.sleep(2);

        flux.subscribe(Util.subscriber("jake"));
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi4");

        Util.sleep(500);
    }

    private static void demo1() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("hi2");
        sink.tryEmitNext("hi3");

        Util.sleep(2);

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("hi4");

        Util.sleep(500);
    }
}
