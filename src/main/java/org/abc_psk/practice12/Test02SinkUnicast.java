package org.abc_psk.practice12;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Test02SinkUnicast {

    public static void main(String[] args) {
        demo1();
    }

    public static void demo1() {

        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer();
        // handle through which subscribers will receive items.
        Flux<Object> flux = objectMany.asFlux();

        objectMany.tryEmitNext("Hi,");
        flux.subscribe(Util.subscriber());
        objectMany.tryEmitNext("How are you");
        objectMany.tryEmitNext("?");


    }
}
