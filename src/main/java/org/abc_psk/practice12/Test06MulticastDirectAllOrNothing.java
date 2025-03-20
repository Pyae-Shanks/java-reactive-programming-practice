package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Test06MulticastDirectAllOrNothing {

    public static void main(String[] args) {
        demo1();
        Util.sleep(20);
    }

    private static void demo1() {

        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.onBackpressureBuffer().delayElements(Duration.ofSeconds(1)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            System.out.println("Item: " + result + " count: " + i);
        }

    }
}
