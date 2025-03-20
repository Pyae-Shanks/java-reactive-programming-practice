package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Sinks;

public class Test07Replay {

    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {

//        var sink = Sinks.many().multicast().onBackpressureBuffer();
//        var sink = Sinks.many().replay().all();
        var sink = Sinks.many().replay().limit(3);

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
